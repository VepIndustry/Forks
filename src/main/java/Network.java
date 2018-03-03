package Bookmaker;

import java.net.*;
import java.io.*;

public class Network {

    private HttpURLConnection connection;

    public Network(String url) throws IOException {
        URL obj = new URL(url);
        connection = (HttpURLConnection) obj.openConnection();
    }

    public String getPage() throws IOException {
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append('\n');
            }
            return response.toString();
        } catch (IOException e) {
            throw e;
        }
    }
}
