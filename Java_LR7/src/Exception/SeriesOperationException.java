package Exception;

public class SeriesOperationException extends RuntimeException {
    public SeriesOperationException(String message) {
        super("\n\nВнимание!\n"+message+"\n");
    }
}
