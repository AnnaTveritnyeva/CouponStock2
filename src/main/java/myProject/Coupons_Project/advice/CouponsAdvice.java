package myProject.Coupons_Project.advice;

import myProject.Coupons_Project.exeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CouponsAdvice {
    @ExceptionHandler(value = {CompanyExceptions.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleCompanyException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {CustomerExceptions.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleCustomerException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {CouponsExceptions.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleCouponException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {PurchaseExceptions.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handlePurchaseException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {LoginException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleLoginException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public String handleUnauthorizedException(Exception e){
        return e.getMessage();
    }

}
