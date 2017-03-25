package com.github.rysiekblah.hashmap.service;

import com.google.common.collect.Maps;
import com.github.rysiekblah.hashmap.exception.InstanceException;
import com.github.rysiekblah.hashmap.repositories.HashMapBindingInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 2/27/2017.
 */
@Service
public class MapService {

    @Autowired
    private HashMapBindingInstanceRepository repository;

    private Map<String, Map<String, String>> instances = Maps.newConcurrentMap();

    public void createInstance(String id) {
        instances.put(id, Maps.newHashMap());
    }

    public Map<String, String> getInstance(String id) {

        if (!repository.isInstanceExisis(id)) {
            throw new InstanceException("Instance not available: " + id);
        }

        if (!instances.containsKey(id)) {
            instances.put(id, Maps.newHashMap());
        }

        return instances.get(id);
    }
}
