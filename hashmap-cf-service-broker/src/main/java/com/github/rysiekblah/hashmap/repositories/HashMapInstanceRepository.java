package com.github.rysiekblah.hashmap.repositories;

import com.google.common.collect.Maps;
import com.github.rysiekblah.hashmap.model.ServiceInstance;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/14/2017.
 */
@Repository
public class HashMapInstanceRepository {

    private Map<String, ServiceInstance> instances;

    public HashMapInstanceRepository() {
        this.instances = Maps.newConcurrentMap();
    }

    public ServiceInstance getServiceInstance(String id) {
        return instances.get(id);
    }

    public int count() {
        return instances.size();
    }

    public boolean isInstanceExists(String id) {
        return instances.containsKey(id);
    }

    public void create(ServiceInstance instance) {
        instances.put(instance.getInstanceId(), instance);
    }

    public void delete(ServiceInstance instance) {
        instances.remove(instance.getInstanceId());
    }

    public void update(ServiceInstance instance) {
        instances.put(instance.getInstanceId(), instance);
    }

}
