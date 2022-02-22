package ee.taltech.iti0202.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(List<Kitten> kittens) {

        this.kittens = kittens;
    }

    public OptionalDouble findKittensAverageAge() {
        if (kittens.size() == 0){
            return OptionalDouble.empty();
        }
        IntSummaryStatistics res = kittens.stream().mapToInt(Kitten::getAge).summaryStatistics();
        return OptionalDouble.of(res.getAverage());
    }

    public Optional<Kitten> findOldestKitten() {
        List<Kitten> res = kittens.stream().sorted(Comparator.comparing(Kitten::getAge).reversed()).toList();
        return Optional.of(res.get(0));
    }

    public List<Kitten> findYoungestKittens() {
        Map<Integer, List<Kitten>> res = kittens.stream().collect(Collectors.groupingBy(Kitten::getAge));
        Integer min = Collections.min(res.keySet());
        return res.get(min);
    }

    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        Map<Kitten.Gender, List<Kitten>> res = kittens.stream().collect(Collectors.groupingBy(Kitten::getGender));
        return res.get(gender);
    }

    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        Map<Integer, List<Kitten>> mapCollect = kittens.stream().collect(Collectors.groupingBy(Kitten::getAge));
        List<List<Kitten>> filterKit = mapCollect.entrySet()
                .stream().filter(age -> age.getKey() >= maxAge && age.getKey() <= maxAge)
                .map(Map.Entry::getValue).toList();
        return filterKit.stream().flatMap(Collection::stream).toList();
    }

    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        List<Kitten> filterKit = kittens.stream().filter(cat -> Objects.equals(cat.getName(), givenName)).toList();
        return Optional.of(filterKit.get(0));
    }

    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return kittens.stream().sorted(Comparator.comparing(Kitten::getAge)).toList();
    }

    public List<Kitten> kittensSortedByAgeOlderFirst() {

        return kittens.stream().sorted(Comparator.comparing(Kitten::getAge).reversed()).toList();
    }

}