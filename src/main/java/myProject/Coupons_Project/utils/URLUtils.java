package myProject.Coupons_Project.utils;

public enum URLUtils {
    URL("http://localhost:8080/"),

    ADMIN(URL.getUrl()+"admin/"),
    COMPANY(URL.getUrl()+"company/"),
    CUSTOMER(URL.getUrl()+"customer/"),
    GUEST(URL.getUrl()+"guest/"),

    LOGIN(URL.getUrl()+"authentication/login"),

    //ADMIN methods:
    ADD_COMPANY(ADMIN.getUrl()+"addCompany"),
    UPDATE_COMPANY(ADMIN.getUrl()+"updateCompany"),
    DELETE_COMPANY(ADMIN.getUrl()+"deleteCompany/{companyId}"),
    GET_ALL_COMPANIES(ADMIN.getUrl()+"getAllCompanies"),
    GET_ONE_COMPANY(ADMIN.getUrl()+"getOneCompany/{companyId}"),
    ADD_CUSTOMER(ADMIN.getUrl()+"addCustomer"),
    UPDATE_CUSTOMER(ADMIN.getUrl()+"updateCustomer"),
    DELETE_CUSTOMER(ADMIN.getUrl()+"deleteCustomer/{customerId}"),
    GET_ALL_CUSTOMER(ADMIN.getUrl()+"getAllCustomers"),
    GET_ONE_CUSTOMER(ADMIN.getUrl()+"getOneCustomer/{customerId}"),

    //COMPANY methods:
    ADD_COUPON(COMPANY.getUrl()+"addCoupon"),
    UPDATE_COUPON(COMPANY.getUrl()+"updateCoupon"),
    DELETE_COUPON(COMPANY.getUrl()+"deleteCoupon/{couponId}"),
    GET_COMPANY_COUPONS(COMPANY.getUrl()+"getCompanyCoupons"),
    GET_COMPANY_COUPONS_BY_CATEGORY(COMPANY.getUrl()+"getCompanyCouponsByCategory/{category}"),
    GET_COMPANY_COUPONS_BY_MAX_PRICE(COMPANY.getUrl()+"getCompanyCouponsByMaxPrice/{maxPrice}"),
    GET_COMPANY_DETAILS(COMPANY.getUrl()+"getCompanyDetails"),

    //CUSTOMER methods:
    PURCHASE_COUPON(CUSTOMER.getUrl()+"couponPurchase"),
    GET_CUSTOMER_COUPONS(CUSTOMER.getUrl()+"getCustomerCoupons"),
    GET_CUSTOMER_COUPONS_BY_CATEGORY(CUSTOMER.getUrl()+"getCustomerCouponsByCategory/{category}"),
    GET_CUSTOMER_COUPONS_BY_MAX_PRICE(CUSTOMER.getUrl()+"getCustomerCouponsByMaxPrice/{maxPrice}"),
    GET_CUSTOMER_DETAILS(CUSTOMER.getUrl()+"getCustomerDetails"),

    //GUEST methods:
    GET_ALL_COUPONS(GUEST.getUrl()+"getAllCoupons");


    private final String url;

    URLUtils(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
