package Classes;

import Classes.Interfaces.Content;
import Exception.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WrapperContent implements Content {
    private final Content wrappedContent;

    public WrapperContent(Content content) {
        this.wrappedContent = content;
    }

    @Override
    public synchronized String getTitle() {
        return wrappedContent.getTitle();
    }

    @Override
    public synchronized void setTitle(String title) {
        wrappedContent.setTitle(title);
    }

    @Override
    public synchronized int getRating() {
        return wrappedContent.getRating();
    }

    @Override
    public synchronized void setRating(int rating) {
        wrappedContent.setRating(rating);
    }

    @Override
    public synchronized int[] getArray() {
        int[] original = wrappedContent.getArray();
        return original != null ? original.clone() : new int[0];
    }

    @Override
    public synchronized void setArray(int[] array) {
        int[] copy = array != null ? array.clone() : new int[0];
        wrappedContent.setArray(copy);
    }

    @Override
    public synchronized int getElement(int index) {
        return wrappedContent.getElement(index);
    }

    @Override
    public synchronized void setElement(int index, int value) {
        wrappedContent.setElement(index, value);
    }

    @Override
    public synchronized void output(OutputStream out) throws IOException {
        wrappedContent.output(out);
    }

    @Override
    public synchronized void write(Writer out) throws IOException {
        wrappedContent.write(out);
    }

    @Override
    public synchronized double calculateAverage() throws SeriesOperationException {
        return wrappedContent.calculateAverage();
    }

    @Override
    public synchronized int hashCode() {
        return wrappedContent.hashCode();
    }

    public synchronized Content getWrappedContent() {
        return wrappedContent;
    }

    @Override
    public int length(){
        return wrappedContent.length();
    }

    //Лаборатораная работа №6
    @Override
    public int compareTo(Content v2) {
        if(this.calculateAverage()>v2.calculateAverage()){
            return 1;
        }
        else if(this.calculateAverage()==v2.calculateAverage()){
            return 0;
        }
        else{return -1;}
    }

    //Лабораторная работа №6 - Задание 3
    public List<Integer> getElements() {
        List<Integer> list = new ArrayList<>();
        for (int i : wrappedContent.getArray()) {
            list.add(i);
        }
        return list;
    }
    //    @Override
//    public Iterator<Integer> iterator() {
//        return new IteratorClasses(this.episodesSeason);
//    }
    @Override
    public Iterator<Integer> iterator() {
        return getElements().iterator(); // базовая реализация на основе списка
    }}