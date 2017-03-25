package com.github.rysiekblah.hashmap.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.UpdateServiceInstanceRequest;

/**
 * Created by Tomasz_Kozlowski on 2/26/2017.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceInstance {

    @JsonSerialize
    private String organizationGuid;
    @JsonSerialize
    private String serviceDefinitionId;
    @JsonSerialize
    private String planId;
    @JsonSerialize
    private String instanceId;
    @JsonSerialize
    private String spaceGuid;

    public ServiceInstance(CreateServiceInstanceRequest request) {
        organizationGuid = request.getOrganizationGuid();
        serviceDefinitionId = request.getServiceDefinitionId();
        planId = request.getPlanId();
        instanceId = request.getServiceInstanceId();
        spaceGuid = request.getSpaceGuid();
    }

    public ServiceInstance(DeleteServiceInstanceRequest request) {
        instanceId = request.getServiceInstanceId();
        planId = request.getPlanId();
        serviceDefinitionId = request.getServiceDefinitionId();
    }

    public ServiceInstance(UpdateServiceInstanceRequest request) {
        serviceDefinitionId = request.getServiceDefinitionId();
        instanceId = request.getServiceInstanceId();
        planId = request.getPlanId();
    }

    public String getOrganizationGuid() {
        return organizationGuid;
    }

    public String getServiceDefinitionId() {
        return serviceDefinitionId;
    }

    public String getPlanId() {
        return planId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getSpaceGuid() {
        return spaceGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInstance instance = (ServiceInstance) o;
        return Objects.equal(serviceDefinitionId, instance.serviceDefinitionId) &&
                Objects.equal(planId, instance.planId) &&
                Objects.equal(instanceId, instance.instanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(serviceDefinitionId, planId, instanceId);
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "organizationGuid='" + organizationGuid + '\'' +
                ", serviceDefinitionId='" + serviceDefinitionId + '\'' +
                ", planId='" + planId + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", spaceGuid='" + spaceGuid + '\'' +
                '}';
    }
}
