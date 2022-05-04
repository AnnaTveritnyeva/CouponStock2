package myProject.Coupons_Project.controller;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.beans.UserModel;
import myProject.Coupons_Project.exeptions.LoginException;
import myProject.Coupons_Project.jwt.JWT;
import myProject.Coupons_Project.sevices.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authentication")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {
    private final LoginService loginService;
    private final JWT jwt;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserModel userModel) throws LoginException {
        //return new ResponseEntity<>(loginService.login(userModel), HttpStatus.IM_USED);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(),loginService.login(userModel))
                .build();
    }


}

