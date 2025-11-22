package Classes;


import Classes.Interfaces.Content;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorClasses<T extends Content> implements Iterator<T> {
    private T[] arr;
    private int ind = 0;

    public IteratorClasses(T[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        if (ind < arr.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        if (hasNext()==false) throw new NoSuchElementException();
        return arr[ind++];
    }

    @Override
    public void remove(){
//        if (ind == 0) {
//            throw new IllegalStateException("Ошибка: курсор стоит в начале массива.");
//        }
        throw new UnsupportedOperationException();
    }
}
