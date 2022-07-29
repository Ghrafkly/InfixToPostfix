public class StackNode {
    private String val;
    private StackNode next;

    public StackNode(String val) {
        this.val = val;
        this.next = null;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
