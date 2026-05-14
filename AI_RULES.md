# AI Coding Guidelines & Framework Rules

**IMPORTANT**: Any AI coding assistant MUST read, understand, and strictly follow these rules before making any code changes, suggestions, or refactoring in this project.
**ALL AI RESPONSES AND EXPLANATIONS MUST BE IN VIETNAMESE (TIẾNG VIỆT).**

## 1. Framework Philosophy
- Prefer stability over clever abstractions.
- Prefer explicit behavior over magic.
- Avoid framework rewrites.
- Avoid unnecessary wrappers.
- Minimize flaky-test risks.
- Keep architecture boring and predictable.

## 2. Locator Rules
Preferred locator priority:
1. `data-testid`
2. `id`
3. Stable `css` selectors
4. `xpath` (only as a last resort)

- Avoid brittle CSS chains tied to layout structure
- Avoid index-based xpath selectors when possible
- Prefer semantic and stable selectors

## 3. Wait Strategy
- **Never** use `Thread.sleep`.
- Prefer page/component-specific waits.
- Avoid global retry systems.
- Avoid generic `waitUntilEverything` methods.
- Re-resolve elements before final actions (to prevent `StaleElementReferenceException`).

## 4. Page Object Rules
- Store `By` locators, not `WebElement` fields.
- Keep business logic out of tests.
- Avoid oversized page objects.
- Prefer reusable components only when real reuse exists.

## 5. Reusable Component Standards
Only create components for:
- repeated UI patterns
- complex synchronization
- real business reuse

Components must:
- avoid owning WebDriver directly
- avoid caching WebElement references
- encapsulate synchronization internally

## 6. Assertion Rules
- Assertions must be stable.
- Avoid timing-dependent assertions.
- Include meaningful failure messages.

## 7. AI Coding Restrictions
AI must **NOT**:
- Introduce new framework layers without justification.
- Create retry frameworks.
- Generate wrapper classes with little value.
- Duplicate existing utilities.
- Rewrite stable architecture.
- Cache `WebElement` instances.

## 8. Thread Safety Rules
- Never store WebDriver in shared mutable instance fields
- Never cache WebElement references
- All driver access must go through DriverManager or BasePage
- Components must not own WebDriver lifecycle
- Framework must support parallel execution safely

## 9. Timeout Philosophy
- Never hardcode waits inside page objects
- Timeouts must be centrally configurable
- CI environments may require longer synchronization
- Prefer explicit synchronization over long waits

## 10. Framework Boundary Rules

Framework core MUST NOT contain:
- business workflows
- project-specific terminology
- customer-specific logic
- application-specific assertions

Project layers MUST NOT:
- duplicate waits
- duplicate driver management
- duplicate synchronization utilities
- bypass framework standards

## 11. Failure Diagnostics Rules

On test failure, framework should capture:
- screenshot
- current URL
- page source
- browser console logs when possible

Failure messages must:
- explain expected vs actual behavior
- avoid vague assertion messages
- support debugging without rerunning tests

## 12. Test Design Rules

Tests should:
- validate business behavior, not UI implementation details
- avoid excessive assertions in a single test
- remain independent and isolated
- avoid dependency between tests
- support parallel execution safely

## 13. AI Review Expectations

AI should:
- identify root causes before suggesting fixes
- prefer localized changes over broad rewrites
- evaluate flaky-test risks before introducing abstractions
- detect duplicate utilities and hidden complexity
- challenge unnecessary framework growth

**Mandatory Post-Update Self-Review:**
- After proposing any code changes or generating a diff, the AI MUST explicitly perform a self-review of its own output.
- Verify that the new code strictly adheres to all thread-safety, locator, and simplicity rules.
- If the generated code violates any rule, the AI must correct it internally before presenting the final response.
- Explicitly state the result of this self-review in the response.

## 14. Simplicity Rules

Prefer:
- fewer abstractions
- explicit logic
- localized synchronization
- readable page objects

Avoid:
- helper proliferation
- generic frameworks pretending to solve all UI problems
- abstraction without proven reuse