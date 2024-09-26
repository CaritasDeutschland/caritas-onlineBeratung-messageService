package de.caritas.cob.messageservice.api.model.jsondeserializer;

import static java.util.Objects.nonNull;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.caritas.cob.messageservice.api.helper.JSONHelper;
import de.caritas.cob.messageservice.api.helper.UserHelper;
import de.caritas.cob.messageservice.api.model.AliasMessageDTO;
import de.caritas.cob.messageservice.api.model.VideoCallMessageDTO;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 * Json Deserializer for the alias.
 */
public class AliasJsonDeserializer extends JsonDeserializer<AliasMessageDTO> {

  private final UserHelper userHelper;

  public AliasJsonDeserializer() {
    this.userHelper = new UserHelper();
  }

  public AliasJsonDeserializer(UserHelper userHelper) {
    this.userHelper = userHelper;
  }

  /**
   * Deserializes the Rocket.Chat custom alias object. The whole new {@link AliasMessageDTO} containing a
   * {@link VideoCallMessageDTO} will be transformed.
   *
   * @param jsonParser the json parser object containing the source object as a string
   * @param context    the current context
   * @return the generated/deserialized {@link AliasMessageDTO}
   */
  @Override
  public AliasMessageDTO deserialize(JsonParser jsonParser, DeserializationContext context)
      throws IOException {

    return getAliasMessageDTO(jsonParser.getValueAsString());
  }

  public AliasMessageDTO getAliasMessageDTO(String aliasValue) {
    if (StringUtils.isBlank(aliasValue)) {
      return null;
    }

    return buildAliasMessageDTOWithPossibleVideoCallMessageDTO(aliasValue);
  }

  private AliasMessageDTO buildAliasMessageDTOWithPossibleVideoCallMessageDTO(String aliasValue) {
    AliasMessageDTO alias = JSONHelper.convertStringToAliasMessageDTO(aliasValue).orElse(null);
    if (nonNull(alias)) {
      decodeUsernameOfVideoCallMessageDTOIfNonNull(alias);
    }
    return alias;
  }

  private void decodeUsernameOfVideoCallMessageDTOIfNonNull(AliasMessageDTO alias) {
    if (nonNull(alias.getVideoCallMessageDTO())) {
      alias.getVideoCallMessageDTO().setInitiatorUserName(
          userHelper.decodeUsername(alias.getVideoCallMessageDTO().getInitiatorUserName()));
    }
  }

}

