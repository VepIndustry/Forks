package Bookmaker.RealBookmakers.Fonbet;

import Bookmaker.Network;
import Fork.Match;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Класс для упрощения работы с сайтом
 */
public class FonbetDriver {

    Network network = new Network("https://line02.bkfon-resource.ru/live/currentLine/ru");

    public FonbetDriver() throws IOException {
    }

    private String getContent() {
        try {
            return network.getPage();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private Response getResponse() {
        return new Response(getContent());
    }

    public List<Match> getMatches() {
        return getResponse().getMatches();
    }

}
