import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueADTTest {

    private QueueADT queue;

    @BeforeEach
    void setUp() {
        queue = new QueueADT();
    }

    @Test
    void test_enqueue() {
        queue.enqueue(1);
        assertEquals(1, queue.queueFront());
        assertEquals(1, queue.size());
    }

    @Test
    void test_dequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    void test_queueFront() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(1, queue.queueFront());
        queue.dequeue();
        assertEquals(2, queue.queueFront());
        queue.dequeue();
        assertEquals(3, queue.queueFront());
        queue.dequeue();
        assertEquals(4, queue.queueFront());
        queue.dequeue();
        assertEquals(5, queue.queueFront());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void test_size() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(5, queue.size());
        queue.dequeue();
        assertEquals(4, queue.size());
        queue.dequeue();
        assertEquals(3, queue.size());
        queue.dequeue();
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    void test_isEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }
}