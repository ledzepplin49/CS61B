package deque;

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    int size();
    T removeFirst();
    T removeLast();
    T get(int index);
    public void printDeque();
    default boolean isEmpty(){
        if(size()==0){
            return true;
        }
        return false;
    }
}
