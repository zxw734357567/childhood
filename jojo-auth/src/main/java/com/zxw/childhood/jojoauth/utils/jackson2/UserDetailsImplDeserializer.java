package com.zxw.childhood.jojoauth.utils.jackson2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.zxw.childhood.jojoauth.model.pojo.TRole;
import com.zxw.childhood.jojoauth.model.vo.RoleVO;
import com.zxw.childhood.jojoauth.utils.UserDetailsImpl;

import java.io.IOException;
import java.util.List;
/**
 * @author zxw
 * @date 2020-05-28 9:48
 */
public class UserDetailsImplDeserializer extends JsonDeserializer<UserDetailsImpl> {

    public UserDetailsImplDeserializer(){}

    @Override
    public UserDetailsImpl deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        JsonNode password = readJsonNode(jsonNode, "password");
        JsonNode userId = readJsonNode(jsonNode, "userId");
        JsonNode username = readJsonNode(jsonNode, "userName");
        JsonNode isEnable = readJsonNode(jsonNode, "isEnable");
        List<TRole> roleVoList = mapper.convertValue(jsonNode.get("tRoles"), new TypeReference<List<TRole>>() {
        });
        UserDetailsImpl result =  new UserDetailsImpl();
        result.setUserId(userId.asText());
        result.setUsername(username.asText());
        result.setPassword(password.asText());
        result.setIsEnable(isEnable.asInt());
        result.settRoles(roleVoList);
        return result;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
