package com.github.rysiekblah.hashmap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rysiekblah.hashmap.HashMapApplication;
import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Tomasz_Kozlowski on 3/22/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashMapApplication.class)
public class PersistanceTest {

    @Autowired
    private Persistance persistance;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void init() {
        persistance.clean();
    }

    @After
    public void cleanup() {
        persistance.clean();
    }

    @Test
    public void testWriteRead() throws IOException, InterruptedException {

        ServiceBindingInstance instance =
                new ServiceBindingInstance(
                        new CreateServiceInstanceBindingRequest("serviceDefinitionId", "planId", "appGuid", null)
                                .withBindingId("1234567890"));

        persistance.write(instance);

        Thread.sleep(200);

        ServiceBindingInstance instance12 = persistance.read();

        System.out.println("ORIG: " + mapper.writeValueAsString(instance));
        System.out.println("READ: " + mapper.writeValueAsString(instance12));

        assertEquals(instance.getAppGuid(), instance12.getAppGuid());

    }

    @Test
    public void testReadOnly() throws IOException {
        ServiceBindingInstance instance = persistance.read();
        assertNull(instance);
    }

}
