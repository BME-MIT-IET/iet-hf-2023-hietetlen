package Utils;

import java.util.LinkedList;

public class DefaultSubject<T> implements Subject<T> {

    private class ObserverData {
        public Observer<T> observer;
        public EventType eventType;
        public ObserverData(Observer<T> observer, EventType eventType) {
            this.observer = observer;
            this.eventType = eventType;
        }
    }

    protected LinkedList<ObserverData> observers = new LinkedList<>();

    @Override
    public void subscribe(Observer<T> observer, EventType eventType) throws IllegalArgumentException {
        if (observer == null) throw new IllegalArgumentException("The Observer cannot be null.");
        observers.add(new ObserverData(observer, eventType));
    }

    @Override
    public void unsubscribe(Observer<T> observer, EventType eventType) {
        ObserverData observerData = findObserved(observer, eventType);
        if (observerData == null) throw new IllegalArgumentException("This Observer isn't active.");
        observers.remove(observerData);
    }

    @Override
    public void notifyObservers(EventType eventType) {
        for (ObserverData od : observers) {
            if (od.eventType == eventType || od.eventType == EventType.Any){
                od.observer.getNotified(this, eventType);
            }
        }
    }

    private ObserverData findObserved (Observer<T> observer, EventType eventType) {
        ObserverData observerData = null;
        for (ObserverData od : observers) {
            if (od.observer == observer && od.eventType == eventType) observerData = od;
        }
        return observerData;
    }

}
