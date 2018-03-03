package Fork.GeneratorFork;

import Fork.BetType;
import Fork.GeneratorForkType.ForkPrototype;
import Fork.Line;
import Fork.Match;
import jcombinatorics.combinations.Combinations;
import jcombinatorics.permutations.Permutations;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Генератор, который на основе прототипов и линий генерирует вилки
 */
public class ForkGenerator {
    /**
     * Типы ставок которые составляют прототипы
     */
    private Set<BetType> types;
    /**
     * Прототипы вилок которые использует генератор
     */
    private Set<ForkPrototype> prototypes;
    /**
     * Все варианты плеч для определённого набора линий (так как плечи immutable, то используется для оптимизации)
     */
    private Map<BetType, Shoulder[]> shoulders = new HashMap<>();

    /**
     * Конструктор который инициализируется прототипами вилок на основе которых будут строиться реальные вилки
     * @param prototypes прототипы вилок на основе которых будут строится реальные вилки
     */
    public ForkGenerator(Set<ForkPrototype> prototypes) {
        types = new HashSet<>();
        for (ForkPrototype prototype : prototypes) {
            types.addAll(prototype.getTypes());
        }
        this.prototypes = prototypes;
    }

    /**
     * Создаёт все возможные плечи для каждого типа ставок на основе полученных линий
     * @param lines линии для какого то конкретного матча
     */
    private void cross(Set<Line> lines) {
        shoulders = new HashMap<>();
        for (BetType type : types) {
            Shoulder[] shoulders = new Shoulder[lines.size()];
            int index = 0;
            for (Line line : lines) {
                shoulders[index] = new Shoulder(line, type);
                index++;
            }

            this.shoulders.put(type, shoulders);
        }
    }

    /**
     * Генерирует все варианты упорядоченных выборок
     * @return множество упорядоченных выборок
     */
    private Set<int[]> permute(int countOfLine, int countOfShoulder) {
        if (countOfLine < countOfShoulder) {
            return new HashSet<>();
        }
        Integer[] indexes = IntStream.range(0, countOfLine).boxed().toArray(Integer[]::new);
        Set<int[]> result = new HashSet<>();
        for (Integer[] permutation : Combinations.of(indexes).take(countOfShoulder)) {
            Permutations.of(Arrays.stream(permutation).mapToInt(Integer::intValue).toArray()).forEach(result::add);
        }
        return result;
    }

    /**
     * Генерирует вилки по прототипу основываясь на ранее заполенном словаре плеч
     * @param prototype прототип вилки на основе которого будут генерироваться вилки
     * @param countOfLines количество линий которые использовались при генреции словаря
     * @param match матч линии которого передаются
     * @return множество вилок
     */
    private Set<Fork> generateByPrototype(ForkPrototype prototype, int countOfLines, Match match) {
        //Выделяем массив BetType, так как нам нужен фиксированный порядок
        BetType[] betTypes = prototype.getTypes().toArray(new BetType[0]);
        //Генерируем комбинации плеч
        Set<int[]> combinations = permute(countOfLines, betTypes.length);
        //Проходимся по каждой комбинации создавая вилку
        Set<Fork> forks = new HashSet<>();
        for (int[] combination : combinations) {
            Fork fork = new Fork(match);
            //Для каждого BetType в зависимости от цифры выбираем плечо
            for (int i = 0; i < combination.length; i++) {
                BetType betType = betTypes[i];
                int index = combination[i];
                Shoulder shoulder = shoulders.get(betType)[index];
                fork.addShoulder(shoulder);
            }
            forks.add(fork);
        }
        return forks;
    }

    /**
     * Метод который генерирует все возможные вилки на основе линий и заранее определённого набора прототипов вилок
     * @param lines линии для матча
     * @param match матч у которого были получены линии
     * @return множество вилок для данного матча
     */
    public synchronized Set<Fork> generate(Set<Line> lines, Match match) {
        //Генерируем все возможные варианты плеч, так как они неизменяемые и иначе будут много раз генерироваться
        cross(lines);
        Set<Fork> forks = new HashSet<>();
        for (ForkPrototype prototype : prototypes) {
            forks.addAll(generateByPrototype(prototype, lines.size(), match));
        }

        return forks;
    }

}
