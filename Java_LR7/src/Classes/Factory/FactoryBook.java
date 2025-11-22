package Classes.Factory;

import Classes.BooksSeries;
import Classes.Interfaces.Content;
import Classes.Interfaces.ContentFactory;
import Classes.Serial;

public class FactoryBook implements ContentFactory {

    @Override
    public Content createInstance() {
        return new BooksSeries("Default Serial", new int[]{1, 2, 3}, 5);
    }

    @Override
    public Content createInstance(String title, int[] array,int rating) {
        return new BooksSeries(title, array, rating);
    }
}
