package novakovskyi.slack.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import novakovskyi.slack.validator.api.POJO.Channel;
import novakovskyi.slack.validator.api.SlackApiClient;

public class Main {
    private static String token = "YOUR TOKEN";
    private static String channelName = "nameewq";

    public static void main(String[] args) {
        SlackApiClient api = new SlackApiClient(token);
        try {
            Channel channel = api.createChannel(channelName);
            api.sendMessage(channel.getId());
            api.archiveChannel(channel.getId());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(api.getResults().toString());
    }
}
