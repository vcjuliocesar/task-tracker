import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tasks {
    private static int lastId = 0;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Tasks(String description, Status status) {
        this.id = ++lastId;
        this.description = description;
        this.status = (status == null) ? Status.TODO : status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Tasks(String description){
        this(description, null);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }   

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    public String toJson() {
        return "{ \"id\": " + id + ", \"description\": \"" + description.strip() + "\", \"status\": \"" + status.toString() + "\", \"createdAt\": \"" + createdAt.format(formatter) + "\", \"updatedAt\": \"" + updatedAt.format(formatter) + "\" }";
    }

    @Override
    public String toString() {
        return "id: " +id + " description: " + description.strip() + " status: " + status.toString() + " Created At: " + createdAt.format(formatter) + " Updated At: " + updatedAt.format(formatter);
    }

    public static Tasks parseTaskFromJson(String json) {
       json = json.replace("{", "").replace("}", "").replace("\"", "");
       String[] jsonParts = json.split(",");

       String id = jsonParts[0].split(":")[1].strip();
       String description = jsonParts[1].split(":")[1].strip();
       String status = jsonParts[2].split(":")[1].strip();
       String createdAt = jsonParts[3].split("[a-z]:")[1].strip();
       String updatedAt = jsonParts[4].split("[a-z]:")[1].strip();
       Status statusEnum = Status.valueOf(status.toUpperCase().replace(" ", "_"));

       Tasks task = new Tasks(description, statusEnum);
       task.id = Integer.parseInt(id);
       task.createdAt = LocalDateTime.parse(createdAt, formatter);
       task.updatedAt = LocalDateTime.parse(updatedAt, formatter);

       if(task.id > lastId) {
           lastId = task.id;
       }

       return task;

    } 

}
