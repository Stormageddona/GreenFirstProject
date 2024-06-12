package com.green.greenfirstproject.common.config;

import com.green.greenfirstproject.opendata.OpenDataWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringScheduledConfig
{

    private final OpenDataWebClientService service ;

    @Scheduled(cron = "0 0 0 */1 * *")
    public void getOpenData()
    {
        service.deleteAllPlantData();
        service.getOpenData();
    }
}
