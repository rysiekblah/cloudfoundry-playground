package com.github.rysiekblah.hashmap.controller;

import com.github.rysiekblah.hashmap.HashMapApplication;
import com.github.rysiekblah.hashmap.exception.InstanceException;
import com.github.rysiekblah.hashmap.repositories.HashMapBindingInstanceRepository;
import com.github.rysiekblah.hashmap.service.MapService;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tomasz_Kozlowski on 3/18/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashMapApplication.class)
public class MapControllerTest {

    @Autowired
    private MapController mapController;

    @Autowired
    @InjectMocks
    private MapService mapService;

    @Mock
    private HashMapBindingInstanceRepository repository;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mapController).build();
    }

    @Test
    public void testWeCanInsertValueToExistingInstance() throws Exception {

        when(repository.isInstanceExisis(any(String.class))).thenReturn(true);

        mockMvc.perform(
                post("/hashmap/binding-instance-id/put")
                    .param("key", "kozlowst")
                    .param("value", "Tomasz Kozlowski"))
                .andExpect(status().isOk());

        assertThat(mapService.getInstance("binding-instance-id").size(), is(1));
        assertThat(mapService.getInstance("binding-instance-id"), hasEntry("kozlowst", "Tomasz Kozlowski"));
    }

    @Test
    public void testTryInsertToNotExistingInstance() throws Exception {
        when(repository.isInstanceExisis(any(String.class))).thenReturn(false);
        mockMvc.perform(
                post("/hashmap/binding-instance-id/put")
                        .param("key", "kozlowst")
                        .param("value", "Tomasz Kozlowski"))
                .andExpect(status().isUnprocessableEntity());
    }

}
