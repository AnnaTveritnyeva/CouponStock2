package myProject.Coupons_Project.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserModel {
    private String email;
    private String password;
    private Role role;
}
