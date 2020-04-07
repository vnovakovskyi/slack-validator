package novakovskyi.slack.validator.api.POJO.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageSend {

    public MessageSend(String channel, String text) {
        this.channel = channel;
        this.text = text;
    }

    @JsonProperty("channel")
    private String channel;
    @JsonProperty("text")
    private String text;
}
