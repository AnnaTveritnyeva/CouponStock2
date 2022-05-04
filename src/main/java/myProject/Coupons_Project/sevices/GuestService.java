package myProject.Coupons_Project.sevices;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.repositories.CouponRepo;
import myProject.Coupons_Project.sevices.serviceModels.GuestServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestService implements GuestServiceModel {
    private final CouponRepo couponRepo;

    /**
     * returns all coupons
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll().stream().
                filter(coupon -> coupon.getAmount()>0).
                collect(Collectors.toList());
    }
}
