

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;


public class Main {
    public static ObjectMapper mapper = new ObjectMapper();
    public static String REMOTE_SERVER_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(REMOTE_SERVER_URI);
        CloseableHttpResponse response = httpClient.execute(request);
        List<FactAboutCat> FAC = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        FAC.stream()
                .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                .forEach(value -> System.out.println(value.getText()));
    }

}
