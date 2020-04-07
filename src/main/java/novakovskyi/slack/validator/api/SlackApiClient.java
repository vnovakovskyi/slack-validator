package novakovskyi.slack.validator.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import novakovskyi.slack.validator.api.POJO.Channel;
import novakovskyi.slack.validator.api.POJO.SlackResponse;
import novakovskyi.slack.validator.api.POJO.request.ChannelArchive;
import novakovskyi.slack.validator.api.POJO.request.ChannelCreate;
import novakovskyi.slack.validator.api.POJO.request.MessageSend;

import java.util.ArrayList;

public class SlackApiClient implements SlackApi {
    private NetworkOperationsImpl networkOperations;
    private ObjectMapper mapper = new ObjectMapper();
    private ArrayList<String> results = new ArrayList<>();
    private int tryCounter = 0;

    public SlackApiClient(String token) {
        networkOperations = new NetworkOperationsImpl(token);
    }

    public Channel createChannel(String channelName) throws JsonProcessingException {
        networkOperations.createConnection("https://slack.com/api/conversations.create");
        networkOperations.writeBody(mapper.writeValueAsString(new ChannelCreate(channelName)));
        String response = networkOperations.getResponse();

        if (networkOperations.isValidResponse(response)) {
            results.add("Creating channel is success");
            return mapper.readValue(response, SlackResponse.class).getChannel();
        }

        if (tryCounter < 1) {
            tryCounter++;
            return createChannel(channelName);
        }
        tryCounter = 0;
        results.add("Creating channel failed");
        return new Channel();
    }

    public void sendMessage(String channelId) throws JsonProcessingException {
        networkOperations.createConnection("https://slack.com/api/chat.postMessage");
        networkOperations.writeBody(mapper.writeValueAsString(new MessageSend(channelId, "something urgent")));
        String response = networkOperations.getResponse();

        if (networkOperations.isValidResponse(response)) {
            results.add("Sending message is success");
            return;
        }
        if (tryCounter < 1) {
            tryCounter++;
            sendMessage(channelId);
            return;
        }
        tryCounter = 0;
        results.add("Sending message failed");
    }

    public void archiveChannel(String channelId) throws JsonProcessingException {
        networkOperations.createConnection("https://slack.com/api/conversations.archive");
        networkOperations.writeBody(mapper.writeValueAsString(new ChannelArchive(channelId)));
        String response = networkOperations.getResponse();

        if (networkOperations.isValidResponse(response)) {
            results.add("Archive channel is success");
            return;
        }
        if (tryCounter < 1) {
            tryCounter++;
            archiveChannel(channelId);
            return;
        }
        tryCounter = 0;
        results.add("Archive channel failed");
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
