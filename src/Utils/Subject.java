package Utils;

public interface Subject<T> {
    void subscribe(Observer<T> observer, EventType eventType);
    void unsubscribe(Observer<T> observer, EventType eventType);
    void notifyObservers(EventType eventType);
}
