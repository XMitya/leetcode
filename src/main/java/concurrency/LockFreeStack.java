package concurrency;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {

    public static void main(String[] args) {
        LockFreeStack<String> stack = new LockFreeStack<>();

        for (int i = 0; i < 10; i++) {
            stack.push(String.valueOf(i));
        }

        while (true) {
            final Optional<String> value = stack.pop();
            if (value.isEmpty())
                break;
            System.out.println(value.get());
        }
    }
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    void push(T value) {
        if (value == null)
            throw new IllegalArgumentException("value cannot be null");

        Node<T> newHead;
        Node<T> currentHead;
        do {
            currentHead = head.get();
            newHead = new Node<>(value, currentHead);
        } while (!head.compareAndSet(currentHead, newHead));
    }

    Optional<T> pop() {
        Node<T> newHead;
        Node<T> currentHead;
        T value;
        do {
            currentHead = head.get();
            if (currentHead == null) {
                value = null;
                break;
            }
            value = currentHead.value();
            newHead = currentHead.next;
        } while (!head.compareAndSet(currentHead, newHead));

        return Optional.ofNullable(value);
    }

    private record Node<T>(T value, Node<T> next) {
    }
}
