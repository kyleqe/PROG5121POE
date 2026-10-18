/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.util.UUID;
/**
 *
 * @author Kylen
 */
public class Message {
    
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
        if (recipient.matches("^\\+[1-9][0-9]{7,12}$")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number incorrectly formatted or does not contain an international code.";
    }

     public String checkMessageLength() { 
        if (messageText == null) { 
            return "Please enter a message."; 
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
    
     public String SentMessage() { 
         return "Choose Send Message, Disregard Message, or Store Message."; 
     }
     
     public String printMessages() {
        return "Message ID: "+messageId
                +"\nMessage Hash: "+messageHash
                +"\nRecipient: "+recipient
                +"\nMessage: "+messageText;
    }
     
     public int returnTotalMessagess() { 
         return 1; 
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
       
    public String getMessageId(){return messageId;}
    public String getMessageHash(){return messageHash;}
    public String getRecipient(){return recipient;}
    public String getMessageText(){return messageText;}
           
}