package uz.pdp.online.onlinepayment.schedules;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.online.onlinepayment.service.RegistirationTempSentCodeService;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Transactional
public class SchedulingRegistirTempSentCode {

    private final RegistirationTempSentCodeService registirationTempSentCodeService;

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void autoDeleteSentCodeIfExpired(){
        registirationTempSentCodeService.deleteAllWhichBefore(new Date());
    }

}
