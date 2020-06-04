package com.tryingpfq.ovver;

import org.apache.hadoop.util.hash.Hash;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tryingpfq
 * @date 2020/4/20
 **/
public class Bigram {
    private final char first;

    private final char second;

    public Bigram(char first, char seconc) {
        this.first = first;
        this.second = seconc;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bigram bigram = (Bigram) o;
        return first == bigram.first &&
                second == bigram.second;
    }

   // @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
        }
        System.err.println(s.size());

    }
}
