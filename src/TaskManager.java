import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    
    private final Path path = Path.of("tasks.json");

    private List<Tasks> tasks;

    public TaskManager(){
        this.tasks = loadTasks();
    }

    private List<Tasks> loadTasks(){
        
        List<Tasks> storedTasks = new ArrayList<>();

        if(!Files.exists(path)){
            return new ArrayList<>();
        }

        try{
            String content = Files.readString(path);
            String[] tasksArray = content.replace("[", "").replace("]", "").split("},");
            for (String taskJson : tasksArray) {
                if(!taskJson.endsWith("}")){
                    taskJson += "}";
                    storedTasks.add(Tasks.parseTaskFromJson(taskJson));
                }else{
                    storedTasks.add(Tasks.parseTaskFromJson(taskJson));
                }
            }
            
        }catch(IOException e){
            System.out.println("An error occurred while loading the file.");
            e.printStackTrace();
        }
        return storedTasks;
    }

    public void save() {

        StringBuilder sb = new StringBuilder();
        
        try (FileWriter file = new FileWriter(path.toString())) {
        
            sb.append("[\n");
        
            for (int i = 0; i < tasks.size(); i++) {
        
                sb.append(tasks.get(i).toJson());
        
                if(i  < tasks.size() - 1) {
                    sb.append(",");
                }
        
                sb.append("\n");
            }

            sb.append("]");

            file.write(sb.toString());

        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        Tasks task = new Tasks(description);
        tasks.add(task);
        System.out.println("Task added successfully "+ task.getId());
    }

    public void updateTask(String id, String description) {
        Tasks task = tasks.stream().filter(t -> t.getId() == Integer.parseInt(id)).findFirst().orElse(null);
        task.updateDescription(description);
        System.out.println("Task updated successfully "+ task.getId());
    }

    public void deleteTask(String id) {
        Tasks task = tasks.stream().filter(t -> t.getId() == Integer.parseInt(id)).findFirst().orElse(null);
        tasks.remove(task);
        System.out.println("Task deleted successfully "+ task.getId());
    }

    public void listTasks() {
        tasks.forEach(System.out::println);
    }

    public void markInProgress(String id) {
        Tasks task = tasks.stream().filter(t -> t.getId() == Integer.parseInt(id)).findFirst().orElse(null);
        task.markInProgress();
        System.out.println("Task marked in progress successfully "+ task.getId());
    }

    public void markDone(String id) {
        Tasks task = tasks.stream().filter(t -> t.getId() == Integer.parseInt(id)).findFirst().orElse(null);
        task.markDone();
        System.out.println("Task marked done successfully "+ task.getId());
    }

    public void listInProgress() {
        tasks.stream().filter(t -> t.getStatus() == Status.IN_PROGRESS).forEach(System.out::println);
    }

    public void listTodo() {
        tasks.stream().filter(t -> t.getStatus() == Status.TODO).forEach(System.out::println);
    }

    public void listDone() {
        tasks.stream().filter(t -> t.getStatus() == Status.DONE).forEach(System.out::println);
    }
}
