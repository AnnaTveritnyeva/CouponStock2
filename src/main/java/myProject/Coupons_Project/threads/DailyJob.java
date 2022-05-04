package myProject.Coupons_Project.threads;

import lombok.RequiredArgsConstructor;
import myProject.Coupons_Project.repositories.CouponRepo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Component
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class DailyJob {
    private final CouponRepo couponRepo;
    private final Date NOW = Date.valueOf(LocalDate.now());
    private Boolean isOn = true;

    /**
     * deletes expired coupons from the system every 24 hours at 01:30
     */
    @Transactional
    @Async
    @Scheduled(cron = "0 30 1 * * ?", zone = "Asia/Jerusalem")
    public void eraseCoupon() {
        while (this.isOn) {
            couponRepo.deleteByEndDateAfter(NOW);
            System.out.println("01:30 AM: erasing all expired coupons in the system");
        }
    }

    /**
     * stops daily job
     */
    public void stopDailyJob() {
        this.isOn = false;
        System.out.println("Daily Job was stopped!");
    }

    /**
     * starts daily job
     */
    public void startDailyJob() {
        this.isOn = true;
        System.out.println("Daily Job was stopped!");
    }
}
