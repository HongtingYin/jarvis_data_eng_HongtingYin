package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonParser;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterDaoIntTest {

    private TwitterDao dao;

    @Before
    public void setUp() {
       String consumerKey = System.getenv("consumerKey");
       String consumerSecret = System.getenv("consumerSecret");
       String accessToken = System.getenv("accessToken");
       String tokenSecret = System.getenv("tokenSecret");

       //setup dependency
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
                accessToken, tokenSecret);

        //pass dependency
        dao = new TwitterDao(httpHelper);
    }

    @Test
    public void create() {
        //create a new tweet with coordinates
        String hashtags = "#create";
        ArrayList<Float> coordinatesList = new ArrayList<>();
        coordinatesList.add(15.00f);
        coordinatesList.add(10.00f);
        Coordinates coordinates = new Coordinates(coordinatesList,"Point");
        Tweet tweet = new Tweet("laptop" + " " + System.currentTimeMillis(), coordinates);

        //send a post request
        Tweet createdTweet = dao.create(tweet);

        //check if the tweet created is correct
        assertNotNull(createdTweet.getId());
        assertNotNull(createdTweet.getId_str());
        assertEquals(tweet.getText(), createdTweet.getText());
        assertEquals(tweet.getCoordinates().getCoordinates().size(),
                createdTweet.getCoordinates().getCoordinates().size());
        assertEquals(tweet.getCoordinates().getCoordinates().get(0),
                createdTweet.getCoordinates().getCoordinates().get(0));
        assertEquals(tweet.getCoordinates().getCoordinates().get(1),
                createdTweet.getCoordinates().getCoordinates().get(1));

    }

    @Test
    public void findPostById() throws JsonProcessingException{
        String hashtags = "#FindById";
        String text = "Tomorrow will be a good day";
        Tweet postTweetF = TweetUtil.createTweet(text, 15.00f, 10.00f);
        System.out.println(JsonParser.toJson(postTweetF, true, false));
        Tweet postedTweetF = dao.create(postTweetF);

        String id = postedTweetF.getId_str();
        Tweet tweet = dao.findById(id);

        assertEquals(postTweetF.getText(), tweet.getText());
        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        assertEquals(postedTweetF.getCoordinates().getCoordinates().get(0),
                tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(postedTweetF.getCoordinates().getCoordinates().get(1),
                tweet.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void deletePostById() throws JsonProcessingException {
        String hashtags = "#deletePostById";
        String text = "Testing";
        Tweet postTweetD = TweetUtil.createTweet(text, 10.00f, 15.00f);
        System.out.println("testing");
        System.out.println(JsonParser.toJson(postTweetD, true, false));
        Tweet postedTweetD = dao.create(postTweetD);


        String id = postedTweetD.getId_str();
        Tweet tweet = dao.deleteById(id);

        assertEquals(postTweetD.getText(), tweet.getText());
        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        assertEquals(postedTweetD.getCoordinates().getCoordinates().get(0),
                tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(postedTweetD.getCoordinates().getCoordinates().get(1),
                tweet.getCoordinates().getCoordinates().get(1));
    }
}
