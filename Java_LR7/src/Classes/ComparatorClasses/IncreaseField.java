package Classes.ComparatorClasses;

import Classes.Interfaces.Content;
import java.util.Comparator;

public class IncreaseField<T extends Content> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return Double.compare(o1.getRating(), o2.getRating());
    }
}