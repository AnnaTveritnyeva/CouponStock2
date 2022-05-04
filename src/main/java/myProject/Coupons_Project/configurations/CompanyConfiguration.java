package myProject.Coupons_Project.configurations;


import myProject.Coupons_Project.beans.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanyConfiguration {
    @Bean
    @Qualifier("company1")
    public static Company company1() {
        return new Company(
                "McDonald's",
                "bigMac@gmail.com",
                "burger2022"
        );
    }

    @Bean
    @Qualifier("company2")
    public Company company2() {
        return new Company(
                "Apple",
                "apple@gmail.com",
                "iphone20"
        );
    }

    @Bean
    @Qualifier("company3")
    public Company company3() {
        return new Company(
                "Mojo",
                "MojoRest@gmail.com",
                "sushiNum1"
        );
    }

    @Bean
    @Qualifier("company4")
    public Company company4() {
        return new Company(
                "El-Al",
                "elal@gmail.com",
                "elAl123"
        );
    }

    @Bean
    @Qualifier("company5")
    public Company company5() {
        return new Company(
                "John Bryce Academy",
                "johnBrice@gmail.com",
                "johny123"
        );
    }
}
