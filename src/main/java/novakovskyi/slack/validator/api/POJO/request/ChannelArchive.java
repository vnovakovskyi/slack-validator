package novakovskyi.slack.validator.api.POJO.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelArchive {

    public ChannelArchive(String channel) {
        this.channel = channel;
    }

    @JsonProperty("channel")
    private String channel;
}
