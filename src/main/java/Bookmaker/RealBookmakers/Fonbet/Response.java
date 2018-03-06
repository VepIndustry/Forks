package Bookmaker.RealBookmakers.Fonbet;

import Fork.Match;
import Fork.SportType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class Response {

    private ResponseContainer responseContainer;

    Response(String content) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        responseContainer = gson.fromJson(content, ResponseContainer.class);
    }

    synchronized List<Match> getMatches() {
        List<Match> matches = new ArrayList<>();
        Map<Integer, SportContainer> sports = new HashMap<>();

        for (SportContainer sport : responseContainer.sports) {
            sports.put(sport.id, sport);
        }

        for (EventContainer eventContainer : responseContainer.events) {
            if (eventContainer.level == 1) {
                Match match = new Match();

                Calendar dateOfStart = Calendar.getInstance();
                dateOfStart.setTimeInMillis(eventContainer.startTime * 1000);
                match.setDateOfBegin(dateOfStart);

                match.setTeam1(eventContainer.team1);
                match.setTeam2(eventContainer.team2);

                SportContainer sportContainer = sports.get(eventContainer.sportId);
                SportContainer sportType = sports.get(sportContainer.parentId);

                match.setType(SportType.getRussianType(sportType.name));

                match.setName(sportContainer.name);

                matches.add(match);
            }
        }
        return matches;
    }
}

class ResponseContainer {
    EventContainer[] events;
    SportContainer[] sports;
}

class CustomFactorContainer {
    @SerializedName("e")
    public int id;

    @SerializedName("f")
    public int type;

    @SerializedName("v")
    public double coef;
}

class SportContainer {
    public int id;
    public String name;
    public int parentId;
    public String sport;
}

class EventContainer {
    public int id;
    public int sportId;
    public String team1;
    public String team2;
    public long startTime;
    public int level;
}