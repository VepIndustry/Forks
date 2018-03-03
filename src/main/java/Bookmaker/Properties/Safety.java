package Bookmaker.Properties;

/**
 * Перечесление показывающее надёжность букмекера
 */
public enum Safety {
    A(1), B(0.8), C(0.6), D(0.4), F(0.2);

    Safety(double safety) {
        this.safety = safety;
    }

    public final double safety;
}
