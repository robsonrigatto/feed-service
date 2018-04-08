package br.com.rr.feed.jackson;

import br.com.rr.feed.enumeration.DescriptionType;
import br.com.rr.feed.vo.ItemDescriptionVO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemDescriptionDeserializer extends JsonDeserializer<ItemDescriptionVO> {

    @Override
    public ItemDescriptionVO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        DescriptionType type = DescriptionType.valueOf(node.get("type").asText().toUpperCase());
        Serializable content;

        if(type.equals(DescriptionType.LINKS)) {
            Iterator<JsonNode> contentIterator = node.get("content").iterator();
            List<String> links = new ArrayList<>();

            while(contentIterator.hasNext()) {
                links.add(contentIterator.next().asText());
            }

            content = (Serializable) links;

        } else {
            content = node.get("content").asText();
        }

        return new ItemDescriptionVO(type, content);
    }
}
