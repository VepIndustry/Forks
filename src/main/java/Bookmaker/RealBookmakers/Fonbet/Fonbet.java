package Bookmaker.RealBookmakers.Fonbet;

import Bookmaker.Bookmaker;
import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.Exception.SiteConnectionException;
import Bookmaker.Network;
import Bookmaker.Properties.Money;
import Bookmaker.Properties.Safety;
import Fork.BetType;
import Fork.Line;
import Fork.Match;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fonbet extends Bookmaker {

    private List<Match> matches = new ArrayList<>();
    private FonbetDriver driver;

    public Fonbet(Money realMoney) throws BookmakerCreationException {
        super("Фонбет", "www.fonbet.ru", 0, Safety.C, realMoney);
        try {
            driver = new FonbetDriver();
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу" + e.getStackTrace());
        }
    }


    @Override
    public List<Match> getMatches() throws SiteConnectionException {
        return driver.getMatches();
    }

    @Override
    public Line getLine(Match match) {
        return null;
    }

    @Override
    public Money getBalance() {
        return null;
    }

    @Override
    protected boolean makeRate(Money money, Match match, BetType type) {
        return false;
    }
}
