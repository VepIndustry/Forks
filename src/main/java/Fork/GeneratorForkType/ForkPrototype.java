package Fork.GeneratorForkType;

import Fork.BetType;

import java.util.*;

/**
 * Является прототипом вилки, задавая типы ставок при проставлении которых может возникнуть вилка.
 * То есть содержит типы ставок которые покрывают всё множество исходов матча
 */
public class ForkPrototype {
    /**
     * Множество типов ставок покрывающих все исходы матча
     */
    private Set<BetType> types;

    /**
     * Конструктор принимающий типы ставок
     * @param types типы ставок
     */
    ForkPrototype(BetType... types) {
        this.types = new HashSet<>(Arrays.asList(types));
    }

    /**
     * Так как класс является immutable, то данный метод объединяет типы ставок у данного прототипа с переданными
     * в качестве аргументов и создаёт новый прототип
     * @param types дополнительные типы ставок
     * @return новый прототип с объединением типов ставок
     */
    ForkPrototype union(BetType... types) {
        ForkPrototype prototype = new ForkPrototype(types);
        prototype.types.addAll(this.types);
        return prototype;
    }

    /**
     * @return типы ставок
     */
    public Set<BetType> getTypes() {
        return Collections.unmodifiableSet(types);
    }

    /**
     * @return количество плеч
     */
    public int getSize() {
        return types.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForkPrototype prototype = (ForkPrototype) o;
        return Objects.equals(types, prototype.types);
    }

    @Override
    public String toString() {
        return "ForkPrototype{" +
                "types=" + types +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(types);
    }
}
