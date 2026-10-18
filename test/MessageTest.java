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
    
    @Test
    public void testMessageLength250Characters() {
        String text = new String(new char[250]).replace('\0', 'a');
        Message m = new Message(0, "+27718693002", text);
        assertEquals("Message sent", m.checkMessageLength());
    }
    
    @Test
    public void testMessageLengthTooLong() {
        String text = new String(new char[251]).replace('\0', 'a');
        Message m = new Message(0, "+27718693002", text);
        assertEquals("Please enter a message of less than 250 characters.",
                m.checkMessageLength());
    }
    
    @Test
    public void testMessageHash() {
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        String expected = m.getMessageId().substring(0, 2) + ":0:HITONIGHT";
        assertEquals(expected, m.getMessageHash());
    }
    
     @Test
    public void testMessageHashFormat() {
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals(true, m.createMessageHash().matches("\\d{2}:0:HITONIGHT"));
    }
    
    @Test
    public void testMessageHashSecondMessage() {
        Message m = new Message(1, "+27718693002",
                "Hi Keegan, did you receive the payment?");
        assertEquals(true, m.getMessageHash().matches("\\d{2}:1:HIPAYMENT"));
    }
    
    @Test
    public void testSendMessage() {
        Message.resetAll();
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", m.SentMessage(1));
    }
    
    @Test
    public void testTotalMessagesSent() {
        Message.resetAll();
        new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?").SentMessage(1);
        assertEquals(1, Message.returnTotalMessagess());
    }
    
     @Test
    public void testDisregardedMessageNotCounted() {
        Message.resetAll();
        new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?").SentMessage(1);
        new Message(1, "+27718693002",
                "Hi Keegan, did you receive the payment?").SentMessage(2);
        assertEquals(1, Message.returnTotalMessagess());
    }
}
