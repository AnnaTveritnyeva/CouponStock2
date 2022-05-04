package myProject.Coupons_Project.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 10)
    private String password;


    @Singular
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Coupon> coupons;


    /**
     * initializes new company with id=0 (updates in DB) and an empty coupons list
     * @param name company name
     * @param email company email
     * @param password company password
     */
    public Company(String name, String email, String password) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }
}
