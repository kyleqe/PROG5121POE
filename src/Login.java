public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String loginUsername;
    private String loginPassword;
    
     public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }
     // checks user name 
      public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }
      // check password 
      public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int index = 0; index < password.length(); index++) {
            char character = password.charAt(index);

            if (Character.isUpperCase(character)) {
                hasCapitalLetter = true;
            } else if (Character.isDigit(character)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasCapitalLetter && hasNumber && hasSpecialCharacter;
      }
      //check phone number if it is a SA number
       public boolean checkCellPhoneNumber() {
        String phoneRegex = "^\\+27[0-9]{9}$";
        return cellPhoneNumber != null && cellPhoneNumber.matches(phoneRegex);
    }
       
       public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User successfully registered.";
    }

}