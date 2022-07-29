public class QueueADT {
    private StackADT front, back;

    public QueueADT() {
        this.front = new StackADT();
        this.back = new StackADT();
    }

    public void enqueue(String val) {
        this.back.push(val);
    }

    public String dequeue() {
        if (this.front.isEmpty())
            while (!this.back.isEmpty())
                this.front.push(this.back.pop());
        return this.front.pop();
    }

    public String queueFront() {
        if (this.front.isEmpty())
            while (!this.back.isEmpty())
                this.front.push(this.back.pop());
        return this.front.stackTop();
    }

    public int size() {
        return this.front.size() + this.back.size();
    }

    public boolean isEmpty() {
        return this.front.isEmpty() && this.back.isEmpty();
    }

    public void print() {
        if (this.front.isEmpty()) {
            this.back.print();
        } else {
            this.front.print();
        }
    }

    public String toString() {
        return this.front.isEmpty() ? this.back.toString() : this.front.toString();
    }

    public static void main(String[] args) {
        QueueADT queue = new QueueADT();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.print();
        System.out.println("First dequeue: " + queue.dequeue());
        System.out.println("Second dequeue: " + queue.dequeue());
        System.out.println("Third dequeue: " + queue.dequeue());
        System.out.println("Fourth dequeue: " + queue.dequeue());
        System.out.println("Fifth dequeue: " + queue.dequeue());
        System.out.println("Queue is empty: " + queue.isEmpty());
    }
}
