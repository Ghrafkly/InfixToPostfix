import java.util.Objects;

/**
 * Custom Stack implementation
 *
 * version 1.0
 * @author Kyle
 */
public class StackADT
{
    private StackNode top;
    private int size;

    public StackADT()
    {
        this.top = null;
        this.size = 0;
    }

    /**
     * Push value onto the stack
     *
     * @param val       value to push onto the stack
     */
    public void push(String val)
    {
        StackNode newNode = new StackNode(val);
        newNode.setNext(this.top);
        this.top = newNode;
        this.size++;
    }

    /**
     * Remove and return value from the top of the stack
     *
     * @return      value from the top of the stack
     */
    public String pop()
    {
        Objects.requireNonNull(this.top, "Stack is empty");
        String val = this.top.getVal();
        this.top = this.top.getNext();
        this.size--;
        return val;
    }

    /**
     * Return value from the top of the stack
     *
     * @return      value from the top of the stack
     */
    public String stackTop()
    {
        Objects.requireNonNull(this.top, "Stack is empty");
        return this.top.getVal();
    }

    /**
     * @return      size of the stack
     */
    public int size()
    {
        return this.size;
    }

    /**
     * @return      true if the stack is empty
     */
    public boolean isEmpty()
    {
        return this.size == 0;
    }

    /**
     * Prints the stack
     */
    public void print()
    {
        StackNode curr = this.top;
        while (curr != null)
        {
            System.out.printf("%s ", curr.getVal());
            curr = curr.getNext();
        }
        System.out.println();
    }

    /**
     * Converts the stack to a string
     *
     * @return      string representation of the stack
     */
    public String toString()
    {
        StackNode curr = this.top;
        StringBuilder sb = new StringBuilder();
        while (curr != null)
        {
            sb.append(curr.getVal());
            sb.append(" ");
            curr = curr.getNext();
        }
        return sb.toString();
    }
}

