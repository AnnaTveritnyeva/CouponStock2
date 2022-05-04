package myProject.Coupons_Project.exeptions;


public class PurchaseExceptions extends Exception{
    public PurchaseExceptions(ErrorMsg method, ErrorMsg description) {
        super(method.getMsg()+ description.getMsg());
    }
}
