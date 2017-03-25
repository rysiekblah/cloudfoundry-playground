package com.github.rysiekblah.hashmap.controller;

import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import com.github.rysiekblah.hashmap.model.ServiceInstance;
import com.github.rysiekblah.hashmap.repositories.HashMapBindingInstanceRepository;
import com.github.rysiekblah.hashmap.repositories.HashMapInstanceRepository;
import com.github.rysiekblah.hashmap.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/18/2017.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/ver", method = RequestMethod.GET)
    private ResponseEntity<String> version() {
        return new ResponseEntity<>(adminService.getVersion(), HttpStatus.OK);
    }

    @RequestMapping(value = "/info/binding/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceBindingInstance> bindingInfo(@PathVariable("id") String id) {
        ServiceBindingInstance instance = adminService.getServiceBindingInstance(id);
        System.out.println(">>>> Instance: " + instance);
        return new ResponseEntity<>(instance, instance == null ? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

    @RequestMapping(value = "/info/binding/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> bindingCount() {
        return new ResponseEntity<>(adminService.getBoundedInstancesCount(), HttpStatus.OK);
    }

    @RequestMapping(value = "/info/instance/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceInstance> instanceInfo(@PathVariable("id") String id) {
        return new ResponseEntity<>(adminService.getServiceInstance(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/info/instance/count")
    public ResponseEntity<Integer> instanceCount() {
        return new ResponseEntity<>(adminService.getInstancesCount(), HttpStatus.OK);
    }

}
