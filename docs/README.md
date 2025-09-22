# Talkist User Guide

Welcome to **Talkist**!  
This guide will help you use all the important features of your chatbot in a friendly and easy-to-understand way.

---

## Notes about the command format

- Words in **UPPER_CASE** are parameters to be supplied by the user.  
  e.g. in `todo DESC`, `DESC` is a parameter which can be used as `todo Buy groceries`.
- All `DATE_TIME` parameters should be entered in the format **“yyyy-MM-dd HHmm”**, for example: `2025-09-29 1500`.

---

## Adding a Todo: `todo`

**Format:**  
todo DESC

**Parameters:**
- `DESC` – description of the todo task

---

## Adding a Deadline: `deadline`

**Format:**  
deadline DESC /by DATE_TIME

**Parameters:**
- `DESC` – description of the task
- `/by DATE_TIME` – due date and time

---

## Adding an Event: `event`

**Format:**  
event DESC /from DATE_TIME /to DATE_TIME

**Parameters:**
- `DESC` – description of the event
- `/from DATE_TIME` – start date and time
- `/to DATE_TIME` – end date and time

---

## Listing all tasks: `list`

Shows a list of all tasks in your task list.

**Example:**  
list  

1. [T][ ] Buy groceries #life  
2. [D][ ] Submit report #academics (by: 2025-09-22 23:59)  
All done! Hope it looks cute ✨

---

## Marking a task as done: `mark`

Marks a task as completed. **[X]** will be shown before the task description.

**Format:**  
mark INDEX

**Parameters:**
- `INDEX` – index of the task in the list (starting from 1), as shown in list

**Example:**  
mark 1  

Yay! I marked this task as done ✅  
[T][X] Buy groceries #life

---

## Unmarking a task: `unmark`

Unmarks a task as not done. **X** in **[X]** will be removed.

**Format:**  
unmark INDEX

**Parameters:**
- `INDEX` – index of the task in the list (starting from 1), as shown in list

**Example:**  
unmark 1

Okay~ this task is now not done  
[T][ ] Buy groceries #life

---

## Deleting a task: `delete`

**Format:**  
delete INDEX

---

## Finding tasks: `find`

Finds tasks that contain a keyword.

**Format:**  
find KEYWORD

**Parameters:**
- `KEYWORD` – word to search for in task descriptions

**Example:**  
find groceries

**Responses:**  
Here are the matching tasks in your list:
[T][ ] Buy groceries #life

If no matches:  
Hmm... no tasks found for: groceries 😿

---

## Tagging a task: `tag`

Assigns a tag to a task.

**Format:**  
tag INDEX TAG

**Parameters:**
- `INDEX` – index of the task in the list (starting from 1), as shown in list
- `TAG` – tag to assign

---

## Exiting the chatbot: `bye`

Exits Talkist.

---

This guide covers all the important commands for **Talkist**. Use it to quickly manage your tasks and enjoy a cute, friendly chatbot experience!