package myProject.Coupons_Project.exeptions;

public class CouponsExceptions extends Exception {
    public CouponsExceptions(ErrorMsg method, ErrorMsg description) {
        super(method.getMsg()+ description.getMsg());
    }
}
