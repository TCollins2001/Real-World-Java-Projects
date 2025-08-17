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

        static void viewClassAverage(List<Student> studentList) {
            System.out.println();

            if (studentList.isEmpty()) {
                System.out.println("No Students Or Grades Added Yet");
            } else {
                double sum = 0;
                for (Student student : studentList) {
                    sum += student.grades;
                }
                double classAverage = sum / studentList.size();
                System.out.printf("Class Average = %.2f%%", classAverage);

                System.out.println();
            }
        }

        static void viewHighestLowestGrades(List<Student> studentList) {
            System.out.println();

            if (studentList.isEmpty()) {
                System.out.println("No Students Or Grades Added Yet");
            } else {
                Student highestGrade = Collections.max(studentList, Comparator.comparingDouble(s -> s.grades));
                Student lowestGrade = Collections.min(studentList, Comparator.comparingDouble(s -> s.grades));

                System.out.println("Highest Grade = " + highestGrade);
                System.out.println();
                System.out.println("Lowest Grade = " + lowestGrade);
            }
        }

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Add Student");
            System.out.println("2. View Student List");
            System.out.println("3. View Class Average");
            System.out.println("4. View Highest And Lowest");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    addStudent(studentList, scanner);
                    break;
                case "2":
                    viewStudentList(studentList);
                    break;
                case "3":
                    viewClassAverage(studentList);
                    break;
                case "4":
                    viewHighestLowestGrades(studentList);
                    break;
                case "5":
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

*/