package myProject.Coupons_Project.clr;

import static myProject.Coupons_Project.utils.RestTmpltUtils.*;


import myProject.Coupons_Project.utils.ArtUtils;
import myProject.Coupons_Project.utils.TablePrinter;
import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Customer;
import myProject.Coupons_Project.beans.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static myProject.Coupons_Project.utils.URLUtils.*;

@Component
@Order(1)
public class TestAdmin implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    List<Company> companies;

    @Autowired
    List<Customer> customers;

    @Autowired
    @Qualifier("admin")
    private UserModel userModel;

    @Override
    public void run(String... args) throws Exception {
        final String ADMIN = "\n\nADMIN - ";
        final String WRONG = ": wrong details";

        try {
            System.out.println(ArtUtils.ADMIN_SERVICE);

            //login
            System.out.println(ADMIN + LOGIN);
            HttpHeaders adminHeader = getHeader(login(userModel));

            //add Company
            try {
                System.out.println(ADMIN + ADD_COMPANY);
                addCompany(companies.get(0), adminHeader);
                addCompany(companies.get(1), adminHeader);
                addCompany(companies.get(2), adminHeader);
                addCompany(companies.get(3), adminHeader);
                addCompany(companies.get(4), adminHeader);

                System.out.println("\n" + ADD_COMPANY + WRONG);
                addCompany(companies.get(4), adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }


            //delete Company:
            try {
                System.out.println(ADMIN + DELETE_COMPANY);
                deleteCompany(5, adminHeader);

                System.out.println("\n" + DELETE_COMPANY + WRONG);
                deleteCompany(5, adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //updateCompany
            try {
                System.out.println(ADMIN + UPDATE_COMPANY);
                Company company = getOneCompany(3, adminHeader);
                company.setEmail("newEmail");
                updateCompany(company, adminHeader);

                System.out.println("\n" + UPDATE_COMPANY + WRONG);
                company.setName("newName");
                updateCompany(company, adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //getAllCompanies:
            System.out.println(ADMIN + GET_ALL_COMPANIES);
            TablePrinter.print(getAllCompanies(adminHeader));

            //getOneCompany:
            try {
                System.out.println(ADMIN + GET_ONE_COMPANY);
                TablePrinter.print(getOneCompany(4, adminHeader));

                System.out.println("\n" + GET_ONE_COMPANY + WRONG);
                getOneCompany(5, adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //addCustomer
            try {
                System.out.println(ADMIN + ADD_CUSTOMER);
                addCustomer(customers.get(0), adminHeader);
                addCustomer(customers.get(1), adminHeader);
                addCustomer(customers.get(2), adminHeader);
                addCustomer(customers.get(3), adminHeader);
                addCustomer(customers.get(4), adminHeader);

                System.out.println("\n" + ADD_CUSTOMER + WRONG);
                addCustomer(customers.get(0), adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //updateCustomer:
            try {
                System.out.println(ADMIN + UPDATE_CUSTOMER);
                Customer customer = getOneCustomer(4, adminHeader);
                customer.setFirstName("newName");
                updateCustomer(customer, adminHeader);

                System.out.println("\n" + UPDATE_CUSTOMER + WRONG);
                customer.setId(8);
                updateCustomer(customer, adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //deleteCustomer
            try {
                System.out.println(ADMIN + DELETE_CUSTOMER);
                deleteCustomer(4, adminHeader);

                System.out.println("\n" + DELETE_CUSTOMER + WRONG);
                deleteCustomer(4, adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //getOneCustomer
            try {
                System.out.println(ADMIN + GET_ONE_CUSTOMER);
                TablePrinter.print(getOneCustomer(1, adminHeader));

                System.out.println("\n" + GET_ONE_CUSTOMER + WRONG);
                getOneCustomer(4, adminHeader);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getMessage());
            }

            //getAllCustomers
            System.out.println(ADMIN + GET_ALL_CUSTOMER);
            TablePrinter.print(getAllCustomers(adminHeader));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ResponseEntity<String> login(UserModel userModel) {
        return restTemplate.exchange(
                LOGIN.getUrl(),
                HttpMethod.POST,
                getHttpEntity(userModel),
                String.class
        );
    }

    private void addCompany(Company company, HttpHeaders adminHeader) {
        restTemplate.postForEntity(ADD_COMPANY.getUrl(),
                getHttpEntity(company, adminHeader),
                Company.class);
    }

    private void deleteCompany(int companyId, HttpHeaders adminHeader) {
        restTemplate.exchange(DELETE_COMPANY.getUrl(),
                HttpMethod.DELETE,
                getHttpEntity(adminHeader),
                String.class,
                getParams("companyId", companyId));
    }

    private void updateCompany(Company company, HttpHeaders adminHeader) {
        restTemplate.put(UPDATE_COMPANY.getUrl(),
                getHttpEntity(company, adminHeader));
    }

    private Company getOneCompany(int companyId, HttpHeaders adminHeader) {
        return restTemplate.exchange(
                GET_ONE_COMPANY.getUrl(),
                HttpMethod.GET,
                getHttpEntity(adminHeader),
                Company.class,
                getParams("companyId", companyId)
        ).getBody();
    }

    private List<Company> getAllCompanies(HttpHeaders adminHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_ALL_COMPANIES.getUrl(),
                HttpMethod.GET,
                getHttpEntity(adminHeader),
                Company[].class
        ).getBody()));
    }

    private void addCustomer(Customer customer, HttpHeaders adminHeader) {
        restTemplate.postForEntity(ADD_CUSTOMER.getUrl(),
                getHttpEntity(customer, adminHeader),
                Customer.class);
    }

    private void deleteCustomer(int customerId, HttpHeaders adminHeader) {
        restTemplate.exchange(DELETE_CUSTOMER.getUrl(),
                HttpMethod.DELETE,
                getHttpEntity(adminHeader),
                String.class,
                getParams("customerId", customerId));
    }

    private void updateCustomer(Customer customer, HttpHeaders adminHeader) {
        restTemplate.put(UPDATE_CUSTOMER.getUrl(),
                getHttpEntity(customer, adminHeader));
    }

    private Customer getOneCustomer(int customerId, HttpHeaders adminHeader) {
        return restTemplate.exchange(
                GET_ONE_CUSTOMER.getUrl(),
                HttpMethod.GET,
                getHttpEntity(adminHeader),
                Customer.class,
                getParams("customerId", customerId)
        ).getBody();
    }

    private List<Customer> getAllCustomers(HttpHeaders adminHeader) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.exchange(
                GET_ALL_CUSTOMER.getUrl(),
                HttpMethod.GET,
                getHttpEntity(adminHeader),
                Customer[].class
        ).getBody()));
    }
}
