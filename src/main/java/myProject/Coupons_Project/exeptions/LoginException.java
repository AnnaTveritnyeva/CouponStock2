package myProject.Coupons_Project.exeptions;

public class LoginException extends Exception {
    public LoginException() {
        super(ErrorMsg.LOGIN.getMsg());
    }
}
