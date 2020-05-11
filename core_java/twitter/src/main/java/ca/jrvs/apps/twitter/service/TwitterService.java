package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TwitterService implements Service {
    private CrdDao dao;
    private static final int maxLength = 140;

    @Autowired
    public TwitterService(CrdDao dao) {
        this.dao =  dao;
    }

    /**
     * Validate and post a Tweet
     *
     * @param tweet tweet need to be validated and created
     * @return tweet
     */
    @Override
    public Tweet postTweet(Tweet tweet) {
        //business logic
        validatePostTweet(tweet);

        //create a tweet via dao
        return (Tweet) dao.create(tweet);
    }

    /**
     * check if tweet text is empty or exceeds the maximum length and check if coordinages are out of range
     *
     * @param tweet
     */
    private void validatePostTweet(Tweet tweet) {
        int lengthTweet =  tweet.getText().length();
        Float lon = tweet.getCoordinates().getCoordinates().get(0);
        Float lat = tweet.getCoordinates().getCoordinates().get(1);

        //check if the tweet is empty
        if (tweet == null) {
            throw new NullPointerException("Tweet if empty");
        }

        //check if text exceeds the maximum length 140
        if (lengthTweet > 140) {
            throw new IllegalArgumentException("Tweet text exceeds 140 characters!");
        }

        //check if coordinates are out of range
        if (lon <= -180 || lon >= 80) {
            throw new IllegalArgumentException("Longitude is out of range");
        }
        if (lat <= -90 || lat >= 90) {
            throw new IllegalArgumentException("Latitude is out of range");
        }

    }

    /**
     * find a tweet by id
     *
     * @param id tweet id
     * @param fields set fields not in the list to null
     * @return
     */
    @Override
    public Tweet showTweet(String id, String[] fields) {
        if (id == null) {
            throw new NullPointerException("No Id Provided!");
        }

//        if (!id.matches("[0-9]+]")) {
//            throw new IllegalArgumentException("Incorrect ID formate: " + id);
//        }

        try {
            long idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect ID format : " + id);
        }

        return (Tweet) dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> deletedTweet = new ArrayList<Tweet>();
        for(String id : ids){
            if(!id.matches("[0-9]+")){
                throw new IllegalArgumentException("ID is not in correct format");
            }else{
                deletedTweet.add((Tweet) dao.deleteById(id));
            }
        }
        return deletedTweet;
    }
}
