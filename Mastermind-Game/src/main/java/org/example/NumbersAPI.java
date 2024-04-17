package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NumbersAPI {
    private static final String RANDOM_API_URL = "https://www.random.org/integers";
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 7;
    private static final int BASE = 10;
    private static final String FORMAT = "plain";
    private static final String RND = "new";

    public static List<Integer> generateSecretCode(int codeLength) {
        List<Integer> secretCode = new ArrayList<>();
        try {
            URL url = new URL(RANDOM_API_URL + "?num=" + codeLength + "&min=" + MIN_VALUE + "&max=" + MAX_VALUE + "&col=1&base=" + BASE + "&format=" + FORMAT + "&rnd=" + RND);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    secretCode.add(Integer.parseInt(line));
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return secretCode;
    }
}
