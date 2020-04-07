package novakovskyi.slack.validator.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import novakovskyi.slack.validator.api.POJO.Channel;

public interface SlackApi {
    Channel createChannel(String name) throws JsonProcessingException;
    void sendMessage(String channelId) throws JsonProcessingException;
    void archiveChannel(String channelId) throws JsonProcessingException;
}
