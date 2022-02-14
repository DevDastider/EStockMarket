package com.cts.stockmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason= "User Id already exists")
public class UserIdAlreadyExistsException extends Exception {

}
