import java.util.Scanner;

public class RegistrationLoginApp {
    
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
        //user input
        System.out.println();
        System.out.println("       REGISTRATION AND LOGIN          ");
        System.out.println();
        
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter a username: ");
        String username = input.nextLine();

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        System.out.print("Enter your South African cell phone number: ");
        String cellPhoneNumber = input.nextLine();
        // Attributes from login
        Login user = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );
        
        System.out.println("\n         REGISTRATION           ");
        
        if (user.checkUserName()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        if (user.checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        if (user.checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }
        
         if (user.checkUserName()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.println("User successfully registered.");

            System.out.println("\n            LOGIN            ");

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            user.setLoginDetails(loginUsername, loginPassword);
            System.out.println(user.returnLoginStatus());
        } else {
            System.out.println("\nRegistration unsuccessful. Please correct the information above.");
        }

        input.close();
    
    }
}
