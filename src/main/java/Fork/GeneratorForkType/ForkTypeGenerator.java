package Fork.GeneratorForkType;

import Fork.BetType;
import Fork.GeneratorForkType.PrototypeChecker.Result;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Генерирует прототипы вилок на основе множества возможных типов ставок
 */
public class ForkTypeGenerator {
    /**
     * Задаёт максимальное количество плеч у прототипов вилок
     */
    private static final int MAX_SIZE = 3;

    /**
     * Добавляет к каждому прототипу по одному типу ставок. Выполняет декартово произведение.
     * @param prototypes прототипы вилок к которым будут добавлятся типы ставок
     * @param types типы ставок которые будут добавляться к прототипам
     * @return множество прототипов полученных произведением прототипов и типов ставок.
     */
    private Set<ForkPrototype> cross(Set<ForkPrototype> prototypes, Set<BetType> types) {
        Set<ForkPrototype> resultSet = new HashSet<>();

        for (ForkPrototype prototype : prototypes) {
            for (BetType type : types) {
                resultSet.add(prototype.union(type));
            }
        }

        return resultSet;
    }

    /**
     * Выбирает из всех прототипов только те, которые могут давать реальные вилки
     * @param prototypes прототипы которые могут являться реальными протототипами вилок
     * @return прототипы реальных вилок
     */
    private Set<ForkPrototype> selectForks(Set<ForkPrototype> prototypes) {
        Set<ForkPrototype> resultSet = new HashSet<>();
        PrototypeChecker checker = new PrototypeChecker();

        for (ForkPrototype prototype : prototypes) {
            Result result = checker.check(prototype);
            if (result.isFork()) {
                resultSet.add(prototype);
            }
        }
        return resultSet;
    }

    /**
     * Генерирует по типам ставок все возможные вилки с количеством плеч меньшим MAX_SIZE
     * @param types типы ставок которые могут быть использованы в прототипах вилок
     * @return множество прототипов реальных вилок
     */
    public Set<ForkPrototype> generate(BetType... types) {
        Set<ForkPrototype> prototypes = new HashSet<>();
        prototypes.add(new ForkPrototype());
        Set<BetType> typeSet = new HashSet<>(Arrays.asList(types));

        for (int i = 0; i < MAX_SIZE; i++) {
            prototypes = cross(prototypes, typeSet);
        }
        return selectForks(prototypes);
    }
}
