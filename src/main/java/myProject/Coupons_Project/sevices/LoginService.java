package myProject.Coupons_Project.sevices;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.utils.DateUtils;
import myProject.Coupons_Project.beans.UserModel;
import myProject.Coupons_Project.exeptions.LoginException;
import myProject.Coupons_Project.jwt.JWT;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
@Service
public class LoginService {
    private final JWT jwt;

    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    /**
     * logging in as the role provided in user
     * @param userModel User Model
     * @return JWT token (String)
     * @throws LoginException if email or password doesn't match
     */
    public String login(UserModel userModel) throws LoginException {
        if (!ValidateDetails(userModel)) {
            throw new LoginException();
        }
        System.out.println("User " + userModel.getEmail().toUpperCase() + " was successfully logged in at " + DateUtils.getLocalDateTime());
        return jwt.generateToken(userModel);
    }

    /**
     * checks userModel's credentials by the role provided in user
     * @param userModel UserModel
     * @return boolean
     */
    private boolean ValidateDetails(UserModel userModel) {
        switch (userModel.getRole()) {
            case ADMINISTRATOR:
                return adminService.login(userModel.getEmail(), userModel.getPassword());
            case COMPANY:
                return companyService.login(userModel.getEmail(), userModel.getPassword());
            case CUSTOMER:
                return customerService.login(userModel.getEmail(), userModel.getPassword());
            default:
                return false;
        }
    }


}
