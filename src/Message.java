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
    private static final List<Message> SENT_MESSAGES = new ArrayList<>();
    private static int totalSent = 0;
    
   private final String messageId;
   private final int messageNumber;
   private final String recipient;
   private final String messageText;
   private final String messageHash;

    /**
     * @param args the command line arguments
     */
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageId = createTenDigitId();
        this.messageHash = createMessageHash();
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
        return "Cell phone number is incorrectly formatted or does not contain an international code.Please try agian";
    }

     public String checkMessageLength() { 
        if (messageText == null) { 
            return "Please enter a message of less than 250 characters."; 
        } 
        if (messageText.length() <= 250) { 
            return "Message ready to send."; 
        } 
        return "Message exceeds 250 characters by " 
                + (messageText.length() - 250) + "; please reduce the size."; 
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
    
     public String SentMessage(int choice) { 
        switch (choice) {
            case 1:
                SENT_MESSAGES.add(this);
                totalSent++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                try {
                    storeMessage();
                    return "Message successfully stored.";
                } catch (IOException e) {
                    return "Could not store message: " + e.getMessage();
                }
            default:
                return "Invalid choice. Message was not processed.";
        } 
     }
     
     public String details() {
        return "Message ID: "+messageId
                +"\nMessage Hash: "+messageHash
                +"\nRecipient: "+recipient
                +"\nMessage: "+messageText;
    }
     
     public static String printMessages() {
        if (SENT_MESSAGES.isEmpty()) {
            return "No messages have been sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : SENT_MESSAGES) {
            sb.append(m.details()).append("\n\n");
        }
        return sb.toString().trim();
    }
     
     public int returnTotalMessagess() { 
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
        SENT_MESSAGES.clear();
        totalSent = 0;
    }   
       
    public String getMessageId(){return messageId;}
    public String getMessageHash(){return messageHash;}
    public String getRecipient(){return recipient;}
    public String getMessageText(){return messageText;}
           
}