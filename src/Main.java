package src;

import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Course Registration System (console-based UI).
 * This class displays a menu, accepts user input, and calls service classes
 * that interact with the PostgreSQL database.
 */
public class Main {

    public static void main(String[] args) {

        // Scanner is used to read user input from the console.
        Scanner scanner = new Scanner(System.in);

        // Service objects that contain database operations (JDBC calls inside these classes).
        CourseRegistrationService registrationService = new CourseRegistrationService();
        PaymentService paymentService = new PaymentService();
        StudentService studentService = new StudentService();

        // Controls when the application should keep running (loop continues until user chooses Exit).
        boolean running = true;

        // Main menu loop: keeps showing the menu after each operation until user exits.
        while (running) {
            System.out.println("\n==============================");
            System.out.println("Course Registration System");
            System.out.println("==============================");
            System.out.println("1. Register for a Course");
            System.out.println("2. Drop a Course");
            System.out.println("3. Make a Payment");
            System.out.println("4. View My Enrolled Courses");
            System.out.println("5. Add New Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;

            // Read the user's menu choice and validate it is a number.
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                // If user enters something that's not a number, prompt again.
                System.out.println("Invalid input. Please enter a number 1-4.");
                continue;
            }

            // Perform the operation based on the menu choice.
            switch (choice) {

                // 1) Register a student for a course
                case 1: {
                    System.out.print("Enter Student ID: ");
                    int studentId;

                    // Validate Student ID input as an integer
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    // Read course code (e.g., CIT3012)
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine().trim();

                    // Call the service method to register the course in the database
                    boolean ok = registrationService.registerCourse(studentId, courseCode);

                    // Display result
                    System.out.println(ok
                            ? "Course registered successfully."
                            : "Failed to register for the course.");
                    break;
                }

                // 2) Drop a student from a course
                case 2: {
                    System.out.print("Enter Student ID: ");
                    int studentId;

                    // Validate Student ID input
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    // Read course code
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine().trim();

                    // Call the service method to drop the course in the database
                    boolean ok = registrationService.dropCourse(studentId, courseCode);

                    // Display result
                    System.out.println(ok
                            ? "Course dropped successfully."
                            : "Failed to drop the course.");
                    break;
                }

                // 3) Process a payment for a student
                case 3: {
                    System.out.print("Enter Student ID: ");
                    int studentId;

                    // Validate Student ID input
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    // Read the payment amount
                    System.out.print("Enter Payment Amount: ");
                    double amount;

                    // Validate amount input as a number
                    try {
                        amount = Double.parseDouble(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid amount.");
                        break;
                    }

                    // Call the service method to insert a payment record in the database
                    boolean ok = paymentService.processPayment(studentId, amount);

                    // Display result
                    System.out.println(ok
                            ? "Payment processed successfully."
                            : "Failed to process the payment.");
                    break;
                }

                // 4) View courses a student is currently enrolled in
                case 4: {
                    System.out.print("Enter Student ID: ");
                    int studentId;

                    // Validate Student ID input
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    // Fetch the student's enrolled courses from the database
                    List<String> courses = registrationService.getStudentCourses(studentId);

                    // Display enrolled courses (or show message if none found)
                    if (courses.isEmpty()) {
                        System.out.println("No enrolled courses found for Student ID " + studentId + ".");
                    } else {
                        System.out.println("\nEnrolled Courses:");
                        for (String c : courses) {
                            System.out.println(" - " + c);
                        }
                    }
                    break;
                }

                // 5) Add a new student record into the database
                case 5: {
                    System.out.print("Enter Student ID (number): ");
                    int studentId;

                    // Validate Student ID input
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    // Collect student details
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine().trim();

                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine().trim();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();

                    System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine().trim();

                    // Enrollment date is also collected here (based on your current version of the code)
                    System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
                    String enrollDate = scanner.nextLine().trim();

                    // Insert student into the database using StudentService
                    boolean ok = studentService.addStudent(studentId, firstName, lastName, email, dob, enrollDate);

                    // Display result
                    System.out.println(ok
                            ? "Student added successfully."
                            : "Failed to add student.");
                    break;
                }

                // 6) Exit the program
                case 6:
                    running = false; // stops the while loop
                    System.out.println("Exiting system...");
                    break;

                // Any other number entered is invalid
                default:
                    System.out.println("Invalid choice. Please enter a number 1-6.");
            }
        }

        // Close scanner to free system resources
        scanner.close();
    }
}
