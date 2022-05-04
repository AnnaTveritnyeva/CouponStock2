package myProject.Coupons_Project.clr;

import myProject.Coupons_Project.utils.ArtUtils;
import myProject.Coupons_Project.utils.TablePrinter;

import static myProject.Coupons_Project.utils.URLUtils.*;

import myProject.Coupons_Project.beans.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Order(4)
public class TestGuest implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.GUEST);
        System.out.println("GUEST - " + GET_ALL_COUPONS);
        TablePrinter.print(getAllCoupons());
    }

    private List<Coupon> getAllCoupons() {
        return Arrays.asList(
                Objects.requireNonNull(restTemplate.getForEntity(
                        GET_ALL_COUPONS.getUrl(),
                        Coupon[].class
                ).getBody()));
    }
}
