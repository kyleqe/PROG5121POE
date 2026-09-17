import java.util.Scanner

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

    }
}
