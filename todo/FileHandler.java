import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadTasks() {
        List<Task> loadedTasks = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof List<?>) {
                    loadedTasks.addAll((List<Task>) obj);
                }
            }
        } catch (EOFException e) {
            // End of file reached, ignore
        } catch (FileNotFoundException e) {
            // File not found, create a new one
            System.out.println("Creating a new tasks.dat file...");
            saveTasks(new ArrayList<>()); // Create a new file with an empty task list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedTasks;
    }
}
