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
    assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
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
    
    @Test
    public void testPrintMessages() {
        Message.resetAll();
        Message m = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        m.SentMessage(1);
        String expected = "Message ID: " + m.getMessageId()
                + "\nMessage Hash: " + m.getMessageHash()
                + "\nRecipient: +27718693002"
                + "\nMessage: Hi Mike, can you join us for dinner tonight?";
        assertEquals(expected, Message.printMessages());
    }
    
    @Test
public void testStoreMessage() throws Exception {
    Message.resetAll();

    java.nio.file.Files.deleteIfExists(
            java.nio.file.Path.of(Message.STORE_FILE)
    );

    Message m = new Message(1, "+27838884567",
            "Where are you? You are late! I have asked you to be on time.");

    assertEquals("Message successfully stored.", m.SentMessage(3));

    assertTrue(Message.displayMessageReport().contains(
            "Where are you? You are late! I have asked you to be on time."
    ));
}

@Test
public void testDisregardMessage() {
    Message.resetAll();

    Message m = new Message(2, "+27838884567",
            "Yohoooo, I am at your gate.");

    assertEquals("Message successfully disregarded.", m.SentMessage(2));
    assertEquals(0, Message.returnTotalMessagess());
}

@Test
public void testSearchMessageByID() {
    Message.resetAll();

    Message m = new Message(1, "+27838884567",
            "Where are you? You are late! I have asked you to be on time.");

    m.SentMessage(3);

    assertTrue(Message.searchMessageByID(m.getMessageId())
            .contains(m.getMessageId()));
}

@Test
public void testSearchMessagesByRecipient() {
    Message.resetAll();

    Message first = new Message(1, "+27838884567",
            "Where are you? You are late! I have asked you to be on time.");

    Message second = new Message(2, "+27838884567",
            "Ok, I am leaving without you.");

    first.SentMessage(3);
    second.SentMessage(3);

    String result = Message.searchMessagesByRecipient("+27838884567");

    assertTrue(result.contains(first.getMessageText()));
    assertTrue(result.contains(second.getMessageText()));
}

@Test
public void testDeleteMessageByHash() {
    Message.resetAll();

    Message m = new Message(1, "+27838884567",
            "Ok, I am leaving without you.");

    m.SentMessage(3);

    assertEquals("Message successfully deleted.",
            Message.deleteMessageByHash(m.getMessageHash()));

    assertEquals("Message hash not found.",
            Message.deleteMessageByHash(m.getMessageHash()));
}

@Test
public void testDisplayLongestMessage() {
    Message.resetAll();

    Message shortMessage = new Message(1, "+27838884567",
            "Hello.");

    Message longMessage = new Message(2, "+27838884567",
            "Where are you? You are late! I have asked you to be on time.");

    shortMessage.SentMessage(3);
    longMessage.SentMessage(3);

    assertTrue(Message.displayLongestMessage()
            .contains(longMessage.getMessageText()));
}

@Test
public void testDisplaySendersAndRecipients() {
    Message.resetAll();

    Message m = new Message(1, "+27838884567",
            "Ok, I am leaving without you.");

    m.SentMessage(3);

    String result = Message.displaySendersAndRecipients();

    assertTrue(result.contains("+27838884567"));
}

@Test
public void testDisplayMessageReport() {
    Message.resetAll();

    Message m = new Message(1, "+27838884567",
            "Ok, I am leaving without you.");

    m.SentMessage(3);

    assertTrue(Message.displayMessageReport()
            .contains(m.getMessageId()));

    assertTrue(Message.displayMessageReport()
            .contains(m.getMessageHash()));
}

@Test
public void testFiveMessages() throws Exception {

    Message.resetAll();

    java.nio.file.Files.deleteIfExists(
            java.nio.file.Path.of(Message.STORE_FILE)
    );

    Message first = new Message(0, "+27838884567",
            "Did you get the cake?");

    Message second = new Message(1, "+27838884567",
            "Where are you? You are late! I have asked you to be on time.");

    Message third = new Message(2, "+27838884567",
            "Yohoooo, I am at your gate.");

    Message fourth = new Message(3, "+27838884567",
            "It is dinner time !");

    Message fifth = new Message(4, "+27838884567",
            "Ok, I am leaving without you.");

    assertEquals("Message successfully sent.", first.SentMessage(1));

    assertEquals("Message successfully stored.", second.SentMessage(3));

    assertEquals("Message successfully disregarded.", third.SentMessage(2));

    assertEquals("Message successfully sent.", fourth.SentMessage(1));

    assertEquals("Message successfully stored.", fifth.SentMessage(3));

    String recipientResults =
            Message.searchMessagesByRecipient("+27838884567");

    assertTrue(recipientResults.contains(second.getMessageText()));
    assertTrue(recipientResults.contains(fifth.getMessageText()));

    assertFalse(recipientResults.contains(first.getMessageText()));
    assertFalse(recipientResults.contains(third.getMessageText()));
    assertFalse(recipientResults.contains(fourth.getMessageText()));

    assertEquals(2, countStoredMessagesInReport());
}
}