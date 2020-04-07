package novakovskyi.slack.validator.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface NetworkOperations {
    void createConnection(String link) throws IOException;
    void writeBody(String body) throws IOException;
    String getResponse() throws IOException;
    boolean isValidResponse(String response) throws JsonProcessingException;
}
