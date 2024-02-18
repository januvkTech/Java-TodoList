package todolist;
import java.util.Scanner;
import java.util.*;

class Task {
    private String description;
    private int importance;
    private boolean completed;

    public Task(String description, int importance) {
        this.description = description;
        this.importance = importance;
        completed = false;
    }

    public void makedAsCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return importance;
    }
}
public class TodoList {
    private ArrayList<Task> tasks;

    public TodoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task); 
        Collections.sort(tasks, Comparator.comparingInt(Task::getPriority).reversed());
    }

    public void showAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "Task Description:" + task.getDescription() + "-Priority:" + task.getPriority()
                    + "- Completed:" + task.isCompleted());
        }
    }

    public void showPriorityTask(int priority) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getPriority() == priority) {
                System.out.println((i+1)+"Task Description:" + task.getDescription() + "-Priority:" + task.getPriority()
                        + "- Completed:" + task.isCompleted());
            } else {
                continue;
            }
        }
    }

    public void deleteTask(int index) {
        if(index>0&&index<tasks.size()){
            Task task=tasks.remove(index);
            System.out.println("Task Removed:"+task.getDescription());
        }else{
            System.out.print("Invalid Task Index!");
        }
    }

    public void markTaskAsCompleted(int index){
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.makedAsCompleted();
            System.out.println("Task marked as completed: " + task.getDescription());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void main(String[] args) {
         TodoList todoList = new TodoList(); // Create todo list object
        Scanner scanner = new Scanner(System.in); // Create scanner object to read user input

        // Menu for todo list operations
        while (true) {
            System.out.println("\nTodo List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. View Priority based Tasks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user choice

            // Handle user choice
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    System.out.print("Enter task priority: ");
                    int importance = scanner.nextInt();
                    Task newTask = new Task(description, importance);
                    todoList.addTask(newTask);
                    System.out.println("Task added.");
                    break;
                case 2:
                    todoList.showAllTasks();
                    break;
                case 3:
                    System.out.print("Enter task index to mark as completed: ");
                    int index = scanner.nextInt();
                    todoList.markTaskAsCompleted(index - 1);
                    break;
                case 4:
                    System.out.print("Enter task index to delete: ");
                    index = scanner.nextInt(); 
                    todoList.deleteTask(index - 1); 
                    break;
                case 5:
                    System.out.print("Enter priority of the desired Tasks: ");
                    int priority = scanner.nextInt(); 
                    todoList.showPriorityTask(priority); 
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner
                    System.exit(0); // Exit program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

}
