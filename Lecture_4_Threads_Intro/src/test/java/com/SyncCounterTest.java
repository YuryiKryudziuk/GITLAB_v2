package com;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static prepare.util.Util.threadSleep;

/**
 * Cover Thread-Sage approach
 * Race Condition - when two threads access the shared object in the memory at the same time competing for changing/reading it
 * IMPORTANT!!!
 *  <p><b>
 *     synchronized() - allows to guaranty sequence access from different threads and avoid Race Condition
 *  </b></p>
 */
public class SyncCounterTest {

    public static class CounterThread implements Runnable {

        private final String name;
        private final Counter counter;
        private final int total;

        public CounterThread(final String name, final Counter counter, int total) {
            this.name = name;
            this.counter = counter;
            this.total = total;
        }


        @Override
         public void run() {
            Thread.currentThread().setName(name);
            int i = 0;
            while (i < total) {
                i++;
                counter.met(name);
                threadSleep(1);

            }
        }
    }


    public static class Counter {

        private AtomicInteger counter;

        public Counter(AtomicInteger counter) {
            this.counter = counter;
        }

        public void inc() {
            counter.incrementAndGet();
        }

        public AtomicInteger getCounter(){
            return counter;
        }
        public synchronized void met(final String name){
            inc();
            System.out.println(name + "; counter = " + getCounter());
        }
    }


    /**
     * TODO: Fix the test and the code to make it Thread-Safe
     *
     * @throws InterruptedException
     */
    @Test
    public void  testSync() throws InterruptedException {

        final int total = 200;
        Counter counter = new Counter(new AtomicInteger(0));
        Thread thread1 = new Thread(new CounterThread("Thread - 1", counter, total));
        Thread thread2 = new Thread(new CounterThread("Thread - 2", counter, total));

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join(); // TODO?

        assertEquals(2 * total, counter.getCounter().longValue());
    }
}
