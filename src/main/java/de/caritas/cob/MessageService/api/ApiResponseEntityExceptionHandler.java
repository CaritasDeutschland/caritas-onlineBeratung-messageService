package de.caritas.cob.MessageService.api;

import java.net.UnknownHostException;
import javax.validation.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import de.caritas.cob.MessageService.api.exception.CustomCryptoException;
import de.caritas.cob.MessageService.api.exception.KeycloakException;
import de.caritas.cob.MessageService.api.exception.NoMasterKeyException;
import de.caritas.cob.MessageService.api.exception.RocketChatBadRequestException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Customizes API error/exception handling to hide information and/or possible security
 * vulnerabilities.
 *
 */
@Slf4j
@NoArgsConstructor
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * 
   * Handle all common "Bad Request" errors (400)
   * 
   */

  /**
   * Constraint violations
   * 
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler({ConstraintViolationException.class, RocketChatBadRequestException.class})
  public ResponseEntity<Object> handleBadRequest(final RuntimeException ex,
      final WebRequest request) {
    logWarning(ex);

    return handleExceptionInternal(null, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  /**
   * Incoming request body could not be deserialized
   */
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    logWarning(status, ex);

    return handleExceptionInternal(null, null, headers, status, request);
  }

  /**
   * @Valid on object fails validation
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    logWarning(status, ex);

    return handleExceptionInternal(null, null, headers, status, request);
  }

  /**
   * 409 - Conflict
   * 
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
  protected ResponseEntity<Object> handleConflict(final RuntimeException ex,
      final WebRequest request) {
    logWarning(HttpStatus.CONFLICT, ex);

    return handleExceptionInternal(null, null, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  /**
   * {@link RestTemplate} API client errors
   */
  @ExceptionHandler({HttpClientErrorException.class})
  protected ResponseEntity<Object> handleHttpClientException(final HttpClientErrorException ex,
      final WebRequest request) {
    logWarning(ex.getStatusCode(), ex);

    return handleExceptionInternal(null, null, new HttpHeaders(), ex.getStatusCode(), request);
  }

  /**
   * 500 - Internal Server Error
   * 
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class,
      IllegalStateException.class, ServiceException.class, KeycloakException.class,
      UnknownHostException.class, CustomCryptoException.class, NoMasterKeyException.class})
  public ResponseEntity<Object> handleInternal(final RuntimeException ex,
      final WebRequest request) {
    logError(ex);

    return handleExceptionInternal(null, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }

  /**
   * Logs an error
   * 
   * @param ex
   */
  private void logError(final Exception ex) {
    log.error("MessageService API: 500 Internal Server Error: {}",
        org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex));
  }

  /**
   * Logs a warning
   * 
   * @param status
   * @param ex
   */
  private void logWarning(final HttpStatus status, final Exception ex) {
    log.warn("MessageService API: {}: {}", status.getReasonPhrase(),
        org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex));
  }

  /**
   * Logs a warning
   * 
   * @param ex
   */
  private void logWarning(final Exception ex) {
    log.warn("MessageService API: {}: {}",
        org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex));
  }
}
