package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

    @Mock
    Service service;

    @InjectMocks
    TwitterController controller;

    private final Logger logger = LoggerFactory.getLogger(TwitterControllerUnitTest.class);

    @Test
    public void shouldPostTweet() {
        logger.info("Post a tweet, Unit testing ... ");
        Tweet newTweet = TweetUtil.createTweet("Unit Test Post #Controller ", 2.0f, 2.1f);
        String[] args = {"post", newTweet.getText(), "2.0f:2.1f"};

        when(service.postTweet(any())).thenReturn(newTweet);
        Tweet tweet = controller.postTweet(args);

        assertEquals(tweet.getText(), newTweet.getText());
        assertNotNull(tweet.getText());
        assertNotNull(tweet.getCoordinates());
    }

    @Test
    public void shouldShowTweet() {
        logger.info("Fina a tweet by id, Unit testing ... ");
        String id = "1260157123943698433";
        Tweet newTweet = TweetUtil.createTweet("Unit Test Show #Controller ", 2.0f, 2.1f);
        String[] args = {"show", id, "Unit Test Show"};

        when(service.showTweet(anyString(), any())).thenReturn(newTweet);
        Tweet tweet = controller.showTweet(args);

        assertEquals(tweet.getText(), newTweet.getText());
    }

    @Test
    public void shouldDeleteTweet() {
        logger.info("Delete tweets, Unit testing .. ");
        String ids = "1260157123943698433,1260158814986342400";
        List<Tweet> tweets = new LinkedList<>();
        tweets.add(TweetUtil.createTweet("Unit Test Delete tweet1 #Controller ", 2.0f, 2.1f));
        tweets.add(TweetUtil.createTweet("Unit Test Delete tweet2 #Controller ", 2.0f, 2.1f));

        String[] args = {"delete", ids, "Unit Test Show"};

        when(service.deleteTweets(any())).thenReturn(tweets);
        List<Tweet> deletedTweets = controller.deleteTweet(args);

        assertEquals(deletedTweets.get(0).getText(), tweets.get(0).getText());
        assertEquals(deletedTweets.get(1).getText(), tweets.get(1).getText());
    }
}
