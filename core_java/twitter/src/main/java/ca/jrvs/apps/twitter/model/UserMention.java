package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "idStr",
        "indices",
        "name",
        "screenName"
})

public class UserMention {

    @JsonProperty("id")
    private long id;
    @JsonProperty("idStr")
    private String idStr;
    @JsonProperty("indices")
    private int[] indices;
    @JsonProperty("name")
    private String name;
    @JsonProperty("screenName")
    private String screenName;

    public UserMention(long id, String idStr, int[] indices, String name, String screenname) {
        setId(id);
        setIdStr(idStr);
        setIndices(indices);
        setName(name);
        setScreenName(screenName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
