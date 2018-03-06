package Bookmaker.FakeBookmaker;

import Bookmaker.Bookmaker;
import Bookmaker.Exception.BookmakerCreationException;
import Bookmaker.Properties.Money;
import Bookmaker.Properties.Safety;
import Fork.BetType;
import Fork.Line;
import Fork.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Fork.BetType.*;
import static java.lang.Math.random;

public class FakeBookmaker extends Bookmaker {
    private static List<Match> matches = new ArrayList<>(Arrays.asList(
            new Match(),
            new Match(),
            new Match(),
            new Match()
    ));


    public FakeBookmaker(String name, String website, double commission, Safety safety, Money realMoney) throws BookmakerCreationException {
        super(name, website, commission, safety, realMoney);
    }

    @Override
    protected boolean makeRate(Money money, Match match, BetType type) {
        return false;
    }

    @Override
    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    //Рандомно составляет линию
    @Override
    public Line getLine(Match match) {
        Line line = new Line(this, match);
        line.setCoefficient(FIRST, random() * 5 + 1);
        line.setCoefficient(SECOND, random() * 5 + 1);
        line.setCoefficient(DRAW, random() * 5 + 1);
        line.setCoefficient(NOT_FIRST, random() * 5 + 1);
        line.setCoefficient(NOT_SECOND, random() * 5 + 1);
        return line;
    }

    @Override
    public Money getBalance() {
        return null;
    }
}
