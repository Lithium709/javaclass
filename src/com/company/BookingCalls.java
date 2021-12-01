package com.company;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingCalls {

    static long[][] generateData(int call_total){
        LocalDateTime t1 = LocalDateTime.now();
        long p1 = t1.toEpochSecond(ZoneOffset.of("+2"));
        Random r = new Random(180);
        long[][] calls = new long[call_total][2];
        while (call_total-- > 0) {

            double nextRand = r.nextGaussian();
            while (Math.abs(nextRand = r.nextGaussian())>1);
            //System.out.println("Next rand " +  nextRand);
            long nextCall = Math.abs((long) (24 * 60 * 60 * nextRand));

            long k = p1 + nextCall;

            calls[call_total][0] = k;
            long callDuration = (long) (180 * r.nextGaussian());
            calls[call_total][1] = k + Math.abs(callDuration);
        }
        return  Arrays.stream(calls).sorted(Comparator.comparingLong(x->x[0])).toArray( size->new long[size][2]);
    }


    static int maxLoadQueue(long[][] calls){
        Queue<long[]> queue = new PriorityQueue<long[]>(Comparator.comparingLong(x->x[1]));
        int max = 0;
        for(int i = 0; i <calls.length;i++){
            long[]x=calls[i];
            long callStart = x[0];
            long callEnd   = x[1];
            // clearing finished calls
            while(queue.size()>0 && queue.peek()[1]<callStart){
                queue.poll();
            }
            queue.offer(x);
            max =  Math.max(max,  queue.size());
        }
        return max;
    }


    public static void main(String[] a) {
        long[][] calls = generateData(10000);
        /*
        for (long[] call : calls) {
            System.out.println(LocalDateTime.ofEpochSecond(call[0],0,ZoneOffset.of("+2"))
                    + ":" + LocalDateTime.ofEpochSecond(call[1],0,ZoneOffset.of("+2")) + " call length: " + (call[1]-call[0]));
        }
        */
        System.out.println(maxLoadQueue(calls));
        Queue<Integer> q = new PriorityQueue<>();
        q.add(5);
        q.add(7);
        q.add(28);


        System.out.println(q.poll());
        /*
       System.out.println("Last call moment :" + LocalDateTime.ofEpochSecond(lastCall[1],0,ZoneOffset.of("+2")));
        System.out.println("First call : " + LocalDateTime.ofEpochSecond(Arrays.stream(calls).mapToLong(x->x[0]).min().getAsLong(),0,ZoneOffset.of("+2")));
        System.out.println("Last call : " +  LocalDateTime.ofEpochSecond(Arrays.stream(calls).mapToLong(x->x[0]).max().getAsLong(),0,ZoneOffset.of("+2")));
        */
       // Collectors.
        //ConcurrentMap
    }

}
