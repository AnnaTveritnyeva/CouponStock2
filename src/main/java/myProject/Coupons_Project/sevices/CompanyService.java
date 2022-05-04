package myProject.Coupons_Project.sevices;

import lombok.RequiredArgsConstructor;

import static myProject.Coupons_Project.utils.MessageUtils.*;

import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Coupon;
import myProject.Coupons_Project.exeptions.CouponsExceptions;
import myProject.Coupons_Project.exeptions.ErrorMsg;
import myProject.Coupons_Project.repositories.*;
import myProject.Coupons_Project.sevices.serviceModels.CompanyServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService implements CompanyServiceModel {
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;

    private int companyId;


    /**
     * checks if provided login credentials are the same as required
     *
     * @param email    company's email
     * @param password company's password
     * @return boolean
     */
    @Override
    public boolean login(String email, String password) {
        boolean loggedIn;
        Company company = companyRepo.findByEmailAndPassword(email, password);

        if (company != null) {
            this.companyId = company.getId();
            loggedIn = true;
        } else {
            loggedIn = false;
        }
        return loggedIn;
    }

    /**
     * adds coupon
     *
     * @param coupon Coupon
     * @throws CouponsExceptions if coupon with the same title already exists in this company
     */
    @Override
    public void addCoupon(Coupon coupon) throws CouponsExceptions {
        if (couponRepo.existsByCompanyIdAndTitle(this.companyId, coupon.getTitle())) {
            throw new CouponsExceptions(ErrorMsg.COUPON_ADD, ErrorMsg.COUPON_TITLE_EXIST);
        }
        coupon.setCompanyId(this.companyId);
        couponRepo.save(coupon);
        System.out.println(ADD_COUPON.getMsg());
    }

    /**
     * updates coupon
     *
     * @param coupon Coupon
     * @throws CouponsExceptions if coupon with provided id doesn't exist in the company
     */
    @Override
    public void updateCoupon(Coupon coupon) throws CouponsExceptions {
        if (!couponRepo.existsByIdAndCompanyId(coupon.getId(), coupon.getCompanyId())) {
            throw new CouponsExceptions(ErrorMsg.COUPON_UPDATE, ErrorMsg.COUPON_ID_AND_COMPANY_ID_NOT_EXIST);
        }
        couponRepo.saveAndFlush(coupon);
        System.out.println(UPDATE_COUPON.getMsg());
    }

    /**
     * deletes coupon
     *
     * @param couponId coupon's id
     * @throws CouponsExceptions if coupon with provided id doesn't exist in this company
     */
    @Override
    public void deleteCoupon(int couponId) throws CouponsExceptions {
        if (!couponRepo.existsByIdAndCompanyId(couponId, this.companyId)) {
            throw new CouponsExceptions(ErrorMsg.COUPON_DELETE, ErrorMsg.COUPON_ID_NOT_EXIST);
        }
        couponRepo.deleteById(couponId);
        System.out.println(DELETE_COUPON.getMsg());
    }

    /**
     * returns company's coupons
     *
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepo.findByCompanyId(this.companyId);
    }

    /**
     * returns company's coupons by category
     *
     * @param category coupon's category
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepo.findByCompanyIdAndCategory(this.companyId, category);
    }

    /**
     * returns company's coupons by max price
     *
     * @param maxPrice max price
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getCompanyCoupons(Double maxPrice) {
        return couponRepo.findByCompanyIdAndPriceLessThan(this.companyId, maxPrice);
    }

    /**
     * returns this company details
     *
     * @return Company
     */
    @Override
    public Company getCompanyDetails() {
        return companyRepo.findById(this.companyId);
    }
}

