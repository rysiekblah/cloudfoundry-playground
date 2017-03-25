package com.github.rysiekblah.hashmap.controller;

import com.github.rysiekblah.hashmap.HashMapApplication;
import com.github.rysiekblah.hashmap.model.ServiceBindingInstance;
import com.github.rysiekblah.hashmap.repositories.HashMapBindingInstanceRepository;
import com.github.rysiekblah.hashmap.repositories.HashMapInstanceRepository;
import com.github.rysiekblah.hashmap.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tomasz_Kozlowski on 3/19/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashMapApplication.class)
@ActiveProfiles("test")
public class AdminControllerTest {

    @Autowired
    private AdminController controller;

    @Autowired
    @InjectMocks
    private AdminService service;

    @Mock
    private HashMapBindingInstanceRepository bindingInstanceRepository;

    @Mock
    private HashMapInstanceRepository instanceRepository;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testVersion() throws Exception {
        mockMvc.perform(get("/admin/ver")).andExpect(status().isOk());
    }

//    @Test
//    public void testBindingInstanceNotExists() throws Exception {
//        mockMvc.perform(get("/admin/info/binding/test-id")).andExpect(status().isGone());
//    }

    @Test
    public void testBindingInstanceExists() throws Exception {
        when(bindingInstanceRepository.getBindingInstance(any(String.class))).thenReturn(createBindingInstance());
        mockMvc.perform(get("/admin/info/binding/test-id")).andExpect(status().isOk());
    }

    @Test
    public void testBindingCount() throws Exception {
        mockMvc.perform(get("/admin/info/binding/count")).andExpect(status().isOk());
    }

    private ServiceBindingInstance createBindingInstance() {
        return new ServiceBindingInstance(new CreateServiceInstanceBindingRequest());
    }


}
