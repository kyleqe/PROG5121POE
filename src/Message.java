/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author Kylen
 */
public class Message {
    
    public static final String STORE_FILE = "messages.json";
    private static final int MAX_MESSAGES = 100;

    private static Message[] sentMessages = new Message[MAX_MESSAGES];
    private static Message[] disregardedMessages = new Message[MAX_MESSAGES];
    private static Message[] storedMessages = new Message[MAX_MESSAGES];

    private static String[] messageHashes = new String[MAX_MESSAGES];
    private static String[] messageIDs = new String[MAX_MESSAGES];

    private static int sentCount = 0;
    private static int disregardedCount = 0;
    private static int storedCount = 0;
    private static int totalSent = 0;
    private static int allMessageCount = 0;
    
   private final String messageId;
   private final int messageNumber;
   private final String recipient;
   private final String messageText;
   private final String messageHash;

    /**
     * @param args the command line arguments
     */
    public Message(int messageNumber, String recipient, String messageText) {
        this(messageNumber, recipient, messageText, createTenDigitId(), null);
    }
    
    private Message(int messageNumber, String recipient, String messageText,
                String messageId, String messageHash) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageId = createTenDigitId();
        
        if  (messageHash == null || messageHash.isEmpty()) {
        this.messageHash = createMessageHash();
        } else {
            this.messageHash = messageHash;
        }
    }
    
    private String createTenDigitId(){
       StringBuilder digits = new StringBuilder();
       while (digits.length()< 10)
           digits.append(UUID.randomUUID().toString().replaceAll("[^0-9]",""));
       return digits.substring(0, 10);
    }
    public boolean checkMessageID() { 
        return messageId.length() <= 10; 
    }
    
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+[1-9][0-9]{7,12}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

         public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message sent";
        }
        return "Please enter a message of less than 250 characters.";
    }
    
     public String createMessageHash() { 
        String[] words = messageText.trim().split("\\s+"); 
        String firstWord = cleanWord(words[0]); 
        String lastWord = cleanWord(words[words.length - 1]); 
        return messageId.substring(0, 2) + ":" + messageNumber + ":" 
                + (firstWord + lastWord).toUpperCase(); 
    } 

    private String cleanWord(String word) {
        return word.replaceAll("[^A-Za-z0-9]", ""); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String details() {
    return "Message ID: " + messageId
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipient
            + "\nMessage: " + messageText;
}
    
     public String SentMessage(int choice) { 
       switch (choice) {
        case 1:
            if (sentCount < MAX_MESSAGES) {
                sentMessages[sentCount++] = this;
                totalSent++;
                addMessageDetails();
                return "Message successfully sent.";
            }
            return "Sent message array is full.";

        case 2:
            if (disregardedCount < MAX_MESSAGES) {
                disregardedMessages[disregardedCount++] = this;
                addMessageDetails();
                return "Message successfully disregarded.";
            }
            return "Disregarded message array is full.";

        case 3:
    if (storedCount >= MAX_MESSAGES) {
        return "Stored message array is full.";
    }

    try {
        storeMessage();
        storedMessages[storedCount++] = this;
        addMessageDetails();

        return "Message successfully stored.";

    } catch (IOException e) {
        return "Could not store message: " + e.getMessage();
    }
        default:
            return "Invalid choice. Message was not processed.";
    }
   }

     private void addMessageDetails() {
    if (allMessageCount < MAX_MESSAGES) {
        messageHashes[allMessageCount] = messageHash;
        messageIDs[allMessageCount] = messageId;
        allMessageCount++;
    }
}
     
     public static String printMessages() {
        if (sentCount == 0) {
        return "No messages have been sent yet.";
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < sentCount; i++) {
        sb.append(sentMessages[i].details()).append("\n\n");
    }

    return sb.toString().trim();
}
     
     public static int returnTotalMessagess() {
    return totalSent;
}
     
     public void storeMessage() throws IOException {
        Path path = Path.of(STORE_FILE);
        String old = Files.exists(path) ? Files.readString(path, StandardCharsets.UTF_8).trim() : "[]";
        if (old.isEmpty() || old.equals("[]")) {
            old = "[" + toJson() + "]";
        } else {
            int end = old.lastIndexOf(']');
            if (end < 0) throw new IOException("Existing " + STORE_FILE + " is not a JSON array.");
            String body = old.substring(0, end).trim();
            old = body.equals("[") ? body + toJson() + "]" : body + "," + toJson() + "]";
        }
        Files.writeString(path, old, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
     
      public String toJson() {
        return "{\"messageId\":\"" + esc(messageId)
            + "\",\"messageHash\":\"" + esc(messageHash)
            + "\",\"recipient\":\"" + esc(recipient)
            + "\",\"message\":\"" + esc(messageText) 
            + "\"}";
    }
     
       private String esc(String s) {
        if (s == null) 
            return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
    public static void resetAll() {
        sentMessages = new Message[MAX_MESSAGES];
        disregardedMessages = new Message[MAX_MESSAGES];
        storedMessages = new Message[MAX_MESSAGES];

        messageHashes = new String[MAX_MESSAGES];
        messageIDs = new String[MAX_MESSAGES];

        sentCount = 0;
        disregardedCount = 0;
        storedCount = 0;
        totalSent = 0;
        allMessageCount = 0;
    }   
       
    public String getMessageId(){return messageId;}
    public String getMessageHash(){return messageHash;}
    public String getRecipient(){return recipient;}
    public String getMessageText(){return messageText;}
    
    public static String displaySendersAndRecipients() {

    if (storedCount == 0) {
        return "No stored messages available.";
    }

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < storedCount; i++) {
        result.append("Sender: Not recorded")
              .append("\nRecipient: ")
              .append(storedMessages[i].getRecipient())
              .append("\n\n");
    }

    return result.toString();
}
    
    public static String displayLongestMessage() {

    if (storedCount == 0) {
        return "No stored messages available.";
    }

    Message longest = storedMessages[0];

    for (int i = 1; i < storedCount; i++) {

        if (storedMessages[i].getMessageText().length()
                > longest.getMessageText().length()) {

            longest = storedMessages[i];
        }
    }

    return "Longest stored message:\n"
            + longest.getMessageText();
}
    
    public static String searchMessageByID(String searchID) {

    for (int i = 0; i < storedCount; i++) {

        if (storedMessages[i].getMessageId().equals(searchID)) {
            return storedMessages[i].details();
        }
    }

    return "Message ID not found.";
}
    
    public static String deleteMessageByHash(String hash) {

    for (int i = 0; i < storedCount; i++) {

        if (storedMessages[i].getMessageHash().equals(hash)) {

            // Remove the message from the stored array
            for (int j = i; j < storedCount - 1; j++) {
                storedMessages[j] = storedMessages[j + 1];
            }

            storedMessages[storedCount - 1] = null;
            storedCount--;

            return "Message successfully deleted.";
        }
    }

    return "Message hash not found.";
}
    
    public static String displayMessageReport() {

    if (storedCount == 0) {
        return "No stored messages available.";
    }

    StringBuilder report = new StringBuilder();

    report.append("FULL STORED MESSAGE REPORT\n");
    report.append("___________________________\n");

    for (int i = 0; i < storedCount; i++) {

        report.append("\nMessage ")
              .append(i + 1)
              .append("\n");

        report.append(storedMessages[i].details());
        report.append("\n--------------------------\n");
    }

    return report.toString();
}
      
    public static String searchMessagesByRecipient(String recipient) {

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < storedCount; i++) {

        if (storedMessages[i].getRecipient().equals(recipient)) {
            result.append(storedMessages[i].details())
                  .append("\n\n");
        }
    }

    if (result.length() == 0) {
        return "No messages found for this recipient.";
    }

    return result.toString();
}
}