package com.google.algorithms;

// public class Interview2 {


//    Interviewer: Brady Hunsaker

 //   a[n]  array of unsorted integer
  //  k

//  [1,2,3,2]   k = 2
//  [1,3,2,2]
// m

// O(n)
// O(nm)
/*
    public void rearrange(int[] a, int k) {

        int last = a.length - 1;
        int[] tmp = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == k && last != i) {
                int tmp = a[last];
                a[last] = k;
                a[i] = tmp;
                last--;
            } else if (a[i] == k && last == i) {
                last--;
            }

        }

    }


--------------------------------
    A: ()
    B: (A C)
    C: (D)
    D: ()

    count(A) = 1

    count(B) = 1 + count(A) + count(C)

    private Map<Person, List<Person>> stuff;

    public int count(Person p) {

        if( p == null) {
            return 0;
        }

        int count = 1;
        for(Person i: stuff.getOrDefault(p, new ArrayList<>()) {
            count += count(i);
        }
        return count;
    }

    public Map<Person, Long> countAll(){

        Map<Person, Long> counter = new HashMap<>();

        for (Person e: stuff.keySet()) {
            counter.put(e, counter(e));
        }

        return counter;
    }











}

*/
