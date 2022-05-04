package myProject.Coupons_Project.exeptions;

public class CustomerExceptions extends Exception {
    public CustomerExceptions(ErrorMsg method, ErrorMsg description) {
        super(method.getMsg()+ description.getMsg());
    }
}
