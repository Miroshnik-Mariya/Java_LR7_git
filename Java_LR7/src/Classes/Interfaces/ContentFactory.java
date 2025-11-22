package Classes.Interfaces;

public interface ContentFactory {
    Content createInstance();
    Content createInstance(String title, int[] array,int rating);
}
