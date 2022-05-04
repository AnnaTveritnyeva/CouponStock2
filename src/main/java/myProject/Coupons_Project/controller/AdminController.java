package myProject.Coupons_Project.controller;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Customer;
import myProject.Coupons_Project.beans.Role;
import myProject.Coupons_Project.exeptions.*;
import myProject.Coupons_Project.jwt.JWT;
import myProject.Coupons_Project.sevices.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminService adminService;
    private final JWT jwt;


    @PostMapping("addCompany")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CompanyExceptions, UnauthorizedException {
        authorize(token);
        adminService.addCompany(company);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @PutMapping("updateCompany")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CompanyExceptions, UnauthorizedException {
        authorize(token);
        adminService.updateCompany(company);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @DeleteMapping("deleteCompany/{companyId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws CompanyExceptions, UnauthorizedException {
        authorize(token);
        adminService.deleteCompany(companyId);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @GetMapping("getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(adminService.getAllCompanies());
    }

    @GetMapping("getOneCompany/{companyId}")
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws CompanyExceptions, UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(adminService.getOneCompany(companyId));
    }

    @PostMapping("addCustomer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CustomerExceptions, UnauthorizedException {
        authorize(token);
        adminService.addCustomer(customer);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @PutMapping("updateCustomer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws CustomerExceptions, UnauthorizedException {
        authorize(token);
        adminService.updateCustomer(customer);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @DeleteMapping("deleteCustomer/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws CustomerExceptions, UnauthorizedException {
        authorize(token);
        adminService.deleteCustomer(customerId);
        return ResponseEntity.accepted()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .build();
    }

    @GetMapping("getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(adminService.getAllCustomers());

    }

    @GetMapping("getOneCustomer/{customerId}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws CustomerExceptions, UnauthorizedException {
        authorize(token);
        return ResponseEntity.ok()
                .header(jwt.getHeaderName(), jwt.generateUpdatedToken(token))
                .body(adminService.getOneCustomer(customerId));
    }

    //private methods:
    private void authorize(String token) throws UnauthorizedException {
        if (!jwt.validateTokenAndRole(token, Role.ADMINISTRATOR)) {
            throw new UnauthorizedException();
        }
    }
}
