package myProject.Coupons_Project.repositories;

import myProject.Coupons_Project.beans.Category;
import myProject.Coupons_Project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    //DailyJob:

    /**
     * deletes coupons which expiration date is after the provided date
     * @param date Date
     */
    void deleteByEndDateAfter(Date date);


    //Company Service:
    /**
     * finds in DB coupons by companyId
     * @param companyId coupon's companyId
     * @return List of Coupons
     */
    List<Coupon> findByCompanyId (int companyId);

    /**
     * finds in DB coupons by companyId and category
     * @param companyId coupon's companyId
     * @param category coupon's category
     * @return List of Coupons
     */
    List<Coupon> findByCompanyIdAndCategory (int companyId, Category category);

    /**
     * finds in DB coupons by companyId and max price
     * @param companyId coupon's companyId
     * @param maxPrice coupon's max price
     * @return List of Coupons
     */
    List<Coupon> findByCompanyIdAndPriceLessThan (int companyId, Double maxPrice);

    /**
     * checks if coupon exists by company id and title
     * @param companyId coupon's companyId
     * @param title coupon's title
     * @return boolean
     */
    boolean existsByCompanyIdAndTitle(int companyId, String title);

    /**
     * checks if coupon exists by coupon id and companyId
     * @param id coupon's id
     * @param companyId coupon's companyId
     * @return boolean
     */
    boolean existsByIdAndCompanyId(int id, int companyId);



}
