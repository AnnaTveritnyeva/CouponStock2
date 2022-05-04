package myProject.Coupons_Project.sevices.serviceModels;

import myProject.Coupons_Project.beans.Company;
import myProject.Coupons_Project.beans.Customer;
import myProject.Coupons_Project.exeptions.CompanyExceptions;
import myProject.Coupons_Project.exeptions.CustomerExceptions;

import java.util.List;

public interface AdminServiceModel extends Loginable {
    @Override
    boolean login(String email, String password);

    void addCompany(Company company) throws CompanyExceptions;

    void updateCompany(Company company) throws CompanyExceptions;

    void deleteCompany(int companyId) throws CompanyExceptions;

    List<Company> getAllCompanies();

    Company getOneCompany(int companyId) throws CompanyExceptions;

    void addCustomer(Customer customer) throws CustomerExceptions;

    void updateCustomer(Customer customer) throws CustomerExceptions;

    void deleteCustomer(int customerId) throws CustomerExceptions;

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int customerId) throws CustomerExceptions;
}
