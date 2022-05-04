package myProject.Coupons_Project.controller;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.exeptions.PurchaseExceptions;
import myProject.Coupons_Project.exeptions.UnauthorizedException;
import myProject.Coupons_Project.jwt.JWT;
import myProject.Coupons_Project.beans.Role;
import myProject.Coupons_Project.sevices.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final JWT jwt;

    @PutMapping("couponPurchase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> couponPurchase(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws PurchaseExceptions, UnauthorizedException {
        authorize(token);
        customerService.purchaseCoupon(coupon);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @GetMapping("getCustomerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(customerService.getCustomerCoupons());
    }

    @GetMapping("getCustomerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(customerService.getCustomerCoupons(category));
    }

    @GetMapping("getCustomerCouponsByMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable Double maxPrice) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(customerService.getCustomerCoupons(maxPrice));
    }

    @GetMapping("getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization") String token) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(customerService.getCustomerDetails());
    }

    private void authorize(String token) throws UnauthorizedException {
        if (!jwt.validateTokenAndRole(token, Role.CUSTOMER)) {
            throw new UnauthorizedException();
        }
    }

}
