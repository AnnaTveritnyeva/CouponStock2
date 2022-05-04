package myProject.Coupons_Project.aop;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.repositories.CouponRepo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class UpdateCouponAmountAspect {
    private final CouponRepo couponRepo;

    /**
     * updates coupon amount after successful purchase
     * @param coupon Coupon
     */
    @AfterReturning("@annotation(UpdateCouponAmount) && args(coupon)")
    public void updateCouponAmount(Coupon coupon){
        coupon.setAmount(coupon.getAmount()-1);
        couponRepo.saveAndFlush(coupon);
    }
}
