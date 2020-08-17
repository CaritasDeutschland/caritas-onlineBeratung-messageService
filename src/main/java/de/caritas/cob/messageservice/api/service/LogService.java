package de.caritas.cob.messageservice.api.service;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for logging
 */
public class LogService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);

  private static final String RC_SERVICE_ERROR = "Rocket.Chat service error: ";
  private static final String RC_ENCRYPTION_SERVICE_ERROR = "Encryption service error: ";
  private static final String RC_ENCRYPTION_BAD_KEY_SERVICE_ERROR =
      "Encryption service error - possible bad key error: ";
  private static final String USERSERVICE_HELPER_ERROR = "UserServiceHelper error: ";
  private static final String RC_BAD_REQUEST_ERROR = "Rocket.Chat Bad Request service error: ";
  private static final String INTERNAL_SERVER_ERROR_TEXT = "Internal Server Error: ";
  private static final String BAD_REQUEST_TEXT = "Bad Request: ";

  private LogService() {}

  /**
   * Logs a Rocket.Chat service error
   *
   * @param exception
   */
  public static void logRocketChatServiceError(Exception exception) {
    LOGGER.error(RC_SERVICE_ERROR + "{}", getStackTrace(exception));
  }

  /**
   * Logs a Rocket.Chat service error
   * 
   * @param message
   */
  public static void logRocketChatServiceError(String message) {
    LOGGER.error(RC_SERVICE_ERROR + "{}", message);
  }

  /**
   * Logs a Rocket.Chat service error
   *
   * @param exception
   */
  public static void logRocketChatServiceError(String message, Exception exception) {
    LOGGER.error(RC_SERVICE_ERROR + "{}", message);
    LOGGER.error(RC_SERVICE_ERROR + "{}",
        getStackTrace(exception));
  }

  /**
   * Logs a Encryption service error
   *
   * @param exception
   */
  public static void logEncryptionServiceError(Exception exception) {
    LOGGER.error(RC_ENCRYPTION_SERVICE_ERROR + "{}",
        getStackTrace(exception));
  }

  public static void logEncryptionPossibleBadKeyError(Exception exception) {
    LOGGER.error(RC_ENCRYPTION_BAD_KEY_SERVICE_ERROR + "{}",
        getStackTrace(exception));
  }

  /**
   * Logs a Rocket.Chat Bad Request error
   * 
   * @param exception
   */
  public static void logRocketChatBadRequestError(Exception exception) {
    LOGGER.error(RC_BAD_REQUEST_ERROR + "{}",
        getStackTrace(exception));
  }

  /**
   * Logs a UserServiceHelper error
   * 
   * @param exception
   */
  public static void logUserServiceHelperError(Exception exception) {
    LOGGER.error(USERSERVICE_HELPER_ERROR + "{}",
        getStackTrace(exception));
  }

  /**
   * Logs a Info message
   * 
   * @param msg The message
   */
  public static void logInfo(String msg) {
    LOGGER.info(msg);
  }

  /**
   * Internal Server Error/Exception
   * 
   * @param message
   * @param exception
   */
  public static void logInternalServerError(String message, Exception exception) {
    LOGGER.error("{}{}", INTERNAL_SERVER_ERROR_TEXT, message);
    LOGGER.error("{}", getStackTrace(exception));
  }

  /**
   * Internal Server Error/Exception
   *
   * @param exception
   */
  public static void logInternalServerError(Exception exception) {
    LOGGER.error("{}{}", INTERNAL_SERVER_ERROR_TEXT, getStackTrace(exception));
  }

  /**
   * Bad Request
   * 
   * @param message
   */
  public static void logBadRequest(String message) {
    LOGGER.error(BAD_REQUEST_TEXT + "{}", message);
  }

  /**
   * Logs a warning
   */
  public static void logWarning(final HttpStatus status, final Exception ex) {
    LOGGER.warn("MessageService API: {}: {}", status.getReasonPhrase(), getStackTrace(ex));
  }

  /**
   * Logs a warning
   */
  public static void logWarning(final Exception ex) {
    LOGGER.warn("MessageService API: {}:", getStackTrace(ex));
  }

  /**
   * Logs a debug message
   */
  public static void logDebug(final String message) {
    LOGGER.debug("MessageService API: {}:", message);
  }
}