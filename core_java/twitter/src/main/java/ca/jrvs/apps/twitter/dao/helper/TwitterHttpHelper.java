package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class TwitterHttpHelper implements HttpHelper {
    /**
     * Dependencies are specified as private member variables
     */
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        httpClient = HttpClientBuilder.create().build();
    }

   public TwitterHttpHelper() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        this.consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,tokenSecret);
        httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        try {
            return executeHttpRequest(HttpMethod.POST, uri);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException("Execution failed", e);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            return executeHttpRequest(HttpMethod.GET, uri);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException("Execution failed", e);
        }
    }

    private HttpResponse executeHttpRequest(HttpMethod method, URI uri) throws OAuthException, IOException {
        if (method == HttpMethod.GET) {
            HttpGet getRequest = new HttpGet(uri);
            consumer.sign(getRequest);
            return httpClient.execute(getRequest);
        } else if (method == HttpMethod.POST) {
            HttpPost postRequest = new HttpPost(uri);
            consumer.sign(postRequest);
            return httpClient.execute(postRequest);
        } else {
            throw new IllegalArgumentException("Unknown HTTP method" + method.name());
        }
    }

}
