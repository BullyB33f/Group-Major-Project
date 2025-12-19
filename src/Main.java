package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CourseRegistrationService registrationService = new CourseRegistrationService();
        PaymentService paymentService = new PaymentService();

        boolean running = true;

        while (running) {
            System.out.println("\n==============================");
            System.out.println("Course Registration System");
            System.out.println("==============================");
            System.out.println("1. Register for a Course");
            System.out.println("2. Drop a Course");
            System.out.println("3. Make a Payment");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number 1-4.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter Student ID: ");
                    int studentId;
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine().trim();

                    boolean ok = registrationService.registerCourse(studentId, courseCode);
                    System.out.println(ok ? "Course registered successfully." : "Failed to register for the course.");
                    break;
                }

                case 2: {
                    System.out.print("Enter Student ID: ");
                    int studentId;
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine().trim();

                    boolean ok = registrationService.dropCourse(studentId, courseCode);
                    System.out.println(ok ? "Course dropped successfully." : "Failed to drop the course.");
                    break;
                }

                case 3: {
                    System.out.print("Enter Student ID: ");
                    int studentId;
                    try {
                        studentId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid Student ID.");
                        break;
                    }

                    System.out.print("Enter Payment Amount: ");
                    double amount;
                    try {
                        amount = Double.parseDouble(scanner.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid amount.");
                        break;
                    }

                    boolean ok = paymentService.processPayment(studentId, amount);
                    System.out.println(ok ? "Payment processed successfully." : "Failed to process the payment.");
                    break;
                }

                case 4:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number 1-4.");
            }
        }

        scanner.close();
    }
}
