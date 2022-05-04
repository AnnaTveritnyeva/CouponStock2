package myProject.Coupons_Project.exeptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super(ErrorMsg.UNAUTHORIZED.getMsg());
    }
}
