package buoproducertest;

public class BuoProducer {
    private static volatile boolean producerFinished = false;
    private static final int TOTAL_PHONES = 20;

    public static void main(String[] args) {
        CustomQueue<String> queue = new CustomQueue();

        String[] inputs = {
            "800-TEST", "800-CALL", "800-HELLO", "800-CODE", "800-HOLLY",
            "800-JAVA", "800-SOFT", "800-TECH", "800-WORLD", "800-HELP",
            "800-LOVE", "800-WORK", "800-PLAY", "800-LEARN", "800-GROW",
            "800-BUO", "800-OPEN", "800-AI", "800-DATA", "800-CODE"
        };

        Thread producerBuo = new Thread(() -> {
            for (int i = 0; i < TOTAL_PHONES; i++) {
                try {
                    String textPhone = inputs[i % inputs.length];
                    if (i >= inputs.length) {
                        textPhone = textPhone + "-" + (i / inputs.length + 1);
                    }
                    
                    queue.enqueue(textPhone);
                    System.out.println("(Queue: " + queue.getSize() + "/10)" );
                    System.out.println(  "Producer: " + textPhone );
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(" Producer was disrupted");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            producerFinished = true;
        });

        Thread consumer = new Thread(() -> {
            int processedQueue = 0;
            while (!producerFinished || !queue.isEmpty()) {
                try {
                    String text = queue.dequeue();
                    if (text != null) {
                        String converted = PhoneNumberConverter.convertToPhoneNumber(text);
                        String phoneFormatted = PhoneNumberConverter.formatPhoneNumber(converted);
                        processedQueue++;
                        System.out.println("Consumer: " + text + " -- " + phoneFormatted + 
                                         " (Processed: " + processedQueue + ") ");
                    }
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("was disrupted");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        System.out.println("We Start app with " + TOTAL_PHONES + " phones");
        
        producerBuo.start();
        consumer.start();

        try {
            producerBuo.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Finished with success.");
    }
}