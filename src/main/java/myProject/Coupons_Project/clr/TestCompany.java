package myProject.Coupons_Project.clr;

import myProject.Coupons_Project.utils.ArtUtils;

import static myProject.Coupons_Project.utils.RestTmpltUtils.*;
import static myProject.Coupons_Project.utils.URLUtils.*;

import myProject.Coupons_Project.utils.TablePrinter;
import myProject.Coupons_Project.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Order(2)
public class TestCompany implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("company")
    private UserModel userModel;

    @Autowired
    List<Coupon> coupons;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.COMPANIES);

        final String COMPANY = "\n\nCOMPANY - ";
        final String WRONG = ": wrong details";

        //login
        System.out.println(COMPANY + LOGIN);
        //ResponseEntity<String> companyHeaderResponse = login(userModel);
        HttpHeaders companyHeader = getHeader(login(userModel));

        //addCoupon
        try {
            System.out.println(COMPANY + ADD_COUPON);
            addCoupon(coupons.get(5), companyHeader);
            addCoupon(coupons.get(6), companyHeader);
            addCoupon(coupons.get(7), companyHeader);
            addCoupon(coupons.get(8), companyHeader);
            addCoupon(coupons.get(9), companyHeader);


            System.out.println("\n" + ADD_COUPON + WRONG);
            addCoupon(coupons.get(9), companyHeader);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }

        //updateCoupon
        try {
            System.out.println(COMPANY + UPDATE_COUPON);
            Coupon coupon = getCompanyCoupons(companyHeader).get(4);
            coupon.setPrice(45.5);
            updateCoupon(coupon, companyHeader);

            System.out.println("\n" + UPDATE_COUPON + WRONG);
            coupon.setCompanyId(10);
            updateCoupon(coupon, companyHeader);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }

        //deleteCoupon
        try {
            System.out.println(COMPANY + DELETE_COUPON);
            deleteCoupon(5, companyHeader);

            System.out.println("\n" + DELETE_COUPON + WRONG);
            deleteCoupon(5, companyHeader);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }

        //getCompanyCoupons
        System.out.println(COMPANY + GET_COMPANY_COUPONS);
        TablePrinter.print(getCompanyCoupons(companyHeader));

        System.out.println(COMPANY + GET_COMPANY_COUPONS_BY_CATEGORY);
        TablePrinter.print(getCompanyCouponsByCategory(Category.FOOD, companyHeader));

        System.out.println(COMPANY + GET_COMPANY_COUPONS_BY_MAX_PRICE);
        TablePrinter.print(getCompanyCouponsByMaxPrice(100.0, companyHeader));

        System.out.println(COMPANY + GET_COMPANY_DETAILS);
        TablePrinter.print(getCompanyDetails(companyHeader));

    }

    private ResponseEntity<String> login(UserModel userModel) {
        return restTemplate.exchange(
                LOGIN.getUrl(),
                HttpMethod.POST,
                getHttpEntity(userModel),
                String.class
        );
    }

    private void addCoupon(Coupon coupon, HttpHeaders companyHeader) {
        restTemplate.postForEntity(ADD_COUPON.getUrl(),
                getHttpEntity(coupon, companyHeader),
                Coupon.class);
    }

    private void deleteCoupon(int couponId, HttpHeaders companyHeader) {
        restTemplate.exchange(DELETE_COUPON.getUrl(),
                HttpMethod.DELETE,
                getHttpEntity(companyHeader),
                String.class,
                getParams("couponId", couponId));
    }

    private void updateCoupon(Coupon coupon, HttpHeaders companyHeader) {
        restTemplate.put(UPDATE_COUPON.getUrl(),
                getHttpEntity(coupon, companyHeader));
    }

    private List<Coupon> getCompanyCoupons(HttpHeaders companyHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_COMPANY_COUPONS.getUrl(),
                HttpMethod.GET,
                getHttpEntity(companyHeader),
                Coupon[].class
        ).getBody()));
    }

    private List<Coupon> getCompanyCouponsByCategory(Category category, HttpHeaders companyHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_COMPANY_COUPONS_BY_CATEGORY.getUrl(),
                HttpMethod.GET,
                getHttpEntity(companyHeader),
                Coupon[].class,
                getParams("category", category)
        ).getBody()));
    }

    private List<Coupon> getCompanyCouponsByMaxPrice(Double maxPrice, HttpHeaders companyHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_COMPANY_COUPONS_BY_MAX_PRICE.getUrl(),
                HttpMethod.GET,
                getHttpEntity(companyHeader),
                Coupon[].class,
                getParams("maxPrice", maxPrice)
        ).getBody()));
    }

    private Company getCompanyDetails(HttpHeaders companyHeader) {
        return restTemplate.exchange(
                GET_COMPANY_DETAILS.getUrl(),
                HttpMethod.GET,
                getHttpEntity(companyHeader),
                Company.class
        ).getBody();
    }
}

