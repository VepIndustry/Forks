package Bookmaker.RealBookmakers;

import Bookmaker.Bookmaker;
import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.Exception.SiteConnectionException;
import Bookmaker.Properties.Money;
import Bookmaker.RealBookmakers.Fonbet.Fonbet;
import org.junit.Test;

public class FonbetTest {

    @Test
    public void test1() {
        try {
            Bookmaker bookmaker = new Fonbet(new Money(100, 0));
            System.out.println(bookmaker.getMatches());
        } catch (BookmakerCreationException | SiteConnectionException e) {
            System.out.println(e);
        }
    }

}