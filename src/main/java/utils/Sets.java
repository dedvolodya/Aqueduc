package utils;

import java.util.HashSet;

public class Sets {
    public static<T> HashSet<T> union(HashSet<T> a, HashSet<T> b) {
        HashSet<T> result = new HashSet<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    public static<T> HashSet<T> intersection(HashSet<T> a, HashSet<T> b) {
        HashSet<T> result = new HashSet<>();
        result.addAll(a);
        result.retainAll(b);
        return result;
    }

    public static<T> HashSet<T> difference(HashSet<T> a, HashSet<T> b) {
        HashSet<T> result = new HashSet<>();
        result.addAll(a);
        result.removeAll(b);
        return result;
    }
}
