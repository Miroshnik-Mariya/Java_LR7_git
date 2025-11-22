package Classes.Threads;

import Classes.Interfaces.Content;
import Exception.InvalidDataException;

public class ThreadRead extends Thread {
    private Content content;

    public ThreadRead(Content content) {
        super();
        this.content = content;
    }

    @Override
    public void run() {
        try {
            System.out.println("Начало чтения");
            for (int i = 0; i < content.length(); i++) {
                int value = content.getElement(i);
                System.out.println(" Read: " + value + " from position " + i);
            }

            System.out.println("Чтение завершено. Прочитано элементов: " + content.length());

        } catch (InvalidDataException e) {
            System.out.println(getName() + " ошибка данных: " + e.getMessage());
        }
    }
}