package com.green.greenfirstproject.opendata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.greenfirstproject.opendata.model.PlantData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OpenDataWebClientService
{
    //웹 클라이언트를 세팅 하는 부분
    //    implementation 'org.springframework.boot:spring-boot-starter-webflux:3.3.0'
    //    build.gradle 에 위의 코드를 추가
    /*
        @Configuration
        public class WebClientConfig {

            @Bean
            public WebClient webClient() {
                return WebClient.builder().build();
            }
        }
        위의 방식으로 config class 를 구성
     */

    // 요청을 받을 서버의 기본 도메인 이나 IP 주소를 기입
    private final WebClient webClient = WebClient.builder().baseUrl("http://openapi.nature.go.kr").build();

    private final PlantDataMapper mapper ;

    //서비스 키를 삽입.
    //본 방법은 application.yml 에 적은 변수를 활용하는 방법.
    // private static String serviceKey = "NASDAQ..." 식으로 적어도 가능
    @Value("${open-data.service-key}")
    private String serviceKey ;

    //시작 페이지. 데이터의 수가 많고, 해당 데이터를 즉시 프론트로 보내는게 아니라 DB에 저장 할때 용도.
    //한번에 모든 데이터를 불러오면 매우 긴 로딩 시간이 필요하거나 너무많을시 제대로 읽어드리지 못하기 때문에 분할해서 받아서 기록하는 용도.
    private Integer page = 1 ;

    public void getOpenData()
    {
        // 페이지네이션 기능을 위한 무한 루프. 루프를 돌때마다 page 값을 1 증가시켜 다음 페이지의 데이터를 받아옴.
        while (true)
        {
            //데이터를 통신하는 부분 - WebClient 의 URL 을 구성 및 전송 , 데이터를 받아옴.
            //Mono = 데이터를 비동기로 받는 로직
            Mono<Map<String, Object>> mono = webClient
                    //GetMapping 타입으로 데이터를 보냄
                    .get()
                    //세부 URL 을 구성.
                    .uri("openapi/service/rest/PlantService/plntIlstrSearch" +
                            //서비스키를 입력하는 쿼리스트링
                            "?serviceKey=" + serviceKey +
                            //오픈 API 에서 요구하는 파라미터. API 마다 달라지는 값들
                            "&st=1" +
                            //한 페이지에 받을 데이터의 수
                            "&numOfRows=300" +
                            //페이지 번호
                            "&pageNo=" + page +
                            //json 형태로 파일을 받기위한 쿼리스트링. 지원되는것도 있고 안되는것도 있을수 있음.
                            "&_type=json"
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});

            //온 JSON 데이터를 내가 쓸수있게 객체화를 시키는 부분
            try {
                // mono.block() = 변수 mono 의 데이터가 다 받아질때까지 대기함. 즉 동기적 작업으로 전환.
                Map<String, Object> info = mono.block();

                //오브젝트 매퍼 객체 선언
                ObjectMapper objectMapper = new ObjectMapper();
                //Map 의 데이터를 Json 으로 변환
                String elem = (objectMapper.writeValueAsString(info));
                //Json 인 elem 의 JsonNode 객체로 전환.
                JsonNode rootNode = objectMapper.readTree(elem);
                //JsonNode 의 response -> body -> items -> item 에 있는 내용만 추출.
                JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
                //위에서 추출한 JSON 을 objectMapper 를 이용하여 PlantData 클래스로 맵핑을 함. 이때 클래스 안의 변수 명과 item 안의 키 값은 일치해야 함.
                List<PlantData> list = objectMapper.convertValue(
                        itemsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, PlantData.class));

                // 맵핑을 한 list 가 null 이거 비어 있을시 정보 조회를 모두 했다고 가정하고 루프를 종료시킴.
                if (list == null || list.isEmpty()) {
                    break;
                }
                //추출한 list 를 db에 넣는 함수
                mapper.insertPlantData(list);
                //페이지를 1개 증가
                page++ ;
                //문제가 발생할시 로그를 콘솔에 띄우고 루프를 종료시킴
            } catch (Exception e) {
                e.printStackTrace();
                break ;
            }


        }

    }

    public void deleteAllPlantData()
    {
        mapper.deleteAllPlantData();
    }
}
