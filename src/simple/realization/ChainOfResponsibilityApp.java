package simple.realization;

/**
 * Created by Artem on 21.06.16.
 */
public class ChainOfResponsibilityApp {
    public static void main(String[] args) {
        Logger smsLogger=new SmsLogger(Level.ERROR);
        Logger fileLogger=new FileLogger(Level.DEBUG);
        Logger emailLogger=new EmailLogger(Level.INFO);

        smsLogger.setNext(fileLogger);
        fileLogger.setNext(emailLogger);
        smsLogger.writeMessage("Everything is Ok",Level.INFO);
        smsLogger.writeMessage("Debug mode",Level.DEBUG);
        smsLogger.writeMessage("System has broken down",Level.ERROR);

    }
}

class Level{
    public static final int ERROR=1;
    public static final int DEBUG=2;
    public static final int INFO=3;
}

abstract class Logger{
    int priority;
    Logger next;

    public Logger(int priority) {
        this.priority = priority;
    }
    public void setNext(Logger next) {
        this.next = next;
    }
    public void writeMessage(String message, int level) {
        if(level<=priority){
            write(message);
        }
        if(next!=null){
            next.writeMessage(message,level);
        }
    }
    abstract void write(String message);
}

class SmsLogger extends Logger{

    public SmsLogger(int priority) {
        super(priority);
    }
    public void write(String message){
        System.out.println("SMS:"+message);
    }
}

class FileLogger extends Logger{

    public FileLogger(int priority) {
        super(priority);
    }
    public void write(String message){
        System.out.println("Write in file:"+message);
    }
}

class EmailLogger extends Logger{

    public EmailLogger(int priority) {
        super(priority);
    }
    public void write(String message){
        System.out.println("On mail:"+message);
    }
}