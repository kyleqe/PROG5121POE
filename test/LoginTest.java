import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    
     @Test
    public void testCorrectUsername() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(true, user.checkUserName());
    }
    
    @Test
    public void testIncorrectUsername() {
        Login user = new Login("Kyle", "Hove", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(false, user.checkUserName());
    }
    
    @Test
    public void testCorrectPassword() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(true, user.checkPasswordComplexity());
    }
    
     @Test
    public void testIncorrectPassword() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "password", "+27838968976");
        assertEquals(false, user.checkPasswordComplexity());
    }
    
    @Test
    public void testCorrectCellPhone() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(true, user.checkCellPhoneNumber());
    }
    
    @Test
    public void testIncorrectCellPhone() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "08966553");
        assertEquals(false, user.checkCellPhoneNumber());
    }
    
    @Test
    public void testSuccessfulLogin() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        user.setLoginDetails("kyl_1", "Ch&&sec@ke99!");
        assertTrue(user.loginUser());
    }
    
     @Test
    public void testFailedLogin() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        user.setLoginDetails("wrong", "wrongpassword");
        assertFalse(user.loginUser());
    }
    
    @Test
    public void testSuccessfulRegistration() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User successfully registered.", user.registerUser());
    }

     @Test
    public void testIncorrectUsernameMessage() {
        Login user = new Login("Kyle", "Hove", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                user.registerUser());
}
  
     @Test
    public void testIncorrectPasswordMessage(){
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "password", "+27838968976");
        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                user.registerUser());
    }
}
