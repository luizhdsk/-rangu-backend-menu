package com.puc.campinas.rangubackendmenu.config.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.grcosta.messagelocator.domain.Error;
import com.grcosta.messagelocator.domain.Service;
import com.grcosta.messagelocator.domain.ServiceMessage;
import com.grcosta.messagelocator.exception.ServiceException;
import com.grcosta.messagelocator.interfaces.MessageService;
import com.puc.campinas.rangubackendmenu.config.Messages;
import com.puc.campinas.rangubackendmenu.config.exception.DishException;
import feign.FeignException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

  @Value("${message-locator.service-name}")
  private String serviceName;

  @Value("${message-locator.service-id}")
  private Integer serviceId;

  private final MessageService messageService;

  public ExceptionHandlerAdvice(MessageService messageService) {
    this.messageService = messageService;
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseAdvice> handleException(Exception exception) {
    log.error("Exception Handler", exception);
    ResponseAdvice body = ResponseAdvice.defaultInternal();
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    try {
      String exceptionMessage = exception.getCause().getMessage();
      Error error = new Error(exceptionMessage, "");
      Service service = new Service(Integer.toString(serviceId), serviceName);
      ServiceException serviceException = new ServiceException(exception, service, error);

      // Get ServiceMessage
      ServiceMessage serviceMessage = messageService.getServiceMessage(serviceException);

      // Response
      httpStatus = HttpStatus.valueOf(serviceMessage.getHttpStatus());
      body.setCode(serviceMessage.getCode());
      body.setDescription(serviceMessage.getDescription());
    } catch (Exception e) {
      log.error("Error", e.toString());
    }

    return ResponseEntity.status(httpStatus).body(body);
  }

  @ExceptionHandler(DishException.class)
  public ResponseEntity<ResponseAdvice> handleUserException(DishException exception) {
    log.error("User Handler", exception);

    HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    ResponseAdvice body = new ResponseAdvice();
    body.setCode(exception.getMessage());
    body.setDescription(httpStatus.getReasonPhrase());

    try {
      String exceptionMessage = exception.getMessage();
      Error error = new Error(exceptionMessage, "");
      Service service = new Service(Integer.toString(serviceId), serviceName);
      ServiceException serviceException = new ServiceException(exception, service, error);

      ServiceMessage serviceMessage = messageService.getServiceMessage(serviceException);

      httpStatus = HttpStatus.valueOf(serviceMessage.getHttpStatus());
      body.setCode(serviceMessage.getCode());
      body.setDescription(serviceMessage.getDescription());
    } catch (Exception e) {
      log.error("Error", e);
    }
    return ResponseEntity.status(httpStatus).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseAdvice> handleExceptionConstraint(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    val first = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
        .findFirst().get();
    val exceptionMessage = first.getDefaultMessage();
    HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;

    val body = new ResponseAdvice();
    body.setCode(exceptionMessage);
    body.setDescription(httpStatus.getReasonPhrase());

    try {
      val error = new Error(exceptionMessage, "");
      val service = new Service(Integer.toString(serviceId), serviceName);
      val serviceException = new ServiceException(methodArgumentNotValidException, service, error);

      // Get ServiceMessage
      val serviceMessage = messageService.getServiceMessage(serviceException);

      // Response
      httpStatus = HttpStatus.valueOf(serviceMessage.getHttpStatus());
      body.setCode(serviceMessage.getCode());
      body.setDescription(serviceMessage.getDescription());
    } catch (Exception e) {
      log.error("Error", e);
    }

    return ResponseEntity.status(httpStatus).body(body);
  }

  @ExceptionHandler(FeignException.class)
  public ResponseEntity<ResponseAdvice> handleFeignStatusException(FeignException feignException,
      HttpServletResponse response) {
    response.setStatus(feignException.status());
    Map<String, Object> feignClientError = new JSONObject(feignException.contentUTF8()).toMap();

    String httpStatusFeignClient = feignClientError.get("code").toString();
    String descriptionFeignClient = feignClientError.get("description").toString();

    ResponseAdvice body = ResponseAdvice.defaultInternal();
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    try {
      // Response
      httpStatus = HttpStatus.valueOf(feignException.status());
      body.setCode(httpStatusFeignClient);
      body.setDescription(descriptionFeignClient);
    } catch (Exception e) {
      log.error("Error", e.toString());
    }

    return ResponseEntity.status(httpStatus).body(body);
  }

}
