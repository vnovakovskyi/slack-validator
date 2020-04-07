package novakovskyi.slack.validator.api.POJO.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelCreate {

    public ChannelCreate(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    private String name;
}
