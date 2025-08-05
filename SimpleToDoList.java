import java.util.*;

class Task {

    String description;

    Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

public class SimpleToDoList {

    static void addTasks(List<Task> tasks, Scanner scanner) {
        System.out.println("Enter Task: ");
        String taskDesc = scanner.nextLine().trim();
        if (taskDesc.isEmpty()) {
            System.out.println("Empty Task! Try Again.");
            return;
        }
        tasks.add(new Task(taskDesc));
        System.out.println("Task Added!");
    }

    static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No Tasks Yet!");
        } else {
            System.out.println("Your Tasks!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Add Task");
            System.out.println("2. View Task List");
            System.out.println("3. Exit");

            System.out.println();

            System.out.print("Enter Choice: ");
            String userChoice = scanner.nextLine().trim();

            System.out.println();

            switch (userChoice) {
                case "1":
                    addTasks(tasks, scanner);
                    break;
                case "2":
                    listTasks(tasks);
                    break;
                case "3":
                    System.out.println("Exited :)");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}

