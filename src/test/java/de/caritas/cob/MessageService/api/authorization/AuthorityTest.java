package de.caritas.cob.MessageService.api.authorization;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import de.caritas.cob.MessageService.api.authorization.Authority;
import de.caritas.cob.MessageService.api.authorization.Role;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityTest {

  @Test
  public void getAuthoritiesByRoleName_Should_ReturnCorrectRoles_ForKeycloakRoleConsultant() {

    List<String> result = Authority.getAuthoritiesByRoleName(Role.CONSULTANT);

    assertNotNull(result);
    assertTrue(result.contains(Authority.CONSULTANT_DEFAULT));
    assertTrue(result.size() == 1);

  }

  @Test
  public void getAuthoritiesByRoleName_Should_ReturnCorrectRoles_ForKeycloakRoleUser() {

    List<String> result = Authority.getAuthoritiesByRoleName(Role.USER);

    assertNotNull(result);
    assertTrue(result.contains(Authority.USER_DEFAULT));
    assertTrue(result.size() == 1);

  }

  @Test
  public void getAuthoritiesByRoleName_Should_ReturnCorrectRoles_ForKeycloakRoleU25Consultant() {

    List<String> result = Authority.getAuthoritiesByRoleName(Role.U25_CONSULTANT);

    assertNotNull(result);
    assertTrue(result.contains(Authority.USE_FEEDBACK));
    assertTrue(result.size() == 1);

  }

  @Test
  public void getAuthoritiesByRoleName_Should_ReturnCorrectRoles_ForKeycloakRoleU25MainConsultant() {

    List<String> result = Authority.getAuthoritiesByRoleName(Role.U25_MAIN_CONSULTANT);

    assertNotNull(result);
    assertTrue(result.contains(Authority.VIEW_ALL_FEEDBACK_SESSIONS));
    assertTrue(result.contains(Authority.VIEW_ALL_PEER_SESSIONS));
    assertTrue(result.contains(Authority.ASSIGN_CONSULTANT_TO_SESSION));
    assertTrue(result.contains(Authority.ASSIGN_CONSULTANT_TO_ENQUIRY));
    assertTrue(result.contains(Authority.VIEW_AGENCY_CONSULTANTS));
    assertTrue(result.size() == 5);

  }

  @Test
  public void getAuthoritiesByRoleName_Should_ReturnCorrectRoles_ForKeycloakRoleTechnical() {

    List<String> result = Authority.getAuthoritiesByRoleName(Role.TECHNICAL);

    assertNotNull(result);
    assertTrue(result.contains(Authority.TECHNICAL_DEFAULT));
    assertTrue(result.size() == 1);

  }

}
