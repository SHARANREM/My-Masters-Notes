class Queue<T> {
    T[] queue;
    int front = 0;
    int rear = -1;

    Queue(int size) {
        queue = (T[]) new Object[size];
    }

    // Enqueue
    void enqueue(T item) {
        if (rear == queue.length - 1)
            System.out.println("Queue is full");
        else
            queue[++rear] = item;
    }

    // Dequeue
    T dequeue() {
        if (front > rear) {
            System.out.println("Queue is empty");
            return null;
        }

        return queue[front++];
    }

    // Display
    void display() {
        for (int i = front; i <= rear; i++)
            System.out.println(queue[i]);
    }
}

public class Queue4 {
    public static void main(String[] args) {

        Queue<Integer> q = new Queue<>(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Queue:");
        q.display();

        System.out.println("Deleted: " + q.dequeue());

        System.out.println("Queue after deletion:");
        q.display();
    }
}
