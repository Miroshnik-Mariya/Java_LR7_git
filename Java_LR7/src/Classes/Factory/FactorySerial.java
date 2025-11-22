package Classes.Factory;

import Classes.Interfaces.Content;
import Classes.Interfaces.ContentFactory;
import Classes.Serial;

public class FactorySerial implements ContentFactory {

    @Override
    public Content createInstance() {
        return new Serial("Default Serial", new int[]{1, 2, 3}, 5);
    }

    @Override
    public Content createInstance(String title, int[] array,int rating) {
        return new Serial(title, array, rating);
    }

}
