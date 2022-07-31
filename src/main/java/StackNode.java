/**
 * StackNode class. Contains a value and a pointer to the next node.
 *
 * version 1.0
 * @author Kyle
 */
public class StackNode {
    private final String val;
    private StackNode next;

    public StackNode(String val) {
        this.val = val;
        this.next = null;
    }

    /**
     * @return value of the node
     */
    public String getVal() {
        return val;
    }

    /**
     * @return pointer to the next node
     */
    public StackNode getNext() {
        return next;
    }

    /**
     * Set the pointer to the next node
     *
     * @param next pointer to the next node
     */
    public void setNext(StackNode next) {
        this.next = next;
    }
}
