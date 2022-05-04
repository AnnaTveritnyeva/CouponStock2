package myProject.Coupons_Project.clr;

import myProject.Coupons_Project.utils.ArtUtils;
import myProject.Coupons_Project.utils.TablePrinter;
import myProject.Coupons_Project.beans.*;
import myProject.Coupons_Project.repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static myProject.Coupons_Project.utils.RestTmpltUtils.*;
import static myProject.Coupons_Project.utils.URLUtils.*;

@Component
@Order(3)
public class TestCustomer implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CouponRepo couponRepo;

    @Autowired
    @Qualifier("customer")
    private UserModel userModel;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.CUSTOMERS);
        final String CUSTOMER = "\n\nCUSTOMER - ";
        final String WRONG = ": wrong details";

        //login
        System.out.println(CUSTOMER + LOGIN);
        //ResponseEntity<String> customerHeaderResponse = login(userModel);
        HttpHeaders customerHeader = getHeader(login(userModel));

        //couponPurchase
        try {
            System.out.println(CUSTOMER + PURCHASE_COUPON);
            addCouponPurchase(getCoupon(3), customerHeader);

            System.out.println("\n" + PURCHASE_COUPON + WRONG);
            addCouponPurchase(getCustomerCoupons(customerHeader).get(0), customerHeader);

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println(e.getMessage());
        }

        //getCustomerCoupons
        System.out.println(CUSTOMER + GET_CUSTOMER_COUPONS);
        TablePrinter.print(getCustomerCoupons(customerHeader));
        System.out.println(CUSTOMER + GET_CUSTOMER_COUPONS_BY_CATEGORY);
        TablePrinter.print(getCustomerCouponsByCategory(Category.VACATION, customerHeader));
        System.out.println(CUSTOMER + GET_CUSTOMER_COUPONS_BY_MAX_PRICE);
        TablePrinter.print(getCustomerCouponsByMaxPrice(1000.0, customerHeader));

        //getCustomerDetails:
        System.out.println(CUSTOMER + GET_CUSTOMER_DETAILS);
        TablePrinter.print(getCustomerDetails(customerHeader));


    }

    private Coupon getCoupon(int index) {
        return couponRepo.findAll().get(index);
    }

    private ResponseEntity<String> login(UserModel userModel) {
        return restTemplate.exchange(
                LOGIN.getUrl(),
                HttpMethod.POST,
                getHttpEntity(userModel),
                String.class
        );
    }

    private void addCouponPurchase(Coupon coupon, HttpHeaders customerHeader) {
        restTemplate.put(PURCHASE_COUPON.getUrl(),
                getHttpEntity(coupon, customerHeader));
    }

    private List<Coupon> getCustomerCoupons(HttpHeaders customerHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_CUSTOMER_COUPONS.getUrl(),
                HttpMethod.GET,
                getHttpEntity(customerHeader),
                Coupon[].class
        ).getBody()));
    }

    private List<Coupon> getCustomerCouponsByCategory(Category category, HttpHeaders customerHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_CUSTOMER_COUPONS_BY_CATEGORY.getUrl(),
                HttpMethod.GET,
                getHttpEntity(customerHeader),
                Coupon[].class,
                getParams("category", category)
        ).getBody()));
    }

    private List<Coupon> getCustomerCouponsByMaxPrice(Double maxPrice, HttpHeaders customerHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_CUSTOMER_COUPONS_BY_MAX_PRICE.getUrl(),
                HttpMethod.GET,
                getHttpEntity(customerHeader),
                Coupon[].class,
                getParams("maxPrice", maxPrice)
        ).getBody()));
    }

    private Customer getCustomerDetails(HttpHeaders customerHeader) {
        return restTemplate.exchange(
                GET_CUSTOMER_DETAILS.getUrl(),
                HttpMethod.GET,
                getHttpEntity(customerHeader),
                Customer.class
        ).getBody();
    }
}

