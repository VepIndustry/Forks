package Fork;

import Bookmaker.Bookmaker;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Содержит информацию о линии (то есть о коеффициентах на то или иное событие) для определённого букмекера и матча
 */
public class Line {
    /**
     * Букмекер у которого была получена данная линия
     */
    private Bookmaker bookmaker;
    /**
     * Матч который определяет данную линию
     */
    private Match match;
    /**
     * Коэффициенты для каждого типа ставок
     */
    private Map<BetType, Double> coefficient = new HashMap<>();
    /**
     * Время, для которого линия является актуальной
     */
    private Calendar creationTime = Calendar.getInstance();

    /**
     * Создаёт линия на основе bookmaker и match
     */
    public Line(Bookmaker bookmaker, Match match) {
        this.bookmaker = bookmaker;
        this.match = match;
    }

    /**
     * Добавляет в линию тип ставки и коэффициент
     * @param type ставки
     * @param coefficient на данный тип ставки с которым выплатят выигрыш
     */
    public void setCoefficient(BetType type, double coefficient) {
        this.coefficient.put(type, coefficient);
    }

    /**
     * @return время когда данная линия была получена
     */
    public Calendar getCreationTime() {
        return creationTime;
    }

    /**
     * Выдаёт коэффициент на данный тип ставки
     * @param type ставки
     * @return коэффициент на указанный тип ставки, если такого нет то 0
     */
    public double getCoefficient(BetType type) {
        Double value = coefficient.get(type);
        return value == null ? 0 : value;
    }

    /**
     * @return букмекера у которого была получена данная линия
     */
    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    /**
     * @return матч для которого была получена данная линия
     */
    public Match getMatch() {
        return match;
    }
}
