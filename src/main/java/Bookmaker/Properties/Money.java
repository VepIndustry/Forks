package Bookmaker.Properties;

/**
 * Представляет деньги в удобной форме, независимой от типа валюты
 * immutable
 */
public class Money {
    private int rubles, pennies;

    public int getRubles() {
        return rubles;
    }

    public int getPennies() {
        return pennies;
    }

    public Money(int rubles, int pennies) {
        this.pennies = pennies;
        this.rubles = rubles;
    }
}
