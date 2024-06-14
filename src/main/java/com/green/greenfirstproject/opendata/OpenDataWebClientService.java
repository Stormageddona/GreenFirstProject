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
    private final WebClient webClient = WebClient.builder().baseUrl("http://openapi.nature.go.kr").build();

    private final PlantDataMapper mapper ;

    @Value("${open-data.service-key}")
    private String serviceKey ;

    private Integer page = 1 ;

    public void getOpenData()
    {
        while (true)
        {
            Mono<Map<String, Object>> mono = webClient.get()
                    .uri("openapi.nature.go.kr/openapi/service/rest/PlantService/plntIlstrSearch" +
                            "?serviceKey=" + serviceKey +
                            "&st=1" +
                            "&numOfRows=300" +
                            "&pageNo=" + page +
                            "&_type=json"
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});

            try {
                Map<String, Object> info = mono.block();


                ObjectMapper objectMapper = new ObjectMapper();
                String elem = (objectMapper.writeValueAsString(info));

                JsonNode rootNode = objectMapper.readTree(elem);
                JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
                List<PlantData> list = objectMapper.convertValue(
                        itemsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, PlantData.class));

                if (list == null || list.isEmpty()) {
                    break;
                }
                mapper.insertPlantData(list);
                page++ ;
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
