# GitHub Copilot / AI agent instructions (project-specific)

Purpose
- Short, discoverable guidance aimed at AI coding assistants to speed up useful contributions in this repository.

Big picture (what to know quickly)
- This workspace contains two main areas:
  - `Automatizace/` — Java test code and related automation (`ExampleTest.java`, `Sprin4_IvCa/`). Expect test sources and test-related utilities here.
  - `IKEItas/` — a small static site: `index.html`, images (e.g. `Kolo.jpg`), and `mycss.css`.
- There is no explicit build manifest (no `pom.xml`, `build.gradle`, or `package.json`) discovered. Assume minimal builds: plain `javac` for Java sources and direct file edits for static site files unless told otherwise.

Key files & places to inspect
- `Automatizace/ExampleTest.java` — example of Java test code and naming conventions used for test classes.
- `Automatizace/Sprin4_IvCa/` — subfolder with sprint-specific code; changes here likely relate to automation or test cases.
- `IKEItas/index.html` and `IKEItas/mycss.css` — examples of the static frontend; edit here for UI/HTML/CSS fixes.
- Root files like `file.txt`, `test.txt`, and `pokus.log` appear to be artifacts or notes. Avoid changing them unless requested.

Project-specific conventions and patterns
- Tests and automation live under `Automatizace/` and appear to use plain Java sources. Keep edits Java-focused here.
- Filenames use non-ASCII characters in directory names (e.g., `Verzování`). Preserve encoding and paths when manipulating files on Windows PowerShell.
- No CI or linter config was detected; be conservative when introducing new tooling or dependencies.

Build / test / debug guidance (conservative defaults)
- Because there is no build file in repo, use these conservative commands on Windows PowerShell to compile/run Java tests manually if needed:

```powershell
# compile all .java files under Automatzace
javac -d out (Get-ChildItem -Recurse -Filter *.java -Path Automatisace).FullName
# or compile a single file
javac -d out Automatzace\ExampleTest.java
```

- If the user indicates they use Maven/Gradle, switch to `mvn test` or `gradle test` accordingly. Ask before adding build files.

Integration points & external dependencies
- No networked services or external integration points were found in the repo. If you need to add integrations (APIs, CI, package managers), first ask the user which service and credentials should be used.

How to behave as an AI coding assistant (concrete rules)
- Prefer minimal, non-invasive changes. Small PRs that change only the necessary files are preferred.
- When modifying `Automatizace/` Java tests, keep test naming and package structure consistent. Show both the changed file and a one-line explanation in the PR.
- When changing static site files in `IKEItas/`, include a short screenshot or description of the visible change in the PR description.
- Do not add or commit new build systems (Maven/Gradle) unless the user asks; instead, propose them and get approval.

Examples of helpful edit suggestions
- Fix a failing test in `Automatizace/ExampleTest.java`: create a focused patch that updates only the failing assertions and add a short test run result.
- Improve CSS in `IKEItas/mycss.css` to fix layout: change minimal selectors and include before/after comments in the PR description.

Unknowns and assumptions (call these out in PRs)
- No build manifest found: I assumed manual javac-based builds for Java. If the project uses Maven/Gradle, update the instructions and add build files only after confirming with the repository owner.
- No CI was detected; run local tests and ask the user whether to add automated CI.

If anything in this file looks incomplete or incorrect, ask the repo owner these quick questions before making further edits:
1. Which Java build tool (Maven/Gradle/none) should I assume?
2. Are there any hidden folders or submodules I should know about?
3. Do you want me to add a basic CI workflow after I confirm the preferred build tool?

End of file.