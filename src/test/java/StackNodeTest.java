import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StackNodeTest {

    private StackNode stackNode;

    @BeforeEach
    void setUp() {
        stackNode = new StackNode("1");

    }

    @Test
    void test_StackNode() {
        assertEquals("1", stackNode.getVal());
        assertNull(stackNode.getNext());
    }

    @Test
    void test_setNext() {
        stackNode.setNext(new StackNode("2"));
        assertEquals("2", stackNode.getNext().getVal());
    }
}