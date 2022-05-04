package myProject.Coupons_Project.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 10)
    private String password;

    @Singular
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE /*CascadeType.ALL*/},
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "customers_coupons",
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id")
    )
    private List<Coupon> coupons;

    /**
     * initializes new customer with id=0 (updates in DB) and an empty coupon list
     * @param firstName customer first name
     * @param lastName customer last name
     * @param email customer email
     * @param password customer password
     */
    public Customer(String firstName, String lastName, String email, String password) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }
}
