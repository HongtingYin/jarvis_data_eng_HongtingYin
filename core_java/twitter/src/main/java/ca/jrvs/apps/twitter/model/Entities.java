package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hashtags",
        "user_mentions"
})

public class Entities {
    @JsonProperty("hashtags")
    private List<Hashtag> hashtages;
    @JsonProperty("user_mentions")
    private List<UserMention> userMentions;

    public Entities(List<Hashtag> hashtages, List<UserMention> userMentions) {
        setHashtages(hashtages);
        setUserMentions(userMentions);
    }

    @JsonProperty("hashtags")
    public List<Hashtag> getHashtages() {
        return hashtages;
    }

    @JsonProperty("hashtags")
    public void setHashtages(List<Hashtag> hashtages) {
        this.hashtages = hashtages;
    }

    @JsonProperty("user_mentions")
    public List<UserMention> getUserMentions() {
        return userMentions;
    }

    @JsonProperty("user_mentions")
    public void setUserMentions(List<UserMention> userMentions) {
        this.userMentions = userMentions;
    }
}
