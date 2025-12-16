package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationService registrationService = new CourseRegistrationService();

        System.out.println("Welcome to the Course Registration System");
        System.out.println("1. Register for a Course");
        System.out.println("2. Drop a Course");
        System.out.println("3. Make a Payment");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Handle course registration
                System.out.println("Enter Student ID: ");
                int studentId = scanner.nextInt();
                System.out.println("Enter Course Code: ");
                String courseCode = scanner.next();
                
                if (registrationService.registerCourse(studentId, courseCode)) {
                    System.out.println("Course registered successfully.");
                } else {
                    System.out.println("Failed to register for the course.");
                }
                break;

            case 2:
                // Handle course drop
                System.out.println("Enter Student ID: ");
                studentId = scanner.nextInt();
                System.out.println("Enter Course Code: ");
                courseCode = scanner.next();
                if (registrationService.dropCourse(studentId, courseCode)) {
                    System.out.println("Course dropped successfully.");
                } else {
                    System.out.println("Failed to drop the course.");
                }
                break;

            case 3:
                // Handle payment
                System.out.println("Enter Student ID: ");
                studentId = scanner.nextInt();
                System.out.println("Enter Payment Amount: ");
                double amount = scanner.nextDouble();
                PaymentService paymentService = new PaymentService();
                if (paymentService.processPayment(studentId, amount)) {
                    System.out.println("Payment processed successfully.");
                } else {
                    System.out.println("Failed to process the payment.");
                }
                break;

            case 4:
                System.out.println("Exiting system...");
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }

        scanner.close();
    }
}
