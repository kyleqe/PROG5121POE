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
    
     @Test
    public void testMessageIDHasTenDigits() {
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals(10, m.getMessageId().length());
    }
    
    @Test
    public void testCorrectRecipient() {
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }
    
    @Test
    public void testIncorrectRecipient() {
        Message m = new Message(1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number incorrectly formatted or does not contain an international code.",
                m.checkRecipientCell());
    }
    
    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message sent", m.checkMessageLength());
    }
}
