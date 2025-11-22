package Classes.RunnableClasses;

import Classes.Interfaces.Content;
import Classes.Semaphore;

public class ThreadReadRun implements Runnable{
    private Content content;
    private Semaphore reader;
    private Semaphore writer;

    public ThreadReadRun(Content content, Semaphore reader, Semaphore writer){
        this.content = content;
        this.reader = reader;
        this.writer = writer;
    }


    @Override
    public void run() {
        try{
            int value = 0;
            for(int i = 0; i<content.length(); i++){
                reader.getPermission();
                value = content.getElement(i);
                System.out.println("Read: "+ value + " from position " + i);
                writer.release();
            }
            System.out.println("\nЧтение окончено.");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
