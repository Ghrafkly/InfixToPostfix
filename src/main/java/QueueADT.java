/**
 * Custom Queue implementation
 *
 * version 1.0
 * @author Kyle
 */
public class QueueADT {
    private final StackADT front, back;

    public QueueADT() {
        this.front = new StackADT();
        this.back = new StackADT();
    }

    /**
     * Adds value to the back of the queue.
     *
     * @param val value to add to the queue
     */
    public void enqueue(String val) {
        this.back.push(val);
    }

    /**
     * Removes and returns value from the front of the queue.
     *
     * @return value from the front of the queue
     */
    public String dequeue() {
        if (this.front.isEmpty())
            while (!this.back.isEmpty())
                this.front.push(this.back.pop());
        return this.front.pop();
    }

    /**
     * Returns the value at the front of the queue
     *
     * @return value at the front of the queue
     */
    public String queueFront() {
        if (this.front.isEmpty())
            while (!this.back.isEmpty())
                this.front.push(this.back.pop());
        return this.front.stackTop();
    }

    /**
     * @return size of the queue
     */
    public int size() {
        return this.front.size() + this.back.size();
    }

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return this.front.isEmpty() && this.back.isEmpty();
    }

    /**
     * Prints the queue
     */
    public void print() {
        if (this.front.isEmpty()) {
            this.back.print();
        } else {
            this.front.print();
        }
    }

    /**
     * Converts the queue to a string
     *
     * @return string representation of the queue
     */
    public String toString() {
        return this.front.isEmpty() ? this.back.toString() : this.front.toString();
    }
}
