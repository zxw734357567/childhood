package com.zxw.childhood.passport.utils.jackson2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.zxw.childhood.passport.common.auth.PassportAuthentication;
import com.zxw.childhood.passport.common.auth.PassportAuthenticationDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;

/**
 * @author zxw
 * @date 2020-06-24 16:40
 */
public class PassportAuthenticationDeserializer  extends JsonDeserializer<PassportAuthentication> {
    /**
     * Method that can be called to ask implementation to deserialize
     * JSON content into the value type this serializer handles.
     * Returned instance is to be constructed by method itself.
     * <p>
     * Pre-condition for this method is that the parser points to the
     * first event that is part of value to deserializer (and which
     * is never JSON 'null' literal, more on this below): for simple
     * types it may be the only value; and for structured types the
     * Object start marker or a FIELD_NAME.
     * </p>
     * <p>
     * The two possible input conditions for structured types result
     * from polymorphism via fields. In the ordinary case, Jackson
     * calls this method when it has encountered an OBJECT_START,
     * and the method implementation must advance to the next token to
     * see the first field name. If the application configures
     * polymorphism via a field, then the object looks like the following.
     * <pre>
     *      {
     *          "@class": "class name",
     *          ...
     *      }
     *  </pre>
     * Jackson consumes the two tokens (the <tt>@class</tt> field name
     * and its value) in order to learn the class and select the deserializer.
     * Thus, the stream is pointing to the FIELD_NAME for the first field
     * after the @class. Thus, if you want your method to work correctly
     * both with and without polymorphism, you must begin your method with:
     * <pre>
     *       if (p.getCurrentToken() == JsonToken.START_OBJECT) {
     *         p.nextToken();
     *       }
     *  </pre>
     * This results in the stream pointing to the field name, so that
     * the two conditions align.
     * <p>
     * Post-condition is that the parser will point to the last
     * event that is part of deserialized value (or in case deserialization
     * fails, event that was not recognized or usable, which may be
     * the same event as the one it pointed to upon call).
     * <p>
     * Note that this method is never called for JSON null literal,
     * and thus deserializers need (and should) not check for it.
     *
     * @param p    Parsed used for reading JSON content
     * @param ctxt Context that can be used to access information about
     *             this deserialization activity.
     * @return Deserialized value
     */
    @Override
    public PassportAuthentication deserialize(JsonParser  jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        JsonNode authoritiesNode = readJsonNode(jsonNode, "authorities");
        JsonNode detailsNode = readJsonNode(jsonNode, "details");
        JsonNode principal = readJsonNode(jsonNode, "principal");
        JsonNode userId = readJsonNode(jsonNode, "userId");

        List<SimpleGrantedAuthority> authorities = null;
        if (authoritiesNode != null && !authoritiesNode.isMissingNode()) {
            authorities = mapper.convertValue(authoritiesNode,
                    new TypeReference<List<SimpleGrantedAuthority>>() {});
        }

        PassportAuthentication result =  new PassportAuthentication(authorities);
        result.setPrincipal(principal.asText());
        result.setUserId(userId.asInt());
        if (detailsNode != null && !detailsNode.isMissingNode()) {
            PassportAuthenticationDetails details = mapper.convertValue(detailsNode,
                    new TypeReference<PassportAuthenticationDetails>() {});
            result.setDetails(details);
        }
        return result;
    }


    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
