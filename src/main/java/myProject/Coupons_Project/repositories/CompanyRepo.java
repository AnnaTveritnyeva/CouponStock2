package myProject.Coupons_Project.repositories;


import myProject.Coupons_Project.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
    /**
     * finds in DB company by email and password
     * @param email company's email
     * @param password company's password
     * @return Company
     */
    Company findByEmailAndPassword(String email, String password);

    /**
     * finds in DB company by company id
     * @param id company's id
     * @return Company
     */
    Company findById(int id);

    /**
     * checks in DB if company exists by name or email
     * @param name company's name
     * @param email company's email
     * @return boolean
     */
    boolean existsByNameOrEmail(String name, String email);

    /**
     * check in DB if company exists by id and name
     * @param id company's id
     * @param name company's name
     * @return boolean
     */
    boolean existsByIdAndName(int id, String name);
}
