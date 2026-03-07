# Spike Task Manager

Spike is a **command-line task manager chatbot** that helps you keep track of tasks quickly using simple text commands.

It runs entirely in the terminal and allows you to manage todos, deadlines, and events efficiently.

---

## Features

- Add **Todo tasks**
- Add **Deadline tasks**
- Add **Event tasks**
- **Mark / unmark** tasks as done
- **Delete** tasks
- **Find tasks** using keywords
- **Persistent storage** (tasks saved automatically)

---

## Quick Start

### 1. Clone the repository

```bash
git clone https://github.com/woshiweiha0/ip.git


### 2. Compile the program

From the project root:


cd src/main/java
javac spike/*.java


### 3. Run Spike


java spike.Spike


---

## Example Usage


todo read book
deadline submit report /by Friday
event meeting /from 2pm /to 4pm
list
mark 1
find book
delete 2
bye


---

## User Guide

For detailed documentation and examples, visit the full **User Guide**:

**https://woshiweiha0.github.io/ip/**

---

## Project Structure


src/main/java/spike/
├── Spike.java # Main application
├── Parser.java # Parses user commands
├── TaskList.java # Stores and manages tasks
├── Storage.java # Handles file saving/loading
├── Ui.java # Handles user interaction
├── Task.java # Base task class
├── Todo.java # Todo task
├── Deadline.java # Deadline task
└── Event.java # Event task


---

## Requirements

- Java **JDK 17**
- IntelliJ IDEA or any Java IDE

---

## License

This project is developed as part of the **NUS CS2113 Software Engineering course**.