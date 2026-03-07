# Spike User Guide

Spike is a **command-line task manager chatbot** that helps you track tasks quickly and efficiently using simple text commands.

Spike supports the following task types:

- Todo tasks
- Deadline tasks
- Event tasks
- Finding tasks using keywords

Spike runs in the terminal and stores tasks locally so they persist between runs.

---

# Features

## Viewing all tasks

Shows all tasks currently in the list.

Command:


list


Example:


list


Expected output:


Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit assignment (by: Friday)


---

## Adding a todo task

Adds a simple task with no date.

Command:


todo <description>


Example:


todo read book


Expected output:


Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.


---

## Adding a deadline

Adds a task with a deadline.

Command:


deadline <description> /by <time>


Example:


deadline submit report /by Friday


Expected output:


Got it. I've added this task:
[D][ ] submit report (by: Friday)


---

## Adding an event

Adds a task that occurs during a time range.

Command:


event <description> /from <start> /to <end>


Example:


event meeting /from 2pm /to 4pm


Expected output:


Got it. I've added this task:
[E][ ] meeting (from: 2pm to: 4pm)


---

## Marking a task as done

Marks a task as completed.

Command:


mark <task number>


Example:


mark 1


Expected output:


Nice! I've marked this task as done:
[T][X] read book


---

## Unmarking a task

Marks a completed task as not done.

Command:


unmark <task number>


Example:


unmark 1


---

## Deleting a task

Removes a task from the list.

Command:


delete <task number>


Example:


delete 2


Expected output:


Noted. I've removed this task:
[D][ ] submit report


---

## Finding tasks

Searches for tasks containing a keyword.

Command:


find <keyword>


Example:


find book


Expected output:


Here are the matching tasks in your list:
1.[T][ ] read book


---

## Exiting the program

Exits Spike.

Command:


bye


Example:


bye


Expected output:


Bye. Hope to see you again soon!


---

# Command Summary

| Command | Format |
|------|------|
| list | `list` |
| todo | `todo <description>` |
| deadline | `deadline <description> /by <time>` |
| event | `event <description> /from <start> /to <end>` |
| mark | `mark <task number>` |
| unmark | `unmark <task number>` |
| delete | `delete <task number>` |
| find | `find <keyword>` |
| exit | `bye` |

---

# Saving

Spike automatically saves tasks to:


data/spike.txt


Tasks will be loaded automatically when the program starts again.