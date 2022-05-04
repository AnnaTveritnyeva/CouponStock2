package myProject.Coupons_Project.controller;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.sevices.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("guest")
@RequiredArgsConstructor
@CrossOrigin
public class GuestController {
    private final GuestService guestService;

    @GetMapping("getAllCoupons")
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(guestService.getAllCoupons(), HttpStatus.OK);
    }
}
