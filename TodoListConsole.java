import java.util.List;
import java.util.Scanner;

/**
 * Console-based interface for the to-do list application.
 */
public class TodoListConsole {
    private static final TaskManager taskManager = new TaskManager();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean running = true;
        
        System.out.println("Welcome to ToDo List Application");
        
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewAllTasks();
                    break;
                case 3:
                    viewActiveTasks();
                    break;
                case 4:
                    viewCompletedTasks();
                    break;
                case 5:
                    markTaskAsCompleted();
                    break;
                case 6:
                    markTaskAsNotCompleted();
                    break;
                case 7:
                    removeTask();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using ToDo List Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n===== ToDo List Menu =====");
        System.out.println("1. Add new task");
        System.out.println("2. View all tasks");
        System.out.println("3. View active tasks");
        System.out.println("4. View completed tasks");
        System.out.println("5. Mark task as completed");
        System.out.println("6. Mark task as not completed");
        System.out.println("7. Remove task");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        
        if (description.trim().isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
        
        Task task = taskManager.addTask(description);
        System.out.println("Task added: " + task);
    }
    
    private static void viewAllTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        displayTasks("All Tasks", tasks);
    }
    
    private static void viewActiveTasks() {
        List<Task> tasks = taskManager.getActiveTasks();
        displayTasks("Active Tasks", tasks);
    }
    
    private static void viewCompletedTasks() {
        List<Task> tasks = taskManager.getCompletedTasks();
        displayTasks("Completed Tasks", tasks);
    }
    
    private static void displayTasks(String title, List<Task> tasks) {
        System.out.println("\n===== " + title + " =====");
        
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
    
    private static void markTaskAsCompleted() {
        int id = promptForTaskId("Enter the ID of the task to mark as completed: ");
        
        if (id == -1) {
            return;
        }
        
        boolean success = taskManager.markTaskAsCompleted(id);
        
        if (success) {
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("No task found with ID " + id + ".");
        }
    }
    
    private static void markTaskAsNotCompleted() {
        int id = promptForTaskId("Enter the ID of the task to mark as not completed: ");
        
        if (id == -1) {
            return;
        }
        
        boolean success = taskManager.markTaskAsNotCompleted(id);
        
        if (success) {
            System.out.println("Task marked as not completed.");
        } else {
            System.out.println("No task found with ID " + id + ".");
        }
    }
    
    private static void removeTask() {
        int id = promptForTaskId("Enter the ID of the task to remove: ");
        
        if (id == -1) {
            return;
        }
        
        boolean success = taskManager.removeTask(id);
        
        if (success) {
            System.out.println("Task removed.");
        } else {
            System.out.println("No task found with ID " + id + ".");
        }
    }
    
    private static int promptForTaskId(String prompt) {
        System.out.print(prompt);
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return -1;
        }
    }
}
