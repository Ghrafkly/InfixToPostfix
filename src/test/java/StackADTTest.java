import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackADTTest {

    private StackADT stack;

    @BeforeEach
    void setUp() {
        stack = new StackADT();
    }

    @Test
    void test_push() {
        stack.push("1");
        assertEquals("1", stack.stackTop());
        assertEquals(1, stack.size());
    }

    @Test
    void test_pop() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        assertEquals("5", stack.pop());
        assertEquals("4", stack.pop());
        assertEquals("3", stack.pop());
        assertEquals("2", stack.pop());
        assertEquals("1", stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void test_stackTop() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        assertEquals("5", stack.stackTop());
        stack.pop();
        assertEquals("4", stack.stackTop());
        stack.pop();
        assertEquals("3", stack.stackTop());
        stack.pop();
        assertEquals("2", stack.stackTop());
        stack.pop();
        assertEquals("1", stack.stackTop());
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void test_size() {
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        assertEquals(5, stack.size());
        stack.pop();
        assertEquals(4, stack.size());
        stack.pop();
        assertEquals(3, stack.size());
        stack.pop();
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void test_isEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("1");
        assertFalse(stack.isEmpty());
    }
}