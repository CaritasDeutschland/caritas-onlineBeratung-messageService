package de.caritas.cob.messageservice.api.model.rocket.chat.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Body object for Rocket.Chat API call to set a group read-only or writable.
 * See <a href="https://developer.rocket.chat/reference/api/rest-api/endpoints/rooms/groups-endpoints/setreadonly">groups.setReadOnly documentation</a>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetGroupReadOnlyDTO {

  private String roomId;
  private boolean readOnly;
}

