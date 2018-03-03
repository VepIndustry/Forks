package Core;

import Fork.GeneratorFork.Fork;

import java.util.Set;

/**
 * Модуль отвечающий за то какие вилки нужно реализовывать, а какие нет
 */
public interface LogicModule {

    /**
     * Метод которые выбирает из предоставленных вилок те которые нужно реализовать
     * @param forks вилки которые есть на данный момент
     * @return вилки которые нужно реализовать
     */
    Set<Fork> calculate(Set<Fork> forks);

}
