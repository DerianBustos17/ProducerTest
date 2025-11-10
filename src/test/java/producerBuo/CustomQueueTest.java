package producerBuo;

import buoproducertest.CustomQueue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomQueueTest {

    @Test
    void testEnqueueAndDequeue() throws InterruptedException {
        CustomQueue<String> queue = new CustomQueue<>();
        
        queue.enqueue("Test01");
        queue.enqueue("Test02");

        assertEquals(2, queue.getSize());
        assertEquals("Test01", queue.dequeue());
        assertEquals(1, queue.getSize());
        assertEquals("Test02", queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    @Test
    void testIsFull() throws InterruptedException {
        CustomQueue<Integer> queue = new CustomQueue<>();
        for (int i = 0; i < 10; i++) queue.enqueue(i);
        assertTrue(queue.isFull());
    }

    @Test
    void testCapacityConstant() {
        CustomQueue<String> queue = new CustomQueue<>();
        assertEquals(10, queue.getCapacity());
    }
    
        @Test
    void testPeek() {
        CustomQueue<Integer> queue = new CustomQueue<>();
        queue.enqueue(5);
        queue.enqueue(10);
        assertEquals(5, queue.peek());
        assertEquals(2, queue.getSize());
    }

}