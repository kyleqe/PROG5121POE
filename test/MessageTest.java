/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Kylen
 */
public class MessageTest {
    
    @Test
    public void testMessageIDCreated() {
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals(true, m.checkMessageID());
    }
}
