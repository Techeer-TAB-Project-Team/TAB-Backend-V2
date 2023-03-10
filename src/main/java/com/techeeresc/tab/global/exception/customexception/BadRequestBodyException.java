package com.techeeresc.tab.global.exception.customexception;

import com.techeeresc.tab.global.status.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestBodyException extends RuntimeException {
  private int errorCode;

  public BadRequestBodyException(String message, StatusCodes statusCodes) {
    super(message);
    this.errorCode = statusCodes.getStatusCode();
  }
}
