package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    @Autowired
    public TwitterController (Service service) {this.service = service; }

    /**
     * Parse user argument and post a tweet by calling service class
     *
     * @param args Text, lat, lon
     * @return post a tweet
     * @throws IllegalArgumentException fi the args are invalid
     */
    @Override
    public Tweet postTweet(String[] args) {

        if(args.length != 3){
            throw new IllegalArgumentException(
                    "USAGE: TwitterCLIApp post \"text\" \"latitude:longitude\"");
        }

        String text = args[1];
        String coordinate = args[2];
        String[] coorArray = coordinate.split(COORD_SEP);

        if(coorArray.length != 2 || text.isEmpty() == true){
            throw new IllegalArgumentException(
                    "Invalid format\nUSAGE: TwitterCLIApp post \"text\" \"latitude:longitude\"");
        }
        Float lon = null;
        Float lat = null;
        try{
            lon = Float.parseFloat(coorArray[0]);
            lat = Float.parseFloat(coorArray[1]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid location format", e);
        }

        Tweet postTweet = TweetUtil.createTweet(text, lon, lat);
        return service.postTweet(postTweet);
    }

    @Override
    public Tweet showTweet(String[] args){
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"id\" \"options..\"");
        }
        String id = args[1];
        String fields = args[2];
        String[] fieldArray = fields.split(COMMA);
        return service.showTweet(id, fieldArray);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args){
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"id\" \"options..\"");
        }
        String ids = args[1];
        String[] idArray = ids.split(COMMA);
        return service.deleteTweets(idArray);
    }

}
