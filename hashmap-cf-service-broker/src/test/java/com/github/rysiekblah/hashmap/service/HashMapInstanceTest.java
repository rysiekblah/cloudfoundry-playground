package com.github.rysiekblah.hashmap.service;

import com.github.rysiekblah.hashmap.HashMapApplication;
import com.google.common.collect.Maps;
import com.github.rysiekblah.hashmap.repositories.HashMapInstanceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceResponse;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Tomasz_Kozlowski on 3/18/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashMapApplication.class)
public class HashMapInstanceTest {

    private final String SVC_DEF_ID = "f7798cc3-b2fd-426f-9d7d-0deaf6b80365";
    private final String PLAN_ID = "";
    private final String ORG_ID = "";
    private final String SPACE_GUID = "";
    private Map<String, Object> params = Maps.newHashMap();

    @Autowired
    @InjectMocks
    private HashMapInstanceService instanceService;

    @Mock
    private HashMapInstanceRepository instanceRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createInstanceServiceTest() {
        when(instanceRepository.isInstanceExists(any(String.class))).thenReturn(false);
        CreateServiceInstanceResponse response = instanceService.createServiceInstance(createRequest());
        assertNotNull(response);
        assertNull(response.getDashboardUrl());
        assertFalse(response.isInstanceExisted());
        assertFalse(response.isAsync());
    }

    @Test(expected = ServiceInstanceExistsException.class)
    public void createInstanceFailedInstanceExistsTest() {
        when(instanceRepository.isInstanceExists(any(String.class))).thenReturn(true);
        instanceService.createServiceInstance(createRequest());
    }

    private CreateServiceInstanceRequest createRequest() {
        return new CreateServiceInstanceRequest(SVC_DEF_ID, PLAN_ID, ORG_ID, SPACE_GUID, params)
                .withServiceInstanceId("instance-id");
    }

}
