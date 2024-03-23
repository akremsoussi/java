import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoGUI extends JFrame {
    private TaskManager taskManager;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JTextField taskDescriptionField;
    private JTextField dueDateField;
    private FileHandler fileHandler;

    public ToDoGUI() {
        super("ToDo List");
        taskManager = new TaskManager();
        fileHandler = new FileHandler("tasks.dat");
        List<Task> tasks = fileHandler.loadTasks();
        if (tasks != null) {
            for (Task task : tasks) {
                taskManager.addTask(task);
            }
        }

        taskListModel = new DefaultListModel<>();
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task);
        }

        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        taskDescriptionField = new JTextField();
        dueDateField = new JTextField();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = taskDescriptionField.getText();
                String dueDate = dueDateField.getText();

                Task newTask = new Task(description, dueDate);
                taskManager.addTask(newTask);
                taskListModel.addElement(newTask); // Add Task object to taskListModel
                clearInputFields();
                fileHandler.saveTasks(taskManager.getTasks()); // Save tasks after adding
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                    taskManager.deleteTask(selectedIndex);
                    fileHandler.saveTasks(taskManager.getTasks());
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task selectedTask = taskListModel.getElementAt(selectedIndex);
                    String newDescription = JOptionPane.showInputDialog("Enter new description:", selectedTask.getDescription());
                    String newDueDate = JOptionPane.showInputDialog("Enter new due date:", selectedTask.getDueDate());
                    Task updatedTask = new Task(newDescription, newDueDate);
                    taskManager.editTask(selectedIndex, updatedTask);
                    taskListModel.set(selectedIndex, updatedTask);
                    fileHandler.saveTasks(taskManager.getTasks());
                }
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(taskDescriptionField);
        inputPanel.add(dueDateField);
        add(inputPanel, BorderLayout.NORTH);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void clearInputFields() {
        taskDescriptionField.setText("");
        dueDateField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoGUI();
            }
        });
    }
}
