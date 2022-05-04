package myProject.Coupons_Project.sevices.serviceModels;

import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.beans.Customer;
import myProject.Coupons_Project.exeptions.PurchaseExceptions;

import java.util.List;

public interface CustomerServiceModel extends Loginable {
    @Override
    boolean login(String email, String password);
    void purchaseCoupon(Coupon coupon) throws PurchaseExceptions;
    List<Coupon> getCustomerCoupons();
    List<Coupon> getCustomerCoupons(Category category);
    List<Coupon> getCustomerCoupons(Double maxPrice);
    Customer getCustomerDetails();
}
