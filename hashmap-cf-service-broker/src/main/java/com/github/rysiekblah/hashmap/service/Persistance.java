package com.github.rysiekblah.hashmap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Tomasz_Kozlowski on 3/22/2017.
 */
@Configuration
public class Persistance {

    @Autowired
    private ObjectMapper mapper;

    public final String FILENAME = "bindingInfo.json";

    public ServiceBindingInstance read() throws IOException {
        File file = new File(FILENAME);
        if (!file.exists()) {
            return null;
        }
        return mapper.readValue(new File(FILENAME), ServiceBindingInstance.class);
    }

    public void write(ServiceBindingInstance instance) throws IOException {
        mapper.writeValue(new File(FILENAME), instance);
    }

    public void clean() {
        File file = new File(FILENAME);
        if (!file.delete()) {
            System.out.println("CAN'T REMOVE FILE");
        }
        System.out.println("FILE DELETED: " + !file.exists());
    }
}
