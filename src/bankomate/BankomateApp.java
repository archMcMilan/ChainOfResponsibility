package bankomate;

/**
 * Created by Artem on 21.06.16.
 */
public class BankomateApp {
    public static void main(String[] args) {
        NoteModule note100=new NoteModule100();
        NoteModule note50=new NoteModule50();
        //NoteModule note20=new NoteModule20();
        NoteModule note10=new NoteModule10();

        note100.setNextMoneyModule(note50);
        //note50.setNextMoneyModule(note20);
        //note20.setNextMoneyModule(note10);
        note50.setNextMoneyModule(note10);
        note100.takeMoney(new Money(1_890));
    }
}

class Note{
    public static final int G10=10;
    public static final int G20=20;
    public static final int G50=50;
    public static final int G100=100;
}

class Money{
    private int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if(amount>0 && amount <2_000 && amount%Note.G10==0){
            this.amount=amount;
        }else{
            throw new RuntimeException("Illegal amount of money! It might be under 2 thousand and aliquot 10");
        }
    }
}

abstract class NoteModule{
    protected NoteModule next;
    abstract void takeMoney(Money money);
    void setNextMoneyModule(NoteModule module){
        next=module;
    }
}

class NoteModule100 extends NoteModule{
    void takeMoney(Money money){
        int countNote=money.getAmount()/Note.G100;
        int remind=money.getAmount()%Note.G100;
        if(countNote>0){
            System.out.println("Give:"+countNote+" note"+Note.G100);
        }
        if(remind>0 && next!=null){
            next.takeMoney(new Money(remind));
        }
    }
}

class NoteModule50 extends NoteModule{
    void takeMoney(Money money){
        int countNote=money.getAmount()/Note.G50;
        int remind=money.getAmount()%Note.G50;
        if(countNote>0){
            System.out.println("Give:"+countNote+" note"+Note.G50);
        }
        if(remind>0 && next!=null){
            next.takeMoney(new Money(remind));
        }
    }
}

class NoteModule20 extends NoteModule{
    void takeMoney(Money money){
        int countNote=money.getAmount()/Note.G20;
        int remind=money.getAmount()%Note.G20;
        if(countNote>0){
            System.out.println("Give:"+countNote+" note"+Note.G20);
        }
        if(remind>0 && next!=null){
            next.takeMoney(new Money(remind));
        }
    }
}

class NoteModule10 extends NoteModule{
    void takeMoney(Money money){
        int countNote=money.getAmount()/Note.G10;
        int remind=money.getAmount()%Note.G10;
        if(countNote>0){
            System.out.println("Give:"+countNote+" note"+Note.G10);
        }
        if(remind>0 && next!=null){
            next.takeMoney(new Money(remind));
        }
    }
}