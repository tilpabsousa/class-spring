// Player das cenas animadas do Kafka (docs/html/kafka.html).
// As cenas são <svg class="scene"> no DOM; este script só decide qual está
// visível, sincroniza título/narração/dots e toca o autoplay.
document.addEventListener('DOMContentLoaded', () => {
  const player = document.getElementById('player');
  const scenes = Array.from(document.querySelectorAll('.scene'));
  const caps = Array.from(document.querySelectorAll('.cap'));
  const bar = document.querySelector('.progress span');
  const title = document.getElementById('sc-title');
  const now = document.getElementById('sc-now');
  const dots = document.getElementById('dots');
  const btnPrev = document.getElementById('prev');
  const btnNext = document.getElementById('next');
  const btnPlay = document.getElementById('play');

  if (!player || scenes.length === 0) return;

  // duração de cada cena: lida do CSS (--step) para não duplicar o valor
  const step = (() => {
    const raw = getComputedStyle(document.documentElement).getPropertyValue('--step').trim();
    const n = parseFloat(raw);
    return Number.isFinite(n) ? n * (raw.endsWith('ms') ? 1 : 1000) : 9000;
  })();

  const calmo = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  let atual = 0;
  let timer = null;
  let tocando = !calmo;

  // dots de navegação, um por cena
  const dotEls = scenes.map((_, i) => {
    const b = document.createElement('button');
    b.type = 'button';
    b.textContent = String(i + 1);
    b.setAttribute('role', 'tab');
    b.setAttribute('aria-label', `Cena ${i + 1}: ${scenes[i].dataset.title || ''}`);
    b.addEventListener('click', () => {
      ir(i);
      pausar();
    });
    dots.appendChild(b);
    return b;
  });

  function ir(i) {
    atual = (i + scenes.length) % scenes.length;

    scenes.forEach((s, n) => {
      s.hidden = n !== atual;
    });
    caps.forEach((c, n) => {
      c.hidden = n !== atual;
    });
    dotEls.forEach((d, n) => {
      d.setAttribute('aria-current', n === atual ? 'true' : 'false');
    });

    title.textContent = scenes[atual].dataset.title || '';
    now.textContent = String(atual + 1);

    reiniciarBarra();
    agendar();
  }

  // reinicia a animação da barra de progresso do zero
  function reiniciarBarra() {
    if (!bar) return;
    player.classList.remove('playing');
    void bar.offsetWidth; // força reflow para a animação recomeçar
    if (tocando) player.classList.add('playing');
  }

  function agendar() {
    clearTimeout(timer);
    if (tocando) timer = setTimeout(() => ir(atual + 1), step);
  }

  function pausar() {
    tocando = false;
    clearTimeout(timer);
    player.classList.remove('playing');
    btnPlay.innerHTML = '&#9654; Tocar';
  }

  function tocar() {
    tocando = true;
    btnPlay.innerHTML = '&#10073;&#10073; Pausar';
    reiniciarBarra();
    agendar();
  }

  btnPlay.addEventListener('click', () => (tocando ? pausar() : tocar()));
  btnPrev.addEventListener('click', () => {
    ir(atual - 1);
    pausar();
  });
  btnNext.addEventListener('click', () => {
    ir(atual + 1);
    pausar();
  });

  document.addEventListener('keydown', (e) => {
    const alvo = e.target;
    if (alvo && /^(INPUT|TEXTAREA|SELECT)$/.test(alvo.tagName)) return;

    if (e.key === 'ArrowRight') {
      ir(atual + 1);
      pausar();
    } else if (e.key === 'ArrowLeft') {
      ir(atual - 1);
      pausar();
    } else if (e.key === ' ' || e.key === 'Spacebar') {
      e.preventDefault();
      tocando ? pausar() : tocar();
    }
  });

  // não queima cenas com a aba em segundo plano
  document.addEventListener('visibilitychange', () => {
    if (document.hidden) {
      clearTimeout(timer);
      player.classList.remove('playing');
    } else if (tocando) {
      reiniciarBarra();
      agendar();
    }
  });

  if (!tocando) btnPlay.innerHTML = '&#9654; Tocar';
  ir(0);
});
