# UI Test Plan

This file is the source of truth for scripted acceptance tests of the interactive console UI. Add concrete test cases before running `$test-ui`.

## Test setup

- **Required Java version:** Java 25
- **Working directory:** Repository root
- **Build command:** Record the current project command before testing.
- **Launch command:** Record the current project command before testing.
- **State setup:** Record any files or other initial state needed for repeatable tests.
- **State cleanup:** Record any cleanup needed after a test case.
- **Comparison:** Compare exact application output after normalizing only `CRLF` and `LF` line endings. Terminal input echo is included in the transcript but excluded from comparison.
- **Timeouts:** Record any project-appropriate startup or response timeout.

## Test cases

Add each test case using the structure below. Each test case runs in a fresh application process unless its preconditions say otherwise.

### TEST-01: Replace with a short name

**Aim:** Replace with the behavior this test verifies.

**Preconditions:** Replace with required initial state, or `None`.

#### Step 1

**Input**

```text
replace with one console command
```

**Expected output**

```text
replace with the exact output produced in response
```

Add more numbered steps for additional commands. Include a clean exit command when relevant.
