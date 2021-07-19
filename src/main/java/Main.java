import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        String json = Json(response.getEntity().getContent());

        List<Cats> facts = List(json);

        List<Cats> voteFacts =  facts.stream()
                .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                .collect(Collectors.toList());

        voteFacts.forEach(System.out::println);
    }

    public static String Json(InputStream inputStream) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        }
    }

    public static List<Cats> List(String json) {
        Type listType = new TypeToken<List<Cats>>(){}.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, listType);
    }
}