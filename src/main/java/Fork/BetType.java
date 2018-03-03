package Fork;

import java.util.function.BiFunction;

/**
 * Enum который определяет типы ставок. Содержит также возможные наборы типов.
 */
public enum BetType implements BiFunction<Integer, Integer, Boolean> {
    FIRST((i, j) -> i > j),
    DRAW(Integer::equals),
    SECOND((i, j) -> i < j),
    NOT_FIRST((i, j) -> i <= j),
    NOT_SECOND((i, j) -> i >= j);

    /**
     * Массив содержащий все типы ставок.
     */
    public static BetType[] ALL_TYPES = {FIRST, DRAW, SECOND, NOT_FIRST, NOT_SECOND};
    /**
     * Функция которая определяет по счёту выиграет команда или нет.
     */
    private BiFunction<Integer, Integer, Boolean> function;

    /**
     * Создаёт тип ставки
     * @param function определяющая когда ставка на данный тип сыграет, а когда нет
     */
    BetType(BiFunction<Integer, Integer, Boolean> function) {
        this.function = function;
    }

    /**
     * Определяет сыграет ли ставка на заданный исход с данным счётом
     * @param i равен счёту первой команды
     * @param j равен счёту второй команды
     * @return true если ставка сыграет, иначе false
     */
    @Override
    public Boolean apply(Integer i, Integer j) {
        return function.apply(i, j);
    }
}
