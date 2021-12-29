package thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDateTime;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application-bean.xml"})
@Slf4j
public class SchedulAtFixedRateTest {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static  final ScheduledExecutorService scheduledExecutorService = Executors
            .newSingleThreadScheduledExecutor(new ThreadFactoryImpl("FilterServiceScheduledThread"));

    public static void start(){
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int i = RandomUtils.nextInt(3);
//            if (i == 0){
//                log.error("error");
//                throw new RuntimeException("scheduleAtFixedRate exception ");
//            }
            log.info("run start threadName:{},threadId:{}",Thread.currentThread().getName(),Thread.currentThread().getId());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                log.error("e:{}",e.getMessage(),e);
            }
            log.info("run end threadName:{},threadId:{}",Thread.currentThread().getName(),Thread.currentThread().getId());
        }, 5000, 12000, TimeUnit.MILLISECONDS);
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
        SchedulAtFixedRateTest.start();
    }
}