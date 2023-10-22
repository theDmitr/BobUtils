package dmitr.bobutils.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {

    private static final String googleScriptUrl = "https://script.google.com/macros/s/AKfycbwbiItZ8RFa495t8q174rgCDpYvge2W7gQK6Z-qwTLvxijg1ISFpeFwJOJ0mY6n9j5xHw/exec";

    public static String translate(String langFrom, String langTo, String text) {
        StringBuilder response = new StringBuilder();

        try {
            String urlStr = googleScriptUrl + "?q=" +
                    URLEncoder.encode(text.replaceAll("\n", "<br>"), "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;

            URL url = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null)
                response.append(inputLine).append("\n");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return response.toString().replaceAll("<br> ", "\n");
    }

}
