package uz.pdp.online.onlinepayment.schedules;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CachingManager {

    private final CacheManager cacheManager;

    @Scheduled(initialDelay = 20000, fixedDelay = 15000)
    public void autoFlushUsersCaching(){
        log.info("Auto delete users caching");
        Cache users = cacheManager.getCache("users");
        if(users!=null){
            users.clear();
        }
    }

}
