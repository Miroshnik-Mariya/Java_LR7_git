package Classes.RunnableClasses;
import Classes.Semaphore;
import Classes.Interfaces.Content;

public class ThreadWriteRun implements Runnable{
    private Content content;
    private Semaphore writer;
    private Semaphore reader;

    public ThreadWriteRun(Content content, Semaphore writer, Semaphore reader){
        super();
        this.content = content;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            for (int i = 0; i < content.length(); i++) {
                writer.getPermission(); //получаем/ожидаем разрешение
                value = 1+(int)(Math.random()*1000);
                content.setElement(i,value);
                System.out.println("Write: " + value + " to position "+i);
                reader.release();
            }
            System.out.println("\nЗапись окончена.");
        }
        catch (InterruptedException e) {
                throw new RuntimeException(e);
        }
    }
}
