package domain;

public interface Subject {

	public void notifyObservers(String message);

	public void addObserver(Observer observer);

	public void removeObserver(Observer observer);

	public boolean hasObserver(Observer observer);

}
