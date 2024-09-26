package de.caritas.cob.messageservice;

import static org.junit.Assert.assertNull;

import de.caritas.cob.messageservice.config.AuthenticatedUserConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AuthenticatedUserConfigTest {

  @MockBean
  AuthenticatedUserConfig authenticatedUserConfig;

  @Test
  public void getAuthenticatedUser_Should_ReturnNullWhenNoUserSessionActive() {
    assertNull(authenticatedUserConfig.getAuthenticatedUser());
  }
}
