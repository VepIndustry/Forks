package Bookmaker;

import Bookmaker.Exception.SiteConnectionException;
import Fork.GeneratorFork.Fork;
import Fork.GeneratorFork.ForkGenerator;
import Fork.Line;
import Fork.Match;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс являющийся контейнером для множества букмекеров и позволяющий выполнять групповые функции с ними
 */
public class BookersPool {
    /**
     * Букмекеры которые входят в пул
     */
    private Set<Bookmaker> bookmakers = new HashSet<>();
    /**
     * Генератор вилок который используется в данном пуле
     */
    private ForkGenerator generator;

    /**
     * Добавляет букмекера в пул, если букмекер уже добавлен то ничего не происходит
     * @param bookmaker букмекер которого нужно добавить
     */
    public void addBookmaker(Bookmaker bookmaker) {
        bookmakers.add(bookmaker);
    }

    /**
     * Возвращает все матчи в реальном времени которые доступны хотя бы у одного букмекера
     * @return матчи
     */
    public Set<Match> getMatches() {
        Set<Match> matches = new HashSet<>();
        for (Bookmaker bookmaker : bookmakers) {
            try {
                matches.addAll(bookmaker.getMatches());
            } catch (SiteConnectionException e) {
                System.out.println(e);
            }
        }
        return matches;
    }

    /**
     * Выдаёт все вилки для конкретного матча используя генератор вилок на основе линий
     * @param match матч для которого нужно получить вилки
     * @return множество вилок
     */
    public Set<Fork> getForks(Match match) {
        Set<Line> lines = new HashSet<>();
        //TODO распалелить и следить чтобы получение не затягивалось, чтобы данные были актуальными
        for (Bookmaker bookmaker : bookmakers) {
            lines.add(bookmaker.getLine(match));
        }

        return generator.generate(lines, match);
    }

    /**
     * Устанаваливает генератор вилок
     * @param generator генератор вилок который будет использоваться для генерации вилок
     */
    public void setForkGenerator(ForkGenerator generator) {
        this.generator = generator;
    }
}
