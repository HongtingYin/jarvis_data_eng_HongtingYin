package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    
    @Mock
    TwitterDao dao;

    @InjectMocks
    TwitterService service;

    @Test
    public void shouldPostTweet(){
        Tweet newTweet = TweetUtil.createTweet("Post testing", " #unittest ", 60.0f, 61.0f);
        Mockito.when(dao.create(any(Tweet.class))).thenReturn(newTweet);
        Tweet postTweet = service.postTweet(newTweet);
        assertEquals(postTweet.getText(), newTweet.getText());
    }

    @Test
    public void shouldShowTweet() {
        Tweet newTweet = TweetUtil.createTweet("Show testing", " #unittest ", 60.0f, 61.0f);
        Mockito.when(dao.findById(anyString())).thenReturn(newTweet);

        Tweet tweet = service.showTweet("1234567890", null);

        assertNull(tweet.getCreated_at());
        assertNull(tweet.getId());
    }

    @Test
    public void shouldDeleteTweets() {
        String[] ids = {"1250185392676130816"};
        Tweet tweet1 = TweetUtil.createTweet("Delete testing", " #unittest ", 60.0f, 61.0f);

        Mockito.when(dao.deleteById((anyString()))).thenReturn(tweet1);
        List<Tweet> tweet = service.deleteTweets(ids);

        assertEquals(tweet.get(0).getText(), tweet1.getText());
    }

}
