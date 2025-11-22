package Classes;

import Exception.*;

public class Semaphore {
    private int per;
    private int nextTicket = 0;
    private int curTicket = 0;


    public Semaphore(int per){
        if(per < 0){
            throw new InvalidDataException("Введено некорректное значение.");
        }
        this.per = per;
    }

    public synchronized void getPermission() throws InterruptedException {
        int ticket = nextTicket;
        nextTicket +=1;

        while (per<=0 || ticket!=curTicket){
            wait();
        }
        per -=1;
        curTicket +=1;
        notifyAll();
    }

    public synchronized void release(){
        per+=1;
        notifyAll();
    }

    public int getLengthQueue(){
        return Math.max(0, nextTicket-curTicket);
    }
}
