package java8;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author lwk
 * @date 2019-07-01 09:06
 */
public class CommonTests {
    /**
     * 流模式，传统模式差异比较
     */
    public static void compareForAndStream() {
        //p1表示for性能,p2表示流处理性能
        long p1 = 0, p2 = 0;
        int n = 500000;
        ArrayList<Integer> arr = Lists.newArrayList();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < n; i++) {
                arr.add(i);
            }
            Integer sum = 0;
            long t1 = System.nanoTime();
            for (Integer v : arr) {
                sum = sum + v;
            }
            long t2 = System.nanoTime();
            arr.stream().reduce(0, Integer::sum);
            //arr.stream().parallel().reduce(0, (a, b) -> a + b);
            long t3 = System.nanoTime();
            p1 += (t2 - t1);
            p2 += (t3 - t2);
            arr.clear();
        }
        System.out.println(p1 / 100 / 1000);
        System.out.println(p2 / 100 / 1000);
    }

    public static void main(String[] args) {
//        compareForAndStream();

//        List<Integer> list = Arrays.asList(10, 101010101, 1);
//        Integer total = list.parallelStream().reduce(Integer::sum).orElse(0);
//        System.out.println(total);
//        List<Integer> sublist = list.parallelStream().filter(o -> o > 10).collect(Collectors.toList());
//        System.out.println(sublist);


    }

}


