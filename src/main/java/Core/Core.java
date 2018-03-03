package Core;

import Bookmaker.BookersPool;
import Fork.GeneratorFork.Fork;
import Fork.Match;
import Solver.ForkSolver;

import java.util.Set;

/**
 * Ядро работы с каждым пулом букмекеров. Отвечает за поиск, анализ и реализацию вилок
 */
public class Core extends Thread {
    /**
     * Пул с которым работает ядро
     */
    private BookersPool pool;
    /**
     * Логический модуль который выберает и отсеивает невыгодные, по тем или иным причинам, вилки.
     */
    private LogicModule logicModule;
    /**
     * Разрешатель вилок, реализует вилки переданные в него
     */
    private ForkSolver solver;

    /**
     * @param name название ядра
     * @param pool пул с которым работает ядро
     * @param logicModule логический модуль отвечающий за поведение
     * @param solver объект который реализует вилки
     */
    public Core(String name, BookersPool pool, LogicModule logicModule, ForkSolver solver) {
        super(name);
        this.pool = pool;
        this.logicModule = logicModule;
        this.solver = solver;
    }

    /**
     * Основной метод в котором происходит действие
     */
    @Override
    public void run() {
        /*
            Основная логика работы ядра состоит в том, чтобы получить все матчи, пройтись по ним, получая по каждому матчу
            у пула вилки, и передавая их в logicModule, который вернёт те вилки которые нужно проставить
            Далее эти вилки передаются в Solver, который вероятнее всего в продакшене будет пересылать эти вилки другому
            серверу который уже и будет их ставить
         */

        while (!isInterrupted()) {
            Set<Match> matches = pool.getMatches();
            for (Match match : matches) {
                long start = System.currentTimeMillis();

                Set<Fork> forks = pool.getForks(match);
                forks = logicModule.calculate(forks);

                System.out.println("Time of work is " + (System.currentTimeMillis() - start) + "ms");

                solver.solve(forks);

                //TODO убрать в продакшене
                try {
                    System.out.println("inner sleep");
                    sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            //Нужно для тестов
            //TODO убрать в продакшене
            try {
                System.out.println("sleep");
                sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

}
