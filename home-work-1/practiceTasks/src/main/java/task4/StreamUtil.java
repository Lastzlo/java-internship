package task4;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtil<T> {

    public static int FIND_LENGTH = 2;

    public List<T> findInStreamThenList(Stream<T> stream, Predicate<T> predicate) {
        return stream.filter(predicate)
                .limit(FIND_LENGTH)
                .collect(Collectors.toList());
    }

    public Stream<T> findInStreamThenStream(Stream<T> stream, Predicate<T> predicate) {
        return stream.filter(predicate)
                .limit(FIND_LENGTH);
    }

    public Stream<T> findInCollectionThenStream(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .limit(FIND_LENGTH);
    }

    public Collection<T> findInCollectionThenCollection(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .limit(FIND_LENGTH)
                .collect(Collectors.toList());
    }

    public Collection<T> findInStreaThenCollection(Stream<T> stream, Predicate<T> predicate,
                                               Supplier<Collection<T>> collectionSupplier) {
        return stream.filter(predicate)
                .filter(predicate)
                .limit(FIND_LENGTH)
                .collect(Collectors.toCollection(collectionSupplier));
    }
}
