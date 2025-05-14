import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a collection of tasks for the to-do list application.
 */
public class TaskManager {
    private List<Task> tasks;
    private int nextId;
    
    /**
     * Creates a new task manager with an empty task list.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }
    
    /**
     * Adds a new task with the given description.
     * 
     * @param description The description of the task
     * @return The created task
     */
    public Task addTask(String description) {
        Task task = new Task(nextId++, description);
        tasks.add(task);
        return task;
    }
    
    /**
     * Removes a task with the given ID.
     * 
     * @param id The ID of the task to remove
     * @return true if the task was removed, false if no task with the given ID exists
     */
    public boolean removeTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
    
    /**
     * Marks a task as completed.
     * 
     * @param id The ID of the task to mark as completed
     * @return true if the task was marked as completed, false if no task with the given ID exists
     */
    public boolean markTaskAsCompleted(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setCompleted(true);
            return true;
        }
        return false;
    }
    
    /**
     * Marks a task as not completed.
     * 
     * @param id The ID of the task to mark as not completed
     * @return true if the task was marked as not completed, false if no task with the given ID exists
     */
    public boolean markTaskAsNotCompleted(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setCompleted(false);
            return true;
        }
        return false;
    }
    
    /**
     * Finds a task by its ID.
     * 
     * @param id The ID of the task to find
     * @return The task, or null if no task with the given ID exists
     */
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    
    /**
     * Gets all tasks.
     * 
     * @return A list of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * Gets all completed tasks.
     * 
     * @return A list of all completed tasks
     */
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                   .filter(Task::isCompleted)
                   .collect(Collectors.toList());
    }
    
    /**
     * Gets all not completed tasks.
     * 
     * @return A list of all not completed tasks
     */
    public List<Task> getActiveTasks() {
        return tasks.stream()
                   .filter(task -> !task.isCompleted())
                   .collect(Collectors.toList());
    }
}
