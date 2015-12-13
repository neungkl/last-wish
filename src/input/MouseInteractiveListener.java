package input;

public interface MouseInteractiveListener<T> {
	public void onEnter(T object);
	public void onLeave(T object);
	public void onClick(T object);
}
