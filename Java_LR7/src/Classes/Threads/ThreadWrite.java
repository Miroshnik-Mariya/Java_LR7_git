package Classes.Threads;

import Classes.Interfaces.Content;
import Exception.InvalidDataException;

public class ThreadWrite extends Thread {
    private Content content;

    public ThreadWrite(Content content) {
        super();
        this.content = content;
    }

    @Override
    public void run() {
        try {
            System.out.println("Начало записи");
            for (int i = 0; i < content.length(); i++) {
                int value = 1 + (int) (Math.random() * 1000);
                content.setElement(i, value);
                System.out.println(" Write: " + value + " to position " + i);
            }
            System.out.println("Запись окончена.");
        } catch (InvalidDataException e) {
            System.out.println(getName() + " ошибка данных: " + e.getMessage());
        }
    }
}