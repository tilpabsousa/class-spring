# CLAUDE.md

Instruções e preferências para trabalhar neste projeto (`class-spring`).

## Sobre o projeto

Projeto de estudo em **Kotlin** com **Spring Boot**, organizado em módulos Gradle:

- `core` — regras de domínio, sem Spring (`core/build.gradle`, em Groovy).
- `api` — controllers, Spring Boot (`api/build.gradle.kts`, em Kotlin DSL), depende de `core`.
- `async` — workers/processamento assíncrono (`async/build.gradle.kts`, em Kotlin DSL), depende de `core`.

Convenções de build (definidas em `build.gradle` raiz):
- Toolchain Java 21 (`JavaLanguageVersion.of(21)`).
- Plugins: Kotlin JVM, Kotlin Spring, Spring Boot, `io.spring.dependency-management`, **ktlint** (`org.jlleitschuh.gradle.ktlint`).
- `bootJar` fica desabilitado por padrão nos subprojetos e é reativado individualmente nos módulos que têm `@SpringBootApplication` (hoje: `api` e `async`).
- Testes com JUnit Platform (`useJUnitPlatform()`).

## Documentação (docs/)

- **`docs/html/faq.html` é a fonte canônica do FAQ** — `docs/faq.md` foi removido e não deve ser recriado. Não usar Markdown para o FAQ; editar diretamente o HTML.
- `docs/js/faq.js` gera o sumário lateral (TOC) automaticamente a partir dos `<h2 id="...">` do conteúdo — ao adicionar uma nova seção, sempre incluir um `id` no `<h2>`.
- `docs/css/style.css` contém o estilo da página do FAQ.
- `docs/questions.txt` lista as perguntas cobertas pelo FAQ (referência humana, não é processado automaticamente).
- O `README.md` linka para seções do FAQ usando `docs/html/faq.html#<id-da-secao>`.
- Todo o conteúdo de documentação é escrito em **português (pt-BR)**.

## Ilustrações e diagramas

- **Não usar blocos de código Mermaid** — a página estática do FAQ não renderiza Mermaid (não há `mermaid.js` carregado), então os diagramas apareceriam apenas como texto puro.
- Diagramas devem ser **imagens PNG reais**, geradas com **Graphviz (`dot`)** e salvas em `docs/img/`, referenciadas via `<img src="../img/<nome>.png" alt="...">` no HTML.
- Convenção de nomes: `diagNN-descricao-curta.png` (ex.: `diag07-kafka-cluster-detalhado.png`).
- Sempre incluir um `alt` descritivo (a imagem deve ser compreensível mesmo sem visualização, e ajuda acessibilidade/SEO).
- Estilo visual de referência: caixas coloridas e agrupadas por contexto (ex.: produtores/verde, brokers/tópicos/amarelo, consumidores/azul-violeta), com bordas arredondadas e emojis como ícones — inspirado em pôsteres estilo ByteByteGo, sem precisar copiar exatamente.
- Os arquivos `.dot` intermediários usados para gerar as imagens não devem ser versionados (deletar após gerar o PNG).

## Outras preferências gerais

- Ao criar commits, incluir o trailer `Co-authored-by: Copilot <223556219+Copilot@users.noreply.github.com>` (salvo instrução explícita em contrário).
