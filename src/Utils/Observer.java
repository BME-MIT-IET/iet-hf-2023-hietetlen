package Utils;

public interface Observer<T> {
    void getNotified(Subject<T> subject, EventType eventType);
}
