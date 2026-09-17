public class LoginTest {
    
     @Test
    public void testCorrectUsername() {
        Login user = new Login("Kyle", "Hove", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals(true, user.checkUserName());
    }
    
    
    
}
