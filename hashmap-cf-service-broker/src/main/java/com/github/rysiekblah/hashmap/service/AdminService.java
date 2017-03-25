package com.github.rysiekblah.hashmap.service;

import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import com.github.rysiekblah.hashmap.model.ServiceInstance;
import com.github.rysiekblah.hashmap.repositories.HashMapBindingInstanceRepository;
import com.github.rysiekblah.hashmap.repositories.HashMapInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Tomasz_Kozlowski on 3/18/2017.
 */
@Service
public class AdminService {

    @Autowired
    private HashMapBindingInstanceRepository bindingInstanceRepository;

    @Autowired
    private HashMapInstanceRepository instanceRepository;

    @Value("${hashmap.version:unknown}")
    private String version;

    public String getVersion() {
        return version;
    }

    public ServiceBindingInstance getServiceBindingInstance(String id) {
        return bindingInstanceRepository.getBindingInstance(id);
    }

    public int getBoundedInstancesCount() {
        return bindingInstanceRepository.count();
    }

    public ServiceInstance getServiceInstance(String id) {
        return instanceRepository.getServiceInstance(id);
    }

    public int getInstancesCount() {
        return instanceRepository.count();
    }
}
