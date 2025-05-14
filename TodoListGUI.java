import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Graphical user interface for the to-do list application using Swing.
 */
public class TodoListGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private TaskManager taskManager;
    private JPanel taskPanel;
    private JTextField taskInputField;
    private JComboBox<String> filterComboBox;
    
    /**
     * The main method to start the GUI application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoListGUI gui = new TodoListGUI();
            gui.setVisible(true);
        });
    }
    
    /**
     * Constructs the GUI for the to-do list application.
     */
    public TodoListGUI() {
        taskManager = new TaskManager();
        
        // Set up the main frame
        setTitle("ToDo List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        
        // Create the main panel with a border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Create the input panel
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Create the filter panel
        JPanel filterPanel = createFilterPanel();
        mainPanel.add(filterPanel, BorderLayout.CENTER);
        
        // Create the task panel with a scroll pane
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        // Add the main panel to the frame
        getContentPane().add(mainPanel);
        
        // Refresh the task list to show initial state
        refreshTaskList("All");
    }
    
    /**
     * Creates the input panel for adding new tasks.
     */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        
        taskInputField = new JTextField();
        JButton addButton = new JButton("Add Task");
        
        addButton.addActionListener(e -> {
            String taskDescription = taskInputField.getText().trim();
            if (!taskDescription.isEmpty()) {
                taskManager.addTask(taskDescription);
                taskInputField.setText("");
                refreshTaskList(getSelectedFilter());
            }
        });
        
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        
        return inputPanel;
    }
    
    /**
     * Creates the filter panel for filtering tasks.
     */
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel filterLabel = new JLabel("Filter: ");
        filterComboBox = new JComboBox<>(new String[]{"All", "Active", "Completed"});
        
        filterComboBox.addActionListener(e -> refreshTaskList(getSelectedFilter()));
        
        filterPanel.add(filterLabel);
        filterPanel.add(filterComboBox);
        
        return filterPanel;
    }
    
    /**
     * Gets the currently selected filter.
     */
    private String getSelectedFilter() {
        return (String) filterComboBox.getSelectedItem();
    }
    
    /**
     * Refreshes the task list based on the selected filter.
     */
    private void refreshTaskList(String filter) {
        taskPanel.removeAll();
        
        List<Task> tasksToShow;
        
        switch (filter) {
            case "Active":
                tasksToShow = taskManager.getActiveTasks();
                break;
            case "Completed":
                tasksToShow = taskManager.getCompletedTasks();
                break;
            default:
                tasksToShow = taskManager.getAllTasks();
                break;
        }
        
        if (tasksToShow.isEmpty()) {
            JLabel emptyLabel = new JLabel("No tasks to display");
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            taskPanel.add(emptyLabel);
        } else {
            for (Task task : tasksToShow) {
                taskPanel.add(createTaskPanel(task));
            }
        }
        
        taskPanel.revalidate();
        taskPanel.repaint();
    }
    
    /**
     * Creates a panel for displaying a single task.
     */
    private JPanel createTaskPanel(Task task) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // Create the checkbox for the task completion status
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(task.isCompleted());
        checkBox.addActionListener(e -> {
            task.setCompleted(checkBox.isSelected());
            refreshTaskList(getSelectedFilter());
        });
        
        // Create the label for the task description
        JLabel descriptionLabel = new JLabel(task.getDescription());
        if (task.isCompleted()) {
            descriptionLabel.setForeground(Color.GRAY);
        }
        
        // Create the remove button
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            taskManager.removeTask(task.getId());
            refreshTaskList(getSelectedFilter());
        });
        
        // Create a panel for the task ID and checkbox
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftPanel.add(new JLabel("#" + task.getId()));
        leftPanel.add(checkBox);
        
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(removeButton, BorderLayout.EAST);
        
        return panel;
    }
}
