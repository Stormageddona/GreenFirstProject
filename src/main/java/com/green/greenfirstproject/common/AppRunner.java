package com.green.greenfirstproject.common;

import com.green.greenfirstproject.opendata.OpenDataWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//스프링부트가 시작되면 실행되는 코드
@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final OpenDataWebClientService service ;

    @Override
    public void run(ApplicationArguments args) throws Exception {


//        service.deleteAllPlantData();
//        service.getOpenData();
    }
}
