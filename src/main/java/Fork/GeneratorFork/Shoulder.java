package Fork.GeneratorFork;

import Bookmaker.Bookmaker;
import Bookmaker.Properties.Money;
import Fork.BetType;
import Fork.Line;

import java.util.Calendar;

public class Shoulder {
    /**
     * Букмекер который является хозяином плеча
     */
    private Bookmaker bookmaker;
    /**
     * Тип ставки который определяет плечо
     */
    private BetType type;
    /**
     * Коэффициент на тип ставки
     */
    private double coefficient;
    /**
     * Сколько нужно поставить на данное плечо денег
     */
    private Money money;
    /**
     * Время создания плеча, определяется временем линии
     */
    private Calendar creationTime;

    /**
     * Конструктор плеча
     * @param line линия из которой было получено плечо
     * @param type тип ставки который образует вилку
     */
    public Shoulder(Line line, BetType type) {
        this.bookmaker = line.getBookmaker();
        this.type = type;
        this.coefficient = line.getCoefficient(type);
        this.creationTime = line.getCreationTime();
    }

    /**
     * @return возвращает время создания плеча (время когда плечо является актуальным)
     */
    public Calendar getCreationTime() {
        return creationTime;
    }

    /**
     * @return букмекера - владельца плеча
     */
    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    /**
     * @return тип ставки плеча
     */
    public BetType getType() {
        return type;
    }

    /**
     * @return коэффициент типа ставки
     */
    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return "Shoulder{" +
                "bookmaker=" + bookmaker +
                ", type=" + type +
                ", coefficient=" + coefficient +
                '}';
    }
}
