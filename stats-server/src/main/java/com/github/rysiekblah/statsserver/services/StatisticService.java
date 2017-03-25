package com.github.rysiekblah.statsserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/20/2017.
 */
@Service
public class StatisticService {

    @Value("${hashmap.service.url}")
    private String hashmapUrl;

    @Value("{hashmap.admin.url}")
    private String hashmapAdminUrl;

    @Value("${hashmap.service.bindingId}")
    private String bindingId;

    @Autowired
    private RestTemplate restTemplate;

    public String handleClick(String name) {
        ResponseEntity<String> response = callGet(buildGetUrl(name), String.class);
        String url;
        if (response.getStatusCode().equals(HttpStatus.NO_CONTENT)) {
            url = buildPostUrl(name, "1");
        } else {
            url = buildPostUrl(name, String.valueOf(Integer.parseInt(response.getBody()) + 1));
        }
        return callPost(url);
    }

    public Map<String, String> getAll() {
        String url = UriComponentsBuilder.fromHttpUrl(hashmapUrl).toUriString();
        ResponseEntity<Map<String, String>> resp = callGet(url, Map.class);
        return resp.getBody();
    }

    public String getHashMapVersion() {
        String url = UriComponentsBuilder.fromHttpUrl(hashmapAdminUrl).path("ver").toUriString();
        ResponseEntity<String> version = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return version.getBody();
    }

    private String buildGetUrl(String key) {
        return UriComponentsBuilder.fromHttpUrl(hashmapUrl).path("get").queryParam("key", key).toUriString();
    }

    private String buildPostUrl(String key, String value) {
        return UriComponentsBuilder.fromHttpUrl(hashmapUrl).path("put").queryParam("key", key).queryParam("value", value).toUriString();
    }

    private String callPost(String url) {
        return restTemplate.exchange(url, HttpMethod.POST, null, String.class).getBody();
    }

    private <T> ResponseEntity<T> callGet(String url, Class clazz) {
        return restTemplate.<T>exchange(url, HttpMethod.GET, null, clazz);
    }

}
