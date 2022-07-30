public class StackNode {
    private final String val;
    private StackNode next;

    public StackNode(String val) {
        this.val = val;
        this.next = null;
    }

    public String getVal() {
        return val;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
