package es.iespuertodelacruz.xptrade.shared.utils;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComparisionUtils {
    public static <A, B> boolean setsAreEqualByName(Set<A> setA, Set<B> setB, Function<A, String> getNameA, Function<B, String> getNameB) {
        if (setA == null || setB == null) {
            return false;
        }
        if (setA.size() != setB.size()) {
            return false;
        }

        Set<String> namesA = setA.stream().map(getNameA).collect(Collectors.toSet());
        Set<String> namesB = setB.stream().map(getNameB).collect(Collectors.toSet());

        return namesA.equals(namesB);
    }
}
