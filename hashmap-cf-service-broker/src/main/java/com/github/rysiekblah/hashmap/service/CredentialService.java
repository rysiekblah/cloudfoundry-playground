package com.github.rysiekblah.hashmap.service;

import com.google.common.collect.Maps;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/14/2017.
 */
@Service
public class CredentialService {

    public Map<String, Object> getCredentials(String serviceId, String bindingId) {
        Map<String, Object> credentials = Maps.newHashMap();
        credentials.put("url", "https://hashmap-cf-service-broker.cfapps.io");
        credentials.put("bindingId", bindingId);
        credentials.put("serviceId", serviceId);
        return credentials;
    }

}
