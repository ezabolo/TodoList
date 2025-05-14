/**
 * Represents a task in the to-do list application.
 */
public class Task {
    private String description;
    private boolean completed;
    private int id;
    
    /**
     * Constructs a new Task with the given description.
     * 
     * @param id The unique identifier for this task
     * @param description The description of the task
     */
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }
    
    /**
     * Gets the task's ID.
     * 
     * @return The task ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the task's description.
     * 
     * @return The task description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the task's description.
     * 
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Checks if the task is completed.
     * 
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }
    
    /**
     * Marks the task as completed or not completed.
     * 
     * @param completed The completed status to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    /**
     * Returns a string representation of the task.
     * 
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%d] %s %s", 
                            id,
                            completed ? "[✓]" : "[ ]", 
                            description);
    }
}
