// Gera o sumário lateral automaticamente a partir dos <h2> do conteúdo
// e destaca o link ativo conforme a rolagem da página.
document.addEventListener('DOMContentLoaded', () => {
  const content = document.querySelector('.faq-content');
  const navLinks = document.getElementById('faq-nav-links');
  const navToggle = document.getElementById('faq-nav-toggle');
  const nav = document.getElementById('faq-nav');

  if (!content || !navLinks) return;

  const headings = content.querySelectorAll('h2[id]');
  headings.forEach((h) => {
    const link = document.createElement('a');
    link.href = `#${h.id}`;
    link.textContent = h.textContent;
    navLinks.appendChild(link);
  });

  const linkEls = navLinks.querySelectorAll('a');

  const setActive = () => {
    let currentId = null;
    headings.forEach((h) => {
      if (h.getBoundingClientRect().top - 80 <= 0) {
        currentId = h.id;
      }
    });
    linkEls.forEach((a) => {
      a.classList.toggle('active', a.getAttribute('href') === `#${currentId}`);
    });
  };

  window.addEventListener('scroll', setActive, { passive: true });
  setActive();

  if (navToggle && nav) {
    navToggle.addEventListener('click', () => {
      nav.classList.toggle('open');
    });
    linkEls.forEach((a) =>
      a.addEventListener('click', () => nav.classList.remove('open'))
    );
  }
});
