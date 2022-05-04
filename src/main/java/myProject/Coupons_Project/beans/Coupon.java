package myProject.Coupons_Project.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_id")
    private int companyId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, name = "category")
    private Category category;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, name = "start_date")
    private Date startDate;

    @Column(nullable = false, name = "end_date")
    private Date endDate;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String image;


    /**
     * initializes new coupon with id = 0 (updates in DB) and company_id=0 (updates in DB when company adds coupon)
     * @param category coupon Category (Enum)
     * @param title coupon title
     * @param description coupon description
     * @param startDate coupon start date
     * @param endDate coupon expiration date
     * @param amount coupon amount
     * @param price coupon price
     * @param image coupon img (URL/path)
     */
    public Coupon(Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.id = 0;
        this.companyId = 0;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

}
