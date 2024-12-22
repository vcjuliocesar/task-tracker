## Task Tracker

[Task Tracker](https://roadmap.sh/projects/task-tracker") is a project used to track and manage your tasks. In this task, you will build a simple command-line interface (CLI) to track what you need to do, what you have done, and what you are currently working on. This project will help you practice your programming skills, including working with the filesystem, handling user inputs, and building a simple CLI application.

### Features
- Add, update, and delete tasks.
- Mark a task as in progress or done.
- List all tasks.
- List all tasks that are done.
- List all tasks that are not done.
- List all tasks that are in progress.

### Installation

1. Clone the repository:
   ```bash
   git clone git@github.com:vcjuliocesar/task-tracker.git
   cd task-tracker
   ```
2. Compile the source code and run the app:
    ```bash
    javac -d bin src/*.java && java src/App.java
    ```
### Usage

```bash
    "Enter a command:"
    # Adding a new task
    add "Buy groceries"
    # Output: Task added successfully 1

    # Updating a task
    update 1 "Buy groceries and cook dinner"
    # Output: Task updated successfully 1
    
    # Deleting a task
    delete 1
    # Output: Task deleted successfully 1

    # Deleting a task
    delete 1
    # Output: Task deleted successfully 1

    # Marking a task as done
    mark-done 1
    # Output: Task marked as done 1

    # Listing all tasks
    list
    # Output: List of all tasks

    # Listing tasks by status
    list-todo
    list-in-progress
    list-done
```