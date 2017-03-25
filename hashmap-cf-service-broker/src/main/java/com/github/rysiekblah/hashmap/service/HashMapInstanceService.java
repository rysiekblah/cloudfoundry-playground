package com.github.rysiekblah.hashmap.service;

import com.github.rysiekblah.hashmap.repositories.HashMapInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

/**
 * Created by Tomasz_Kozlowski on 2/25/2017.
 */
@Service
public class HashMapInstanceService implements ServiceInstanceService {

    private Logger logger = LoggerFactory.getLogger(HashMapInstanceService.class);

    @Autowired
    private HashMapInstanceRepository repository;

    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
        com.github.rysiekblah.hashmap.model.ServiceInstance instance = new com.github.rysiekblah.hashmap.model.ServiceInstance(request);
        logger.info("InstanceID: " + instance.getInstanceId());
        if (repository.isInstanceExists(instance.getInstanceId())) {
            throw new ServiceInstanceExistsException(instance.getInstanceId(), instance.getServiceDefinitionId());
        }

        repository.create(instance);

        return new CreateServiceInstanceResponse();
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
        return new GetLastServiceOperationResponse().withOperationState(OperationState.SUCCEEDED);
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
        com.github.rysiekblah.hashmap.model.ServiceInstance instance = new com.github.rysiekblah.hashmap.model.ServiceInstance(request);

        if (!repository.isInstanceExists(instance.getInstanceId())) {
            throw new ServiceInstanceDoesNotExistException(instance.getInstanceId());
        }

        repository.delete(instance);

        return new DeleteServiceInstanceResponse();
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
        com.github.rysiekblah.hashmap.model.ServiceInstance instance = new com.github.rysiekblah.hashmap.model.ServiceInstance(request);

        if (!repository.isInstanceExists(instance.getInstanceId())) {
            throw new ServiceInstanceDoesNotExistException(instance.getInstanceId());
        }

        repository.update(instance);

        return new UpdateServiceInstanceResponse();
    }
}
