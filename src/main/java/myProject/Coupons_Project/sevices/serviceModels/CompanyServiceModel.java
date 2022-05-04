package myProject.Coupons_Project.sevices.serviceModels;

import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.exeptions.CouponsExceptions;

import java.util.List;

public interface CompanyServiceModel extends Loginable {
    @Override
    boolean login(String email, String password);

    void addCoupon(Coupon coupon) throws CouponsExceptions;

    void updateCoupon(Coupon coupon) throws CouponsExceptions;

    void deleteCoupon(int couponId) throws CouponsExceptions;

    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(Category category);

    List<Coupon> getCompanyCoupons(Double maxPrice);

    Company getCompanyDetails();
}
