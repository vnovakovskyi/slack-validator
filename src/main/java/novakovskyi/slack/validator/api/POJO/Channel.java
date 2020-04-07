package novakovskyi.slack.validator.api.POJO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Boolean isChannel;
    @JsonIgnore
    private Boolean isGroup;
    @JsonIgnore
    private Boolean isIm;
    @JsonIgnore
    private Integer created;
    @JsonIgnore
    private Boolean isArchived;
    @JsonIgnore
    private Boolean isGeneral;
    @JsonIgnore
    private Integer unlinked;
    @JsonIgnore
    private String nameNormalized;
    @JsonIgnore
    private Boolean isShared;
    @JsonIgnore
    private Object parentConversation;
    @JsonIgnore
    private String creator;
    @JsonIgnore
    private Boolean isExtShared;
    @JsonIgnore
    private Boolean isOrgShared;
    @JsonIgnore
    private List<String> sharedTeamIds = null;
    @JsonIgnore
    private List<Object> pendingShared = null;
    @JsonIgnore
    private List<Object> pendingConnectedTeamIds = null;
    @JsonIgnore
    private Boolean isPendingExtShared;
    @JsonIgnore
    private Boolean isMember;
    @JsonIgnore
    private Boolean isPrivate;
    @JsonIgnore
    private Boolean isMpim;
    @JsonIgnore
    private Topic topic;
    @JsonIgnore
    private Purpose purpose;
    @JsonIgnore
    private List<Object> previousNames = null;
    @JsonIgnore
    private Integer numMembers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    private Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonIgnore
    private void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}