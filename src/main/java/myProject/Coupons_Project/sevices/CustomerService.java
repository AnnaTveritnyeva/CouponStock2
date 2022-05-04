package myProject.Coupons_Project.sevices;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.utils.MessageUtils;
import myProject.Coupons_Project.aop.UpdateCouponAmount;
import myProject.Coupons_Project.beans.*;
import myProject.Coupons_Project.exeptions.ErrorMsg;
import myProject.Coupons_Project.exeptions.PurchaseExceptions;
import myProject.Coupons_Project.repositories.*;
import myProject.Coupons_Project.sevices.serviceModels.CustomerServiceModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService implements CustomerServiceModel {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;

    private int customerId;


    /**
     * checks if provided login credentials are the same as required
     *
     * @param email    customer's email
     * @param password customer's password
     * @return boolean
     */
    @Override
    public boolean login(String email, String password) {
        boolean loggedIn;
        Customer customer = customerRepo.findByEmailAndPassword(email, password);
        if (customer != null) {
            this.customerId = customer.getId();
            loggedIn = true;
        } else {
            loggedIn = false;
        }
        return loggedIn;
    }

    /**
     * purchases coupon
     *
     * @param coupon Coupon
     * @throws PurchaseExceptions if coupon doesn't exist, if coupon amount=0, if customer already bought this coupon, if coupon expired
     */
    @UpdateCouponAmount //updates coupon amount if purchase was completed successfully
    @Override
    public void purchaseCoupon(Coupon coupon) throws PurchaseExceptions {
        checkIfCanBuyCoupon(coupon);
        Customer customer = getCustomerDetails();
        customer.getCoupons().add(coupon);
        customerRepo.saveAndFlush(customer);

        System.out.println(MessageUtils.PURCHASE_COUPON.getMsg());
    }

    /**
     * returns customer's coupons
     *
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getCustomerCoupons() {
        return getCustomerDetails().getCoupons();
    }

    /**
     * returns customer's coupons by category
     *
     * @param category coupon's category
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        return getCustomerCoupons().stream()
                .filter(coupon -> coupon.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    /**
     * returns customer's coupons by max price
     *
     * @param maxPrice max price
     * @return List of Coupon
     */
    @Override
    public List<Coupon> getCustomerCoupons(Double maxPrice) {
        return getCustomerCoupons().stream()
                .filter(coupon -> coupon.getPrice() < maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * returns this customer details
     *
     * @return Customer
     */
    @Override
    public Customer getCustomerDetails() {
        return customerRepo.findById(this.customerId);
    }


    //private methods:

    /**
     * checks if customer can buy provided coupon
     *
     * @param coupon Coupon
     * @throws PurchaseExceptions if coupon doesn't exist, if coupon amount=0, if customer already bought this coupon, if coupon expired
     */
    private void checkIfCanBuyCoupon(Coupon coupon) throws PurchaseExceptions {
        if (!companyRepo.existsById(coupon.getId())) {
            throw new PurchaseExceptions(ErrorMsg.ADD_COUPON_PURCHASE, ErrorMsg.COUPON_ID_NOT_EXIST);
        }
        if (getCustomerCoupons().stream()
                .anyMatch(coupon1 -> coupon1.getId() == (coupon.getId()))) {
            throw new PurchaseExceptions(ErrorMsg.ADD_COUPON_PURCHASE, ErrorMsg.ALREADY_BOUGHT_COUPON);
        }
        if (coupon.getEndDate().toLocalDate().isBefore(LocalDate.now())) {
            throw new PurchaseExceptions(ErrorMsg.ADD_COUPON_PURCHASE, ErrorMsg.COUPON_EXPIRED);
        }
        if (coupon.getAmount() == 0) {
            throw new PurchaseExceptions(ErrorMsg.ADD_COUPON_PURCHASE, ErrorMsg.NO_COUPONS_LEFT);
        }
    }


}
