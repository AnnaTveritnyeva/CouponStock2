package myProject.Coupons_Project.repositories;


import myProject.Coupons_Project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    /**
     * finds in DB customer by id
     * @param id customer's id
     * @return Customer
     */
    Customer findById(int id);

    /**
     * finds in DB customer by email and password
     * @param email customer's email
     * @param password customer's password
     * @return Customer
     */
    Customer findByEmailAndPassword(String email, String password);

    /**
     * checks if customer exists by email
     * @param email customer's email
     * @return boolean
     */
    boolean existsByEmail(String email);
}
