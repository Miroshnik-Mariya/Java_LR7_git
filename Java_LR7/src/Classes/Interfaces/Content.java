package Classes.Interfaces;

import Exception.SeriesOperationException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;

public interface Content extends Serializable, Comparable<Content>, Iterable<Integer> {
    String getTitle();
    void setTitle(String title);

    int getRating();
    void setRating(int rating);

    int[] getArray();
    void setArray(int[] array);

    int getElement(int index);
    void setElement(int index, int value);

    //записи в байтовый поток
    void output(OutputStream out) throws IOException;

    //записи в символьный поток
    void write(Writer out) throws IOException;

    double calculateAverage() throws SeriesOperationException;
    List<Integer> getElements();
    int length();

    String toString();

    //String printInfo(int idx);
}