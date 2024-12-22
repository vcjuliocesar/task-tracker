import java.util.Scanner;

public class App {

    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String MARK_IN_PROGRESS = "mark-in-progress";
    private static final String MARK_DONE = "mark-done";
    private static final String LIST_DONE = "list-done";
    private static final String LIST_TODO = "list-todo";
    private static final String LIST_IN_PROGRESS = "list-in-progress";
    private static final String EXIT = "exit";

    public static void main(String[] args) throws Exception {

        TaskManager tasksmanager = new TaskManager();

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter a command: ");

                String options = scanner.nextLine().trim();

                if (options.isEmpty()) {
                    System.out.println("No command provided. Please try again.");
                    return;
                }

                if(options.equalsIgnoreCase(EXIT)){
                    System.out.println("Exiting...");
                    break;
                }

                String[] optionsArray = options.split(" ");

                if (optionsArray.length < 1) {
                    System.out.println("Invalid command format. Please try again.");
                    return;
                }

                String command = optionsArray[0].toLowerCase();

                switch (command) {
                    case ADD:
                        tasksmanager.addTask(optionsArray[1]);
                        break;
                    case UPDATE:

                        tasksmanager.updateTask(optionsArray[1], optionsArray[2]);
                        break;
                    case DELETE:

                        tasksmanager.deleteTask(optionsArray[1]);
                        break;
                    case LIST:
                        tasksmanager.listTasks();
                        break;

                    case MARK_IN_PROGRESS:
                        tasksmanager.markInProgress(optionsArray[1]);
                        break;
                    case MARK_DONE:
                        tasksmanager.markDone(optionsArray[1]);
                        break;
                    case LIST_DONE:
                        tasksmanager.listDone();
                        break;
                    case LIST_TODO:
                        tasksmanager.listTodo();
                        break;
                    case LIST_IN_PROGRESS:
                        tasksmanager.listInProgress();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
                tasksmanager.save();
            }

        } catch(Exception e){
            System.out.println("An error occurred. Please try again.");
            e.printStackTrace();
        }
    }
}
