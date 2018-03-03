package Fork.GeneratorForkType;

import Fork.BetType;

import java.util.Set;
import java.util.function.IntPredicate;

/**
 * Класс с помощью которого можно проверить является ли прототип вилки реальной вилкой или коридором
 */
class PrototypeChecker {
    /**
     * Максимальный счёт игры
     */
    private static final int MAX_VALUE = 10;

    /**
     * Проверяет прототип на то какие исходы матча он покрывает
     * @param prototype прототип который нужно проверить
     * @return объект Result который позволяет определять тип прототипа
     */
    public Result check(ForkPrototype prototype) {
        Set<BetType> types = prototype.getTypes();
        int[][] scores = new int[MAX_VALUE][MAX_VALUE];

        for (int i = 0; i < MAX_VALUE; i++) {
            for (int j = 0; j < MAX_VALUE; j++) {
                for (BetType type : types) {
                    if (type.apply(i, j)) {
                        scores[i][j] += 1;
                    }
                }
            }
        }

        return new Result(scores);
    }

    /**
     * Вспомогательный класс который содержит результат проверки прототипа на его тип
     */
    class Result {
        /**
         * Массив исходов который показывает какие исходы покрыты и сколько раз
         */
        private int[][] scores;

        /**
         * @param scores массив исходов для данного прототипа
         */
        private Result(int[][] scores) {
            this.scores = scores;
        }

        /**
         * Метод позволяющий понять удовлетворяет ли каждый исход предикату
         * @param predicate предикат для которого проверяется каждый исход
         * @return true если каждый исход удовлетворяет пердикату, иначе false
         */
        private boolean isEquals(IntPredicate predicate) {
            for (int i = 0; i < MAX_VALUE; i++) {
                for (int j = 0; j < MAX_VALUE; j++) {
                    if (!predicate.test(scores[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }

        /**
         * @return true если прототип покроет все исходы хотя бы 1 раз, иначе false
         */
        boolean isFull() {
            return isEquals(x -> x != 0);
        }

        /**
         * @return true если все исходы покрыты ровно один раз (вилка), иначе false
         */
        boolean isFork() {
            return isEquals(x -> x == 1);
        }

        /**
         * @return true если исходы покрываются 1 или более раз (мб коридор), иначе false
         */
        boolean biggerThanFork() {
            return !isEquals(x -> x <= 1);
        }
    }


}

