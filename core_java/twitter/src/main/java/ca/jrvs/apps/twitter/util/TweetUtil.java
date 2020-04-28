package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetUtil {

    /**
     * create a new tweet with text and coordinates
     *
     * @param text status
     * @param lon longitude
     * @param lat latitude
     * @return a nre tweet
     */
    public static Tweet createTweet(String text, String hashtags, Float lon, Float lat) {
        String status = text + hashtags + System.currentTimeMillis();

        List<Float> lonLat = new ArrayList<>();
        lonLat.add(lon);
        lonLat.add(lat);

        Coordinates coordinates = new Coordinates(lonLat, "Point");

        Tweet tweet = new Tweet(status, coordinates);
        return tweet;

    }

}
