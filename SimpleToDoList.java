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
            System.out.println("No Tasks Yet!");
            return;
        }
        tasks.add(new Task(taskDesc));
        System.out.println("Task Added!");
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
    }
}