package de.caritas.cob.MessageService.api.service;

import static net.therore.logback.EventMatchers.text;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.PrintWriter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import de.caritas.cob.MessageService.api.exception.RocketChatGetGroupMessagesException;
import net.therore.logback.LogbackRule;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {

  private final String ERROR_MESSAGE = "error";
  private final String RC_SERVICE_ERROR_TEXT = "Rocket.Chat service error: ";
  private final String INTERNAL_SERVER_ERROR_TEXT = "Internal Server Error: ";
  private final String BAD_REQUEST_TEXT = "Bad Request: ";

  @InjectMocks
  private LogService logService;

  @Mock
  private RocketChatGetGroupMessagesException rocketChatGetGroupMessagesException;
  @Mock
  Exception exception;

  @Rule
  public LogbackRule rule = new LogbackRule();

  @Before
  public void setup() {
    logService = new LogService();

  }

  /**
   * 
   * Tests for method: logRocketChatServiceError
   *
   */

  @Test
  public void logRocketChatServiceError_Should_LogExceptionStackTrace() {

    logService.logRocketChatServiceError(rocketChatGetGroupMessagesException);
    verify(rocketChatGetGroupMessagesException, atLeastOnce())
        .printStackTrace(any(PrintWriter.class));

  }

  @Test
  public void logRocketChatServiceError_Should_LogErrorMessage() {

    logService.logRocketChatServiceError(ERROR_MESSAGE);
    verify(rule.getLog(), times(1)).contains(argThat(text(RC_SERVICE_ERROR_TEXT + ERROR_MESSAGE)));
  }

  @Test
  public void logRocketChatServiceError_Should_LogErrorMessageAndExceptionStackTrace() {

    logService.logRocketChatServiceError(ERROR_MESSAGE, rocketChatGetGroupMessagesException);
    verify(rule.getLog(), times(1)).contains(argThat(text(RC_SERVICE_ERROR_TEXT + ERROR_MESSAGE)));
    verify(rocketChatGetGroupMessagesException, atLeastOnce())
        .printStackTrace(any(PrintWriter.class));
  }

  /**
   * 
   * Tests for method: logUserServiceHelperError
   *
   */

  @Test
  public void logUserServiceHelperError_Should_LogExceptionStackTrace() {

    logService.logUserServiceHelperError(exception);
    verify(exception, atLeastOnce()).printStackTrace(any(PrintWriter.class));

  }

  /**
   * 
   * Tests for method: logInfo
   *
   */

  @Test
  public void logInfo_Should_LogMessage() {

    logService.logInfo(ERROR_MESSAGE);
    verify(rule.getLog(), times(1)).contains(argThat(text(ERROR_MESSAGE)));
  }

  /**
   * 
   * Tests for method: logEncryptionServiceError
   *
   */

  @Test
  public void logEncryptionServiceError_Should_LogExceptionStackTrace() {

    logService.logEncryptionServiceError(exception);
    verify(exception, atLeastOnce()).printStackTrace(any(PrintWriter.class));

  }

  /**
   * 
   * Tests for method: logEncryptionPossibleBadKeyError
   *
   */

  @Test
  public void logEncryptionPossibleBadKeyError_Should_LogExceptionStackTrace() {

    logService.logEncryptionPossibleBadKeyError(exception);
    verify(exception, atLeastOnce()).printStackTrace(any(PrintWriter.class));
  }

  /**
   * 
   * Tests for method: logRocketChatBadRequestError
   *
   */

  @Test
  public void logRocketChatBadRequestError_Should_LogExceptionStackTrace() {

    logService.logRocketChatBadRequestError(exception);
    verify(exception, atLeastOnce()).printStackTrace(any(PrintWriter.class));
  }

  /**
   * 
   * Tests for method: logInternalServerError
   *
   */

  @Test
  public void logInternalServerError_Should_LogErrorMessageAndExceptionStackTrace() {

    logService.logInternalServerError(ERROR_MESSAGE, exception);
    verify(rule.getLog(), times(1))
        .contains(argThat(text(INTERNAL_SERVER_ERROR_TEXT + ERROR_MESSAGE)));
    verify(exception, atLeastOnce()).printStackTrace(any(PrintWriter.class));
  }

  /**
   * 
   * Tests for method: logInfo
   *
   */

  @Test
  public void logBadRequest_Should_LogMessage() {

    logService.logBadRequest(ERROR_MESSAGE);
    verify(rule.getLog(), times(1)).contains(argThat(text(BAD_REQUEST_TEXT + ERROR_MESSAGE)));
  }
}