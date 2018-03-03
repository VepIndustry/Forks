package Bookmaker;

import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.FakeBookmaker.FakeBookmaker;
import Bookmaker.Properties.Money;
import Bookmaker.Properties.Safety;

/**
 * Класс фабрика предназначен для более удобного создания и настройки букмекеров
 */
public class BookmakerFactory {

    /**
     * Создаёт букмекера по его имени и другим параметрам
     *
     * @param name название букмекера
     * @return созданного букмекера
     */
    public static Bookmaker getBookmaker(String name) {
        switch (name) {
            case "Fake": {
                try {
                    return new FakeBookmaker("Fake", "fake.com", 0, Safety.A, new Money(100, 0));
                } catch (BookmakerCreationException e) {
                    return null;
                }
            }
            default:
                return null;
        }
    }

}
