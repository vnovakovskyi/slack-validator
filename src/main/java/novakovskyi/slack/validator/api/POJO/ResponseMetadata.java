package novakovskyi.slack.validator.api.POJO;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMetadata {

    @JsonIgnore
    private String nextCursor;
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
}