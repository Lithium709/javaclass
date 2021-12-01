package com.company.cracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashTable<K, V> {

    private int buckets = 10;
    @SuppressWarnings("unchecked")
    private List<V>[] values = new List[buckets];


    boolean put(K key, V value) {

        final int hashkey = key.hashCode() % buckets;
        if (values[hashkey] == null) {
            values[hashkey] = new LinkedList<>();
        } else {
            for (V v : values[hashkey]) {
                if (v == value) {
                    return false;
                }
            }
        }
        values[hashkey].add(value);
        return true;
    }




}
