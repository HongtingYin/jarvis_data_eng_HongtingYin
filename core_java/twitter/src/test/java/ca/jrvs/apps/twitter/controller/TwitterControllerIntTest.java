package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterControllerIntTest {
    private final Logger logger = LoggerFactory.getLogger(TwitterControllerIntTest.class);
    private TwitterController controller;

    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        //pass dependency
        HttpHelper twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        TwitterDao dao = new TwitterDao(twitterHttpHelper);
        Service service = new TwitterService(dao);
        controller = new TwitterController(service);
    }

    @Test
    public void shouldPostTweet() {
        logger.info("Post a tweet, testing...");
        String text = "Testing Post #Controller "+ System.currentTimeMillis();

        Float lon = 1.0f;
        Float lat = 1.1f;

        String[] args = {"post", text, "1.0f:1.1f"};
        Tweet tweetPost = controller.postTweet(args);

        assertEquals(text, tweetPost.getText());
        assertNotNull(tweetPost.getCoordinates());
        assertEquals(2, tweetPost.getCoordinates().getCoordinates().size());
        assertEquals(lon, tweetPost.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweetPost.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void shouldShowTweet() {
        logger.info("Find a tweet, testing...");
        String text = "Testing Show #Controller " + System.currentTimeMillis();
        Float lon = 1.0f;
        Float lat = 1.1f;
        String[] args = {"post", text, "1.0f:1.1f"};
        Tweet tweet = controller.postTweet(args);
        String id = tweet.getId_str();
        String[] argsId = {"show", id, "Find by id"};

        Tweet tweetShow = controller.showTweet(argsId);

        assertEquals(text, tweetShow.getText());
        assertNotNull(tweetShow.getCoordinates());
        assertEquals(2, tweetShow.getCoordinates().getCoordinates().size());
        assertEquals(lon, tweetShow.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweetShow.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void shouldDeleteTweet() {
        logger.info("Delete tweets, testing...");
        String firstText = "Testing Delete 1 #Controller " + System.currentTimeMillis();
        String[] firstPostArgs = {"post", firstText, "1.0f:1.1f"};
        Tweet firstPostedTweet = controller.postTweet(firstPostArgs);

        String secondText = "Testing Delete 2 #Controller " + System.currentTimeMillis();
        String[] secondPostArgs = {"post", secondText, "1.0f:1.1f"};
        Tweet secondPostedTweet = controller.postTweet(secondPostArgs);

        String firstId = firstPostedTweet.getId_str();
        String secondId = secondPostedTweet.getId_str();
        String ids = firstId + "," + secondId;
        String[] args = {"delete", ids, "Delete by id"};

        List<Tweet> tweets = controller.deleteTweet(args);

        assertEquals(firstText, tweets.get(0).getText());
        assertEquals(secondText, tweets.get(1).getText());

    }
}