package com.github.rysiekblah.hashmap.repositories;

import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import com.github.rysiekblah.hashmap.service.Persistance;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/14/2017.
 */
@Repository
public class HashMapBindingInstanceRepository {

    private Persistance persistance;

    private Map<String, ServiceBindingInstance> bindingInstances = Maps.newConcurrentMap();

    @Autowired
    public HashMapBindingInstanceRepository(Persistance persistance) {

        this.persistance = persistance;

        bindingInstances.put("123",
                new ServiceBindingInstance(
                        new CreateServiceInstanceBindingRequest("SERVICE-DEF-ID", "PLAN-ID", "APP-GUID", null)));

        try {
            ServiceBindingInstance instance = persistance.read();
            if (instance == null) {
                System.out.println("NO PERSTSTNACE BINDING INFO");
                return;
            }
            System.out.println("id " + instance.getBindingId());
            bindingInstances.put(instance.getBindingId(), instance);
            System.out.println("PERSISTANT READ DONE");
        } catch (IOException e) {
            System.out.println("ERROR! COULD NOT READ INSTANCE: " + e.getMessage());
        }
    }

    public boolean isInstanceExisis(String id) {
        return bindingInstances.containsKey(id);
    }

    public ServiceBindingInstance getBindingInstance(String id) {
        return bindingInstances.get(id);
    }

    public int count() {
        return bindingInstances.size();
    }

    public void create(ServiceBindingInstance instance) {
        bindingInstances.put(instance.getBindingId(), instance);
        try {
            persistance.write(instance);
        } catch (IOException e) {
            System.out.println("ERROR! COULD NOT PERSIST INSTANCE: " + e.getMessage());
        }
    }

    public void delete(String id) {
        bindingInstances.remove(id);
    }

}
