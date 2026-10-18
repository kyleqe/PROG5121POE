/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
import java.util.Scanner;
 
/**
 *
 * @author Kylen
 */
public class QuickChart {
    private static int processedCount = 0;
    
    public static void main(String[] args) {
        RegistrationLoginApp.main(args);
    }
    
    public static void start(Scanner input) {
        System.out.println("\nWelcome to QuickChat.");
        System.out.print("How many messages would you like to enter? ");
        int limit = readPositiveInteger(input);
        boolean running = true;
        while (running) {
            System.out.println("\nQUICKCHAT MENU");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option (1-3): ");
            switch (input.nextLine().trim()) {
                case "1": sendMessages(input, limit); break;
                case "2": System.out.println("Coming Soon."); break;
                case "3": running = false; System.out.println("Thank you for using QuickChat."); break;
                default: System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
        System.out.println("Total messages successfully sent: " + Message.returnTotalMessagess());
    }
    
    private static int readPositiveInteger(Scanner input) {
        while (true) {
            try {
                int n = Integer.parseInt(input.nextLine().trim());
                if (n > 0) return n;
                System.out.print("Enter a number greater than zero: ");
            } catch (NumberFormatException e) {
                System.out.print("Enter a whole number: ");
            }
        }
    }
    
    private static void sendMessages(Scanner input, int limit) {
        if (processedCount >= limit) {
            System.out.println("You have reached the message limit you selected.");
            return;
        }
        while (processedCount < limit) {
            int number = processedCount;
            System.out.println("\n--- Message " + (number + 1) + " of " + limit + " ---");
        }  
        System.out.print("Enter recipient in international format (example +27718693002): ");
            String recipient = input.nextLine().trim();
            Message check = new Message(number, recipient, "placeholder");
            String recipientStatus = check.checkRecipientCell();
            if (!recipientStatus.startsWith("Cell phone number successfully")) {
                System.out.println(recipientStatus);
                continue;
            }
    }
    
    
    
 
}
