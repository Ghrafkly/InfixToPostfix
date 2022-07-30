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
}
