package task_3_6;

public interface SimpleQueue<T> {
    void add(T value);

    T remove() throws RuntimeException;

    T element() throws Exception;

    int count();

    default boolean empty() {
        return count() == 0;
    }
}