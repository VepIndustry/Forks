package Bookmaker.RealBookmakers;

import Bookmaker.*;
import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.Exception.SiteConnectionException;
import Bookmaker.Network;
import Bookmaker.Properties.Money;
import Bookmaker.Properties.Safety;
import Fork.BetType;
import Fork.Line;
import Fork.Match;

import java.util.List;
import java.io.*;

public class Fonbet extends Bookmaker {

    private Network network;

    public Fonbet(Money realMoney) throws BookmakerCreationException {
        super("Фонбет", "www.fonbet.ru", 0, Safety.C, realMoney);
        try {
            network = new Network("https://line01.bkfon-resource.ru/live/updatesFromVersion/180441657/ru/");
        } catch (IOException e) {
            //TODO добавить больше отладочной информации и логгирование
            throw new BookmakerCreationException("Невозможно подключиться к сайту с матчами\n" + e);
        }
    }

    @Override
    public List<Match> getMatches() throws SiteConnectionException {
        String page;
        try {
            page = network.getPage();
            System.out.println(page);
        } catch (IOException e) {
            //TODO добавить больше отладочной информации и логгирование
            throw new SiteConnectionException("Невозможно получить страницу с матчами\n" + e);
        }
        return null;
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
