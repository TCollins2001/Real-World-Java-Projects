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

    static void removeTasks(List<Task> tasks, Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No Tasks To Remove!");
            return;
        }

        listTasks(tasks);
        System.out.println();
        System.out.println("Enter Task Number You Want To Remove: ");
        String removedInput = scanner.nextLine().trim();

        /* convert string to index */

        int index;

        try {
            index = Integer.parseInt(removedInput) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entry Was Not A Number. Try Again.");
            return;
        }

        if (index < 0 || index > tasks.size()) {
            System.out.println("Number Not On Task List. Try Again.");
            return;
        }

        Task removed = tasks.remove(index);
        System.out.println("Task \"" + removed + "\" Removed!");
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Add Task");
            System.out.println("2. View Task List");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");

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
                    removeTasks(tasks, scanner);
                    break;
                case "4":
                    System.out.println("Exited :)");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}

