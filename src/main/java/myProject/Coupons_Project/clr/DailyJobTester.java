package myProject.Coupons_Project.clr;

import myProject.Coupons_Project.threads.DailyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class DailyJobTester implements CommandLineRunner {
    @Autowired
    DailyJob dailyJob;

    @Override
    public void run(String... args) throws Exception {
        dailyJob.stopDailyJob();
    }

}
