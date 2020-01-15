package uoc.ded.practica.model;

import java.util.Date;
import java.util.Objects;

public class Message {

    private Date date;
    private User sender;
    private User receiver = null;
    private String message;

    public Message(String message, User sender, Date date) {
        this.message = message;
        this.sender = sender;
        this.date = date;
    }

    public Message(String message, User sender, User receiver, Date date) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() { return receiver; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "date=" + date +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(date, message1.date) &&
                Objects.equals(sender, message1.sender) &&
                Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, sender, message);
    }
}
