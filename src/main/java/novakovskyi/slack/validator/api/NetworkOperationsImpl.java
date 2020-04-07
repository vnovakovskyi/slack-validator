package novakovskyi.slack.validator.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

public class NetworkOperationsImpl implements NetworkOperations {
    private String token;
    private HttpURLConnection connection;

    NetworkOperationsImpl(String token) {
        this.token = token;
    }

    public void createConnection(String link) {
        try {
            URL url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
        } catch (IOException e) {
            connection.disconnect();
            e.printStackTrace();
        }
    }

    public void writeBody(String body) {
        try {
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(body);
            osw.flush();
            osw.close();
            os.close();
        } catch (IOException e) {
            connection.disconnect();
            e.printStackTrace();
        }
    }

    public String getResponse() {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        int status;

        try {
            status = connection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
    }

    public boolean isValidResponse(String response) throws JsonProcessingException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = null;
        rootNode = mapper.readTree(response);

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getKey().equals("ok")) {
                return Boolean.parseBoolean(field.getValue().toString());
            }
        }
        return false;
    }
}
