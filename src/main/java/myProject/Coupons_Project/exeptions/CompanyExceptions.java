package myProject.Coupons_Project.exeptions;

public class CompanyExceptions extends Exception {
    public CompanyExceptions(ErrorMsg method, ErrorMsg description) {
        super(method.getMsg()+ description.getMsg());
    }
}
