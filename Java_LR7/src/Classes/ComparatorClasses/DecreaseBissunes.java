package Classes.ComparatorClasses;

import Classes.Interfaces.Content;
import java.util.Comparator;

public class DecreaseBissunes<T extends Content> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        double value1 = o1.calculateAverage();
        double value2 = o2.calculateAverage();
        return Double.compare(value2, value1);
    }
}
