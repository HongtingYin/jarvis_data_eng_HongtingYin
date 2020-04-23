package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TwitterDao implements CrdDao<Tweet, String> {

    //URI constants
    private static final String API_BASE_URI = "http://api.tweeter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;
    private final PercentEscaper escaper = new PercentEscaper("", false);

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    /**
     * Create an Entity(Tweet) to the underlying storage
     *
     * @param tweet entity that need to be created
     * @return entity created successfully
     */
    @Override
    public Tweet create(Tweet tweet) {
        //construct URI
        URI uri;
        try {
            uri = getPostUri(tweet);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Wrong input for tweet : " + e);
        }

        //execute Http request
        HttpResponse response = httpHelper.httpPost(uri);

        //validate response and parse response to Tweet object
        return parseResponseBody(response);
    }

    /**
     * Helper function to get the POST uri
     *
     * @param tweet the tweet need to be posted
     * @return the uri we can use
     * @throws URISyntaxException exception with wrong uri syntax
     */
    private URI getPostUri(Tweet tweet) throws URISyntaxException {
        List<Double> coordinates = tweet.getCoordinates().getCoordinates();
        URI uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM
                + "status" + EQUAL + escaper.escape(tweet.getText())
                + AMPERSAND + escaper.escape(String.valueOf(coordinates.get(0)))
                + AMPERSAND + escaper.escape(String.valueOf(coordinates.get(1))));
        return uri;

    }

    /**
     * Check response status code and Covert Response Entity to Tweet Object
     * if the status code = 200 and the Entity body is not empty
     *
     * @param response HTTP request response
     * @return Tweet Object
     */
    private Tweet parseResponseBody(HttpResponse response) {
        //check status code
        int status  = response.getStatusLine().getStatusCode();
        if (status != TwitterDao.HTTP_OK) {
            throw new RuntimeException("Unexpected HTTP status: " + status);
        }

        //check the Entity and covert the Response Entity to String if Entity is not empty
        HttpEntity httpEntity = response.getEntity();
        String tweetStr;
        if (httpEntity ==  null) {
            throw new RuntimeException("Empty Entity body");
        } else {
            try {
                tweetStr = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert Entity to String");
            }
        }

        //parse Json String to Tweet Object
        try {
            return JsonParser.toObjectFromJson(tweetStr, Tweet.class);
        } catch (IOException e){
            throw new RuntimeException("Failed to convert JSON to Tweet Object");
        }
    }

    /**
     * Find an Entity(Tweet) by its id
     *
     * @param tweetIdF  Entity Id
     * @return Tweet Entity
     */
    @Override
    public Tweet findById(String tweetIdF) {
        URI uri;
        try {
            uri = new URI(API_BASE_URI + SHOW_PATH + tweetIdF);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Tweet ID not found:" + e);
        }

        HttpResponse response = httpHelper.httpGet(uri);
        return parseResponseBody(response);
    }

    @Override
    public Tweet deleteById(String tweetIdD) {
        URI uri;
        try {
            uri = new URI(API_BASE_URI + DELETE_PATH + tweetIdD);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Tweet ID not found:" + e);
        }

        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponseBody(response);
    }
}