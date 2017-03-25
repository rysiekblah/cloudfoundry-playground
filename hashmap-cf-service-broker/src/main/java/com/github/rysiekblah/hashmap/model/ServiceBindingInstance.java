package com.github.rysiekblah.hashmap.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.rysiekblah.cloudfoundry.servicebroker.config.json.EmptyMapSerializer;
import com.google.common.collect.Maps;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/14/2017.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceBindingInstance {

    @JsonSerialize
    private String serviceId;
    @JsonSerialize
    private String bindingId;
    @JsonSerialize(nullsUsing = EmptyMapSerializer.class)
    private Map<String, Object> credentials;
    @JsonSerialize
    private String planId;
    @JsonSerialize
    private String appGuid;

    public ServiceBindingInstance() {

    }

    public ServiceBindingInstance(CreateServiceInstanceBindingRequest request) {
        this.serviceId = request.getServiceDefinitionId();
        this.bindingId = request.getBindingId();
        this.planId = request.getPlanId();
        this.appGuid = request.getBoundAppGuid();
    }

    public ServiceBindingInstance(CreateServiceInstanceBindingRequest request, Map<String, Object> credentials) {
        this(request);
        setCredentials(credentials);
    }

    public void setCredentials(Map<String, Object> credentials) {
        if (credentials == null) {
            this.credentials = Maps.newHashMap();
        }else{
            this.credentials = credentials;
        }
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getBindingId() {
        return bindingId;
    }

    public Map<String, Object> getCredentials() {
        return credentials;
    }

    public String getPlanId() {
        return planId;
    }

    public String getAppGuid() {
        return appGuid;
    }
}
