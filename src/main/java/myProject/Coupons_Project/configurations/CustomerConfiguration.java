package myProject.Coupons_Project.configurations;


import myProject.Coupons_Project.beans.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {
    @Bean
    @Qualifier("customer1")
    public Customer customer1() {
        return new Customer(
                "Anna",
                "Tveritnyeva",
                "atveretniv@gmail.com",
                "anna123"
        );
    }

    @Bean
    @Qualifier("customer2")
    public Customer customer2() {
        return new Customer(
                "Zeev",
                "Mindali",
                "zeev@gmail.com",
                "zeev123"
        );
    }

    @Bean
    @Qualifier("customer3")
    public Customer customer3() {
        return new Customer(
                "Tal",
                "Rozner",
                "tal@gmail.com",
                "tal123"
        );
    }

    @Bean
    @Qualifier("customer4")
    public Customer customer4() {
        return new Customer(
                "Yair",
                "Nadav",
                "yair@gmail.com",
                "yair123"
        );
    }

    @Bean
    @Qualifier("customer5")
    public Customer customer5() {
        return new Customer(
                "Tomer",
                "Yoel",
                "tomer@gmail.com",
                "tomer123"
        );
    }

}
