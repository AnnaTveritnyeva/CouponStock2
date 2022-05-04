package myProject.Coupons_Project.configurations;

import myProject.Coupons_Project.utils.DateUtils;
import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Coupon;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouponConfiguration {
    @Bean
    @Qualifier("coupon11")
    public Coupon coupon11() {
        return new Coupon(
                Category.FOOD,
                "BigMac",
                "10% discount!!",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                10,
                25.5,
                "https://www.mcdonalds.com/is/image/"
        );
    }

    @Bean
    @Qualifier("coupon12")
    public Coupon coupon12() {
        return new Coupon(
                Category.TECHNOLOGY,
                "Ice Cream machine",
                "5000 shekels discount",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                5,
                25_000.9,
                "https://www.mcdonalds.com/is/image/"
        );
    }

    @Bean
    @Qualifier("coupon13")
    public Coupon coupon13() {
        return new Coupon(
                Category.FASHION,
                "Meal for 2",
                "1+1",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                15,
                60.5,
                "https://www.mcdonalds.com/is/image/"
        );
    }

    @Bean
    @Qualifier("coupon14")
    public Coupon coupon14() {
        return new Coupon(
                Category.VACATION,
                "McDonald's Hotel",
                "15% discount",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                5,
                2000.0,
                "https://www.mcdonalds.com/is/image/"
        );

    }

    @Bean
    @Qualifier("coupon15")
    public Coupon coupon15() {
        return new Coupon(
                Category.EDUCATION,
                "Burger making lessons",
                "1000 shekels off!",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                35,
                3_000.0,
                "https://www.mcdonalds.com/is/image/"
        );

    }

    @Bean
    @Qualifier("coupon21")
    public Coupon coupon21() {
        return new Coupon(
                Category.FOOD,
                "Meal at apple",
                "10% discount!!",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                18,
                64.5,
                "https://res.cloudinary");
    }

    @Bean
    @Qualifier("coupon22")
    public Coupon coupon22() {
        return new Coupon(
                Category.TECHNOLOGY,
                "AirPods Pro",
                "100 shekels discount",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                5,
                800.90,
                "https://res.cloudinary"
        );

    }

    @Bean
    @Qualifier("coupon23")
    public Coupon coupon23() {
        return new Coupon(
                Category.FASHION,
                "Meal for 2",
                "1+1",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                15,
                60.5,
                "https://res.cloudinary"
        );

    }

    @Bean
    @Qualifier("coupon24")
    public Coupon coupon24() {
        return new Coupon(
                Category.VACATION,
                "Vacation on Apple",
                "buy for 10000$ and get free",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                5,
                100.0,
                "https://res.cloudinary"
        );
    }

    @Bean
    @Qualifier("coupon25")
    public Coupon coupon25() {
        return new Coupon(
                Category.EDUCATION,
                "App developer for Apple",
                "1000 shekels off!",
                DateUtils.getStartDate(),
                DateUtils.getEndDate(),
                35,
                13_000.0,
                "https://res.cloudinary"
        );

    }
}
