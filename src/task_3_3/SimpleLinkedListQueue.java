package task_3_3;

public class SimpleLinkedListQueue<T> extends SimpleLinkedList<T> implements SimpleQueue<T> {
    @Override
    public void add(T value) {
        this.addLast(value);
    }

    @Override
    public T remove() throws RuntimeException {
        T result = this.element();
        this.removeFirst();
        return result;
    }

    @Override
    public T element() throws RuntimeException {
        if (this.empty()) {
            throw new RuntimeException("Queue is empty");
        }
        return this.getFirst();
    }

    @Override
    public int count() {
        return this.size();
    }
}
