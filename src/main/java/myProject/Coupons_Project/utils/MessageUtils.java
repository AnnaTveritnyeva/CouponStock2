package myProject.Coupons_Project.utils;

public enum MessageUtils {
    COMPANY("company "),
    CUSTOMER("customer "),
    COUPON("coupon "),

    SUCCESS("was successfully "),

    ADD ("added!"),
    UPDATE("updated!"),
    DELETE("deleted!"),
    PURCHASE("purchased!"),

    ADD_COMPANY(COMPANY.msg+SUCCESS.msg+ADD.msg),
    UPDATE_COMPANY(COMPANY.msg+SUCCESS.msg+UPDATE.msg),
    DELETE_COMPANY(COMPANY.msg+SUCCESS.msg+DELETE.msg),

    ADD_CUSTOMER(CUSTOMER.msg+SUCCESS.msg+ADD.msg),
    UPDATE_CUSTOMER(CUSTOMER.msg+SUCCESS.msg+UPDATE.msg),
    DELETE_CUSTOMER(CUSTOMER.msg+SUCCESS.msg+DELETE.msg),

    ADD_COUPON(COUPON.msg+SUCCESS.msg+ADD.msg),
    UPDATE_COUPON(COUPON.msg+SUCCESS.msg+UPDATE.msg),
    DELETE_COUPON(COUPON.msg+SUCCESS.msg+DELETE.msg),

    PURCHASE_COUPON(COUPON.msg+SUCCESS.msg+PURCHASE.msg);

    private final String msg;

    MessageUtils(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
