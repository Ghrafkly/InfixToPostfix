import java.util.Objects;

public class StackADT {
    private StackNode top;
    private int size;

    public StackADT() {
        this.top = null;
        this.size = 0;
    }

    public void push(int val) {
        StackNode newNode = new StackNode(val);
        newNode.setNext(this.top);
        this.top = newNode;
        this.size++;
    }

    public int pop() {
        Objects.requireNonNull(this.top, "Stack is empty");
        int val = this.top.getVal();
        this.top = this.top.getNext();
        this.size--;
        return val;
    }

    public int stackTop() {
        Objects.requireNonNull(this.top, "Stack is empty");
        return this.top.getVal();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void print() {
        StackNode curr = this.top;
        while (curr != null) {
            System.out.printf("%d ", curr.getVal());
            curr = curr.getNext();
        }
        System.out.println();
    }
}

