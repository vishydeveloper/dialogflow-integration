package dialogflow;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@PropertySource("classpath:application.properties")
public class Client {
    public static void main(String[] args) throws IOException {
        try {
            RestTemplate rt = new RestTemplate();
            String uri = "http://localhost:9002/hello/{user}";
            User u = new User();
            u.setName("Vishy");
            u.setJob("dev");
            Map<String, String> vars = new HashMap<String, String>();
            vars.put("user", "");
//            User returns = rt.postForObject(uri, u, User.class, vars);
            String returns = rt.postForObject(uri, u, String.class, vars);
            System.out.println(returns);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
