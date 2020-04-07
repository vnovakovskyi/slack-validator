package novakovskyi.slack.validator.api.POJO;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackResponse {

    @JsonIgnore
    public Boolean ok;
    @JsonProperty("channel")
    public Channel channel;
    @JsonIgnore
    public String warning;
    @JsonIgnore
    public ResponseMetadata responseMetadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonIgnore
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Channel getChannel() {
        return channel;
    }
}