import java.util.*;

class Student {

    String name;
    double grades;

    Student(String name, double grades) {
        this.name = name;
        this.grades = grades;
    }

    @Override
    public String toString() {
        return name + " - " + grades + "%";
    }
}

public class StudentTracker {

    static void viewStudentList(List<Student> studentList) {
        System.out.println();
        if (studentList.isEmpty()) {
            System.out.println("No Students Added Yet.");
        } else {
            System.out.println("Student List");
            System.out.println("-------------");
            studentList.sort(Comparator.comparing(s -> s.name));
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println((i + 1) + ". " + studentList.get(i));
            }
        }
    }

    static void addStudent(List<Student> studentList, Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.print("Enter Student Name: ");
            String userStudentNames = scanner.nextLine().trim();

            System.out.println();

            System.out.print("Enter Student Grade: ");
            String index = scanner.nextLine().trim();

            try {
                double userGrades = Double.parseDouble(index);

                if (!userStudentNames.isEmpty()) {
                    studentList.add(new Student(userStudentNames, userGrades));
                    System.out.println();
                    System.out.println("Student Name & Grade Added Successfully!");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("Entry Empty. Try Again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entry Not A Number. Try Again.");
            }

                System.out.println();
                System.out.print("Do You Want To Add Another Name? (Y/N): ");
                char userYN = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();

                if (userYN != 'Y') {
                    return;
                }
            }
        }

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. View Student List");
            System.out.println("2. Add Student");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Enter Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    viewStudentList(studentList);
                    break;
                case "2":
                    addStudent(studentList, scanner);
                    break;
                case "3":
                    System.out.println();
                    System.out.println("Exited :)");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}



/*

Student Grade Tracker:

Store students’ names and grades.

Calculate average, highest, and lowest grade.

Optional: Letter grades (A, B, C…).

Concepts: arrays/lists, loops, calculations.

*/