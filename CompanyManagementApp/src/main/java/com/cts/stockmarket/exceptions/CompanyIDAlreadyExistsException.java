package com.cts.stockmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason= "Company Code already exists")
public class CompanyIDAlreadyExistsException extends Exception {

}
