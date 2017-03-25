package com.github.rysiekblah.hashmap.service;

import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import com.github.rysiekblah.hashmap.repositories.HashMapBindingInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 2/26/2017.
 */
@Service
public class HashmapInstanceBindingService implements ServiceInstanceBindingService {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private HashMapBindingInstanceRepository repository;

    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {

        if (repository.isInstanceExisis(request.getBindingId())) {
            throw new ServiceInstanceBindingExistsException(request.getServiceInstanceId(), request.getBindingId());
        }

        Map<String, Object> bindingCredentials = credentialService.getCredentials(request.getServiceDefinitionId(), request.getBindingId());
        ServiceBindingInstance bindingInstance = new ServiceBindingInstance(request, bindingCredentials);

        repository.create(bindingInstance);

        return new CreateServiceInstanceAppBindingResponse().withCredentials(bindingCredentials);
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {

        if (!repository.isInstanceExisis(request.getBindingId())) {
            throw new ServiceInstanceBindingDoesNotExistException(request.getBindingId());
        }

        repository.delete(request.getBindingId());

    }
}
