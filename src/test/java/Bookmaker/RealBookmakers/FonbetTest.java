package Bookmaker.RealBookmakers;

import Bookmaker.Bookmaker;
import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.Exception.SiteConnectionException;
import Bookmaker.Properties.Money;
import org.junit.Test;

import static org.junit.Assert.*;

public class FonbetTest {

    @Test
    public void test1() {
        try {
            Bookmaker bookmaker = new Fonbet(new Money(100, 0));
            bookmaker.getMatches();
        } catch (BookmakerCreationException | SiteConnectionException e) {
            System.out.println(e);
        }
    }

}