package buoproducertest;

public class CustomQueue<T> {
    private final int MAX_SIZE = 10;
    private T[] queue;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomQueue() {
        queue = (T[]) new Object[MAX_SIZE];
        front = 0;
        rear = -1;
        size = 0;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized boolean isFull() {
        return size == MAX_SIZE;
    }

    public synchronized void enqueue(T element) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        rear = (rear + 1) % MAX_SIZE;
        queue[rear] = element;
        size++;
        notifyAll();
    }

    public synchronized T dequeue() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        T element = queue[front];
        queue[front] = null;
        front = (front + 1) % MAX_SIZE;
        size--;
        notifyAll();
        return element;
    }

    public synchronized int getSize() {
        return size;
    }
    
    public synchronized T peek() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }

    public synchronized int getCapacity() {
        return MAX_SIZE;
    }
}