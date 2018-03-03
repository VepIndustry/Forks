package Bookmaker;

import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.Exception.SiteConnectionException;
import Bookmaker.Properties.Money;
import Bookmaker.Properties.Safety;
import Fork.BetType;
import Fork.Line;
import Fork.Match;

import java.util.List;
import java.util.Objects;

/**
 * Класс представляющий букмекеров, хранящий о них информацию и позволяющий получать информацию о матчах и
 * делать ставки
 */
public abstract class Bookmaker {
    /**
     * Название букмекера
     */
    private String name;
    /**
     * Логин от аккаунта данного букмекера (может не использоваться при сканировании)
     */
    private String login;
    /**
     * Пароль от аккаунта
     */
    private String password;
    /**
     * Название сайта букмекера
     */
    private String website;
    /**
     * Коммиссия при выигрыше ставки (предполагается что она учитывается при расчёте коэффициентов)
     */
    private double commission;
    /**
     * Надёжность букмекера (может учитываться при выборе вилок)
     */
    private Safety safety;
    /**
     * Количество денег заведённых к букмекеру (позволяет понять сколько было выиграно у букмекера)
     */
    private Money realMoney;

    /**
     * @param name название букмекера
     * @param website название сайта букмекера
     * @param commission коммиссия которая взымается в случае выигрыша
     * @param safety надёжность букмекера
     * @param realMoney количество денег заведённых в букмекера
     */
    public Bookmaker(String name, String website, double commission, Safety safety, Money realMoney) throws BookmakerCreationException {
        this.name = name;
        this.website = website;
        this.commission = commission;
        this.safety = safety;
        this.realMoney = realMoney;
        HistoryModule.getInstance().addBookmaker(this);
    }

    /**
     * @return количество денег заведённых букмекеру
     */
    public Money getRealMoney() {
        return realMoney;
    }

    /**
     * @return название букмекерской конторы
     */
    public String getName() {
        return name;
    }

    /**
     * @return название сайта
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @return коммиссию которая взымается при выигрыше
     */
    public double getCommission() {
        return commission;
    }

    /**
     * @return надёжность букмекера
     */
    public Safety getSafety() {
        return safety;
    }

    @Override
    public String toString() {
        return "Bookmaker{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * @return список матчей которые проходят в режиме реального времени
     */
    public abstract List<Match> getMatches() throws SiteConnectionException;

    /**
     * Возвращает линию для конкретного матча
     * @param match матч для которого нужно получить линию
     * @return линия для конкретного матча
     */
    public abstract Line getLine(Match match);

    /**
     * @return количество денег на счету у букмекера
     */
    public abstract Money getBalance();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmaker bookmaker = (Bookmaker) o;
        return Objects.equals(name, bookmaker.name) &&
                Objects.equals(login, bookmaker.login) &&
                Objects.equals(password, bookmaker.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, login, password);
    }

    /**
     * Ставит деньги на конкретный тип ставки на конкретный матч
     * @param money количество денег которое нужно поставить
     * @param match матч на который нужно поставить
     * @param type тип ставки на который нужно поставить
     * @return true если ставка удалась, false если не удалось

     */
    protected abstract boolean makeRate(Money money, Match match, BetType type);
}
