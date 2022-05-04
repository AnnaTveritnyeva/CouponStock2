package myProject.Coupons_Project.controller;


import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.beans.Role;
import myProject.Coupons_Project.exeptions.CouponsExceptions;
import myProject.Coupons_Project.exeptions.UnauthorizedException;
import myProject.Coupons_Project.jwt.JWT;
import myProject.Coupons_Project.sevices.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("company")
@RequiredArgsConstructor
@CrossOrigin
public class CompanyController {
    private final CompanyService companyService;
    private final JWT jwt;

    @PostMapping("addCoupon")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponsExceptions, UnauthorizedException {
        authorize(token);
        companyService.addCoupon(coupon);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @PutMapping("updateCoupon")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws CouponsExceptions, UnauthorizedException {
        authorize(token);
        companyService.updateCoupon(coupon);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @DeleteMapping("deleteCoupon/{couponId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws CouponsExceptions, UnauthorizedException {
        authorize(token);
        companyService.deleteCoupon(couponId);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @GetMapping("getCompanyCoupons")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(companyService.getCompanyCoupons());
    }

    @GetMapping("getCompanyCouponsByCategory/{category}")
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(companyService.getCompanyCoupons(category));
    }

    @GetMapping("getCompanyCouponsByMaxPrice/{maxPrice}")
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable Double maxPrice) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(companyService.getCompanyCoupons(maxPrice));
    }

    @GetMapping("getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization") String token) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(companyService.getCompanyDetails());
    }

    //private methods:
    private void authorize(String token) throws UnauthorizedException {
        if (!jwt.validateTokenAndRole(token, Role.COMPANY)) {
            throw new UnauthorizedException();
        }
    }
}

