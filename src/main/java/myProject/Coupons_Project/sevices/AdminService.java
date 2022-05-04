package myProject.Coupons_Project.sevices;


import lombok.RequiredArgsConstructor;

import static myProject.Coupons_Project.utils.MessageUtils.*;

import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Customer;
import myProject.Coupons_Project.exeptions.CompanyExceptions;
import myProject.Coupons_Project.exeptions.CustomerExceptions;
import myProject.Coupons_Project.exeptions.ErrorMsg;
import myProject.Coupons_Project.repositories.*;
import myProject.Coupons_Project.sevices.serviceModels.AdminServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService implements AdminServiceModel {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;


    /**
     * checks if provided login credentials are the same as required
     * @param email    administrator's email
     * @param password administrator's password
     * @return boolean
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * adds company to DB
     *
     * @param company Company
     * @throws CompanyExceptions if company with the same email or name already exists
     */
    @Override
    public void addCompany(Company company) throws CompanyExceptions {
        if (companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CompanyExceptions(ErrorMsg.COMPANY_ADD, ErrorMsg.COMPANY_EMAIL_OR_NAME_EXISTS);
        }
        companyRepo.save(company);
        System.out.println(ADD_COMPANY.getMsg());
    }

    /**
     * updates company details
     *
     * @param company Company
     * @throws CompanyExceptions if company with the same id and name doesn't exist (as we cannot update those parameters)
     */
    @Override
    public void updateCompany(Company company) throws CompanyExceptions {
        if (!companyRepo.existsByIdAndName(company.getId(), company.getName())) {
            throw new CompanyExceptions(ErrorMsg.COMPANY_UPDATE, ErrorMsg.COMPANY_ID_AND_NAME_NOT_EXIST);
        }
        companyRepo.saveAndFlush(company);
        System.out.println(UPDATE_COMPANY.getMsg());
    }

    /**
     * deletes company by id
     *
     * @param companyId company's id
     * @throws CompanyExceptions if company with provided id doesn't exist
     */
    @Override
    public void deleteCompany(int companyId) throws CompanyExceptions {
        checkCompanyExistence(companyId, ErrorMsg.COMPANY_DELETE);
        companyRepo.deleteById(companyId);
        System.out.println(DELETE_COMPANY.getMsg());
    }

    /**
     * returns all companies
     *
     * @return List of Company
     */
    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    /**
     * returns company by id
     *
     * @param companyId company's id
     * @return Company
     * @throws CompanyExceptions if company with provided id doesn't exist
     */
    @Override
    public Company getOneCompany(int companyId) throws CompanyExceptions {
        checkCompanyExistence(companyId, ErrorMsg.GET_COMPANY);
        return companyRepo.findById(companyId);
    }

    /**
     * adds customer
     *
     * @param customer Customer
     * @throws CustomerExceptions if customer with the same email already exists
     */
    @Override
    public void addCustomer(Customer customer) throws CustomerExceptions {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CustomerExceptions(ErrorMsg.CUSTOMER_ADD, ErrorMsg.CUSTOMER_EMAIL_EXIST);
        }
        customerRepo.save(customer);
        System.out.println(ADD_CUSTOMER.getMsg());
    }

    /**
     * updates customer
     *
     * @param customer Customer
     * @throws CustomerExceptions if customer with the same id doesn't exist (as we cannot update id)
     */
    @Override
    public void updateCustomer(Customer customer) throws CustomerExceptions {
        checkCustomerExistence(customer.getId(), ErrorMsg.CUSTOMER_UPDATE);
        customerRepo.saveAndFlush(customer);
        System.out.println(UPDATE_CUSTOMER.getMsg());
    }

    /**
     * deletes customer by id
     *
     * @param customerId customer id
     * @throws CustomerExceptions if customer with provided id doesn't exist
     */
    @Override
    public void deleteCustomer(int customerId) throws CustomerExceptions {
        checkCustomerExistence(customerId, ErrorMsg.CUSTOMER_DELETE);
        customerRepo.deleteById(customerId);
        System.out.println(DELETE_CUSTOMER.getMsg());
    }

    /**
     * returns all customers
     *
     * @return List of Customer
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    /**
     * returns customer by id
     *
     * @param customerId customer's id
     * @return List of Customer
     * @throws CustomerExceptions if customer with provided id doesn't exist
     */
    @Override
    public Customer getOneCustomer(int customerId) throws CustomerExceptions {
        checkCustomerExistence(customerId, ErrorMsg.GET_CUSTOMER);
        return customerRepo.findById(customerId);
    }


    //private methods:

    /**
     * checks if customer exists by id
     *
     * @param customerId customer's id
     * @param errorMsg   name of method to be provided if throws exception
     * @throws CustomerExceptions if customer with provided id doesn't exist
     */
    private void checkCustomerExistence(int customerId, ErrorMsg errorMsg) throws CustomerExceptions {
        if (!customerRepo.existsById(customerId)) {
            throw new CustomerExceptions(errorMsg, ErrorMsg.CUSTOMER_ID_NOT_EXIST);
        }
    }

    /**
     * checks if company exists by id
     *
     * @param companyId company's id
     * @param errorMsg  name of method to be provided if throws exception
     * @throws CompanyExceptions if company with provided id doesn't exist
     */
    private void checkCompanyExistence(int companyId, ErrorMsg errorMsg) throws CompanyExceptions {
        if (!companyRepo.existsById(companyId)) {
            throw new CompanyExceptions(errorMsg, ErrorMsg.COMPANY_ID_NOT_EXIST);
        }
    }


}
