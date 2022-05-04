package myProject.Coupons_Project.configurations;

import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Customer;
import myProject.Coupons_Project.beans.Role;
import myProject.Coupons_Project.beans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Autowired
    @Qualifier("company2")
    Company company;

    @Autowired
    @Qualifier("company4")
    Company company1;

    @Autowired
    @Qualifier("customer2")
    Customer customer;


    @Bean
    @Qualifier("admin")
    public UserModel adminUserModel() {
        return new UserModel(
                "admin@admin.com",
                "admin",
                Role.ADMINISTRATOR
        );
    }

    @Bean
    @Qualifier("company")
    public UserModel companyUserModel() {
        return new UserModel(
                company.getEmail(),
                company.getPassword(),
                Role.COMPANY
        );
    }

    @Bean
    @Qualifier("customer")
    public UserModel customerUserModel() {
        return new UserModel(
                customer.getEmail(),
                customer.getPassword(),
                Role.CUSTOMER
        );
    }

    @Bean
    @Qualifier("company1")
    public UserModel company1UserModel() {
        return new UserModel(
                company1.getEmail(),
                company1.getPassword(),
                Role.COMPANY
        );
    }
}
