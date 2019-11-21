package nl.fortezza.skill.service;

import com.google.gson.Gson;
import nl.fortezza.skill.builder.StandaardPersonenSetBuilder;
import nl.fortezza.skill.entiteiten.Persoon;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ElasticService {

    private static final String URL = "http://localhost:9200";
    private static final String SEARCH = "posts/_search";
    private static final String WRITE = "posts";
    private static final String INDEXNAME = "personen";

    static int sequenceNr = 101;

    private ElasticService() {
        // Geen instanties aanmaken.
    }

    public static String getAllData() throws IOException {
        String urlString = String.format("%s/%s/%s", URL, INDEXNAME, SEARCH);
        System.out.println(urlString);
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
            result.append("\n");
        }
        rd.close();
        return result.toString();
    }

    public static void writeAll() {
        StandaardPersonenSetBuilder.getTestPersonen().forEach(persoon -> {
            try {
                write(persoon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static String write(Persoon persoon) throws IOException {
        String url = String.format("%s/%s/%s/%s", URL, INDEXNAME, WRITE, sequenceNr++);
        System.out.println(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        StringEntity stringEntity = new StringEntity(toJson(persoon));
        httpPut.setEntity(stringEntity);

        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        return httpclient.execute(httpPut, responseHandler);
    }

    private static String toJson(Persoon persoon) {
        return new Gson().toJson(persoon);
    }
}