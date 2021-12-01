package com.company;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class BookingCalls2 {

    static long[][] generateData(int call_total) {
        LocalDateTime t1 = LocalDateTime.now();
        long p1 = t1.toEpochSecond(ZoneOffset.of("+2"));
        Random r = new Random(180);
        long[][] calls = new long[call_total][2];
        while (call_total-- > 0) {

            double nextRand = r.nextGaussian();
            while (Math.abs(nextRand = r.nextGaussian()) > 1) {
                ;
            }
            //System.out.println("Next rand " +  nextRand);
            long nextCall = Math.abs((long) (24 * 60 * 60 * nextRand));

            long k = p1 + nextCall;

            calls[call_total][0] = k;
            long callDuration = (long) (180 * r.nextGaussian());
            calls[call_total][1] = k + Math.abs(callDuration);
        }
        return Arrays.stream(calls).sorted(Comparator.comparingLong(x -> x[0])).toArray(size -> new long[size][2]);
    }


    static int maxLoadQueue(long[][] calls) {
        Queue<long[]> queue = new PriorityQueue<long[]>(Comparator.comparingLong(x -> x[1]));
        int max = 0;
        for (long[] call: calls) {
            if (!queue.isEmpty() && queue.peek()[1] < call[0]) {
                queue.poll();
            }
            queue.offer(call);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    static int maxLoadQueue2(long[][] calls) {

        long start = calls[0][0];
        long end = calls[calls.length - 1][1];
        int n = (int) (end - start);
        int[] arr = new int[n + 1];

        for (int i = 0; i < calls.length; i++) {
            int p = (int) (calls[i][0] - start);
            int q = (int) (calls[i][1] - start);
            arr[p]++;
            if (q + 1 <= n) {
                arr[q + 1]--;
            }
        }
        int x = 0;
        int max = 0;
        for (int i = 0; i <= n; i++) {
            x += arr[i];
            max = Math.max(max, x);
        }
        // for(int el:arr) System.out.print(el+" ");
        return max;
    }


    static int maxLoadQueue3(long[][] calls) {
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        for (long[] call : calls) {
            if(!pq.isEmpty() && pq.peek()[1] < call[0]) {
                pq.poll();
            }
            pq.offer(call);
        }
        return pq.size();
    }

    public static void main(String[] a) {
        long[][] calls = generateData(1000000);
        /*
        for(long[] cc : calls){
            System.out.println(Instant.ofEpochSecond(cc[0])+""+Instant.ofEpochSecond(cc[1]));
        }
        */
        long start1 = System.currentTimeMillis();
        System.out.println(maxLoadQueue(calls));
        long end1 = System.currentTimeMillis();
        System.out.println("Time elapsed " + (end1 - start1));

        long start2 = System.currentTimeMillis();
        System.out.println(maxLoadQueue2(calls));
        long end2 = System.currentTimeMillis();
        System.out.println("Time elapsed " + (end2 - start2));

        long start3 = System.currentTimeMillis();
        System.out.println(maxLoadQueue3(calls));
        long end3 = System.currentTimeMillis();
        System.out.println("Time elapsed " + (end3 - start3));

    }

}
