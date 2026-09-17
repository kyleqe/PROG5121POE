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
}
