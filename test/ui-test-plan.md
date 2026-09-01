# UI Test Plan

This file is the source of truth for scripted acceptance tests of the interactive console UI. Add concrete test cases before running `$test-ui`.

## Test setup

- **Required Java version:** Java 25
- **Working directory:** Repository root
- **Build command:** `javac -d src/main/java/out src/main/java/alfred/*.java`
- **Launch command:** `java -cp src/main/java/out alfred.Alfred`
- **State setup:** None. Tasks are stored only in memory and each application process starts with an empty list.
- **State cleanup:** Close standard input after the assertions so the application exits normally.
- **Comparison:** Compare exact application output after normalizing only `CRLF` and `LF` line endings. Terminal input echo is included in the transcript but excluded from comparison.
- **Timeouts:** Allow 10 seconds for startup and 5 seconds for each command response.

## Test cases

Each test case runs in a fresh application process unless its preconditions say otherwise.

### TEST-01: Add and list all task types

**Aim:** Verify that todos, deadlines, and events are added with their type-specific details and listed in order.

**Preconditions:** The application has just started and the in-memory task list is empty.

#### Step 1

**Input**

```text
todo borrow book
```

**Expected output**

```text
____________________________________________________________
Understood Master Wayne, I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
```

#### Step 2

**Input**

```text
deadline return book /by Sunday
```

**Expected output**

```text
____________________________________________________________
Understood Master Wayne, I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### Step 3

**Input**

```text
event project meeting /from Mon 2pm /to 4pm
```

**Expected output**

```text
____________________________________________________________
Understood Master Wayne, I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```

#### Step 4

**Input**

```text
list
```

**Expected output**

```text
____________________________________________________________
These are your tasks Master Wayne:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
```

After Step 4 passes, close standard input so the application exits normally.

### TEST-02: Reject a deadline without a description

**Aim:** Verify that a deadline without a task description explains the exact problem and shows how to fix it.

**Preconditions:** The application has just started and the in-memory task list is empty.

#### Step 1

**Input**

```text
deadline /by tomorrow
```

**Expected output**

```text
____________________________________________________________
Unable to add the deadline: the task description is missing.
Use this format: deadline <description> /by <date or time>
Example: deadline return book /by tomorrow
____________________________________________________________
```

After Step 1 passes, close standard input so the application exits normally.

### TEST-03: Reject an event without a description

**Aim:** Verify that an event without a task description explains the exact problem and shows how to fix it.

**Preconditions:** The application has just started and the in-memory task list is empty.

#### Step 1

**Input**

```text
event /from Mon 2pm /to 4pm
```

**Expected output**

```text
____________________________________________________________
Unable to add the event: the task description is missing.
Use this format: event <description> /from <start> /to <end>
Example: event project meeting /from Mon 2pm /to 4pm
____________________________________________________________
```

After Step 1 passes, close standard input so the application exits normally.
