package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application-bean.xml"})
@Slf4j
public class CatTest {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

//    @Test
    public static void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().isDaemon());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t:"+i);
            t.setDaemon(true);
            t.start();
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().isDaemon());

//        System.out.println(LocalDateTime.now().getHourOfDay() < 15);
//
//        print();
    }

    private static void p1(){
        print();
    }

    public static void print(){
        System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    private static  final ScheduledExecutorService scheduledExecutorService = Executors
            .newSingleThreadScheduledExecutor(new ThreadFactoryImpl("FilterServiceScheduledThread"));

    public static void start(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int i = RandomUtils.nextInt(3);
                if (i == 0){
                    throw new RuntimeException("scheduleAtFixedRate exception ");
                }
            }
        }, 20000, 12000, TimeUnit.MILLISECONDS);
    }
    static class ThreadFactoryImpl implements ThreadFactory {
        private final AtomicLong threadIndex = new AtomicLong(0);
        private final String threadNamePrefix;
        private final boolean daemon;

        public ThreadFactoryImpl(final String threadNamePrefix) {
            this(threadNamePrefix, false);
        }

        public ThreadFactoryImpl(final String threadNamePrefix, boolean daemon) {
            this.threadNamePrefix = threadNamePrefix;
            this.daemon = daemon;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, threadNamePrefix + this.threadIndex.incrementAndGet());
            thread.setDaemon(daemon);
            return thread;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int time = LocalDateTime.now().getHourOfDay();
        boolean am = time <= 12;
        boolean noon = time > 12 && time <= 17 ;
        boolean pm = time > 17;
        System.out.println(am);
        System.out.println(noon);
        System.out.println(pm);

        String str = "，,,..aaaBB11啊啊cc22";
        Pattern pattern = Pattern.compile("^([\\p{P}A-Za-z0-9\\u4e00-\\u9fa5])*$");
        Matcher match = pattern.matcher(str);
        System.out.println(match.matches());

        try {
            System.out.println(Integer.parseInt(","));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }




    }
}