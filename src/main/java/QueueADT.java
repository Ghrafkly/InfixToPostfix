public class QueueADT {
    // queue implementation using linked list using StackADT
    private StackADT front;
    private StackADT back;

    public QueueADT() {
        this.front = new StackADT();
        this.back = new StackADT();
    }

    public void enqueue(int val) {
        this.back.push(val);
    }

    public int dequeue() {
        while (!this.back.isEmpty()) {
            int val = this.back.pop();
            this.front.push(val);
        }

        int val = this.front.pop();

        while (!this.front.isEmpty()) {
            int val2 = this.front.pop();
            this.back.push(val2);
        }

        return val;
    }

    public int queueFront() {
        while (!this.back.isEmpty()) {
            int val = this.back.pop();
            this.front.push(val);
        }

        int val = this.front.stackTop();

        while (!this.front.isEmpty()) {
            int val2 = this.front.pop();
            this.back.push(val2);
        }

        return val;
    }

    public int size() {
        return this.front.size() + this.back.size();
    }

    public boolean isEmpty() {
        return this.front.isEmpty() && this.back.isEmpty();
    }

    public void print() {
        if (this.front.isEmpty())
            this.back.print();
        else
            this.front.print();
    }
}
