package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;

public class TwitterHttpHelperTest {

    @Test
    public void httpPost() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        //create components
        HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        URI uri = new URI("https://api.twitter.com/1.1/statuses/update.json?status=example&long=0%2E0&lat=0%2E0");
        HttpResponse response = helper.httpPost(uri);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}