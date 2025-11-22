package Classes;

import Classes.Interfaces.Content;
import Exception.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Decorator implements Content {
    private final Content content;

    public Decorator(Content content) {
        this.content = content;
    }

    // Методы чтения - делегируем внутреннему объекту
    @Override
    public String getTitle() {
        return content.getTitle();
    }

    @Override
    public int getRating() {
        return content.getRating();
    }

    @Override
    public int getElement(int index) {
        return content.getElement(index);
    }

    @Override
    public int[] getArray() {
        int[] original = content.getArray();
        return original != null ? Arrays.copyOf(original, original.length) : null;
    }

    @Override
    public void output(OutputStream out) throws IOException {
        content.output(out);
    }

    @Override
    public void write(Writer out) throws IOException {
        content.write(out);
    }

    @Override
    public double calculateAverage() throws SeriesOperationException {
        return content.calculateAverage();
    }

    @Override
    public int length() {
        return content.length();
    }

    @Override
    public Iterator<Integer> iterator() {
        return content.iterator();
    }

    @Override
    public int compareTo(Content o) {
        return content.compareTo(o);
    }

    @Override
    public List<Integer> getElements(){
        return content.getElements();
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }

    @Override
    public void setRating(int rating) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }

    @Override
    public void setArray(int[] array) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }

    @Override
    public void setElement(int index, int value) {
        throw new UnsupportedOperationException("Объект нельзя изменять!");
    }
}
