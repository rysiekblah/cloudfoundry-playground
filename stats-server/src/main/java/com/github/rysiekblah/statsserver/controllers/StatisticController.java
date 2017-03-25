package com.github.rysiekblah.statsserver.controllers;

import com.github.rysiekblah.statsserver.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 3/20/2017.
 */
@RestController
@RequestMapping("/stats")
public class StatisticController {

    @Autowired
    private StatisticService service;

    @RequestMapping(value = "/info/storage", method = RequestMethod.GET)
    public String storageVersion() {
        return service.getHashMapVersion();
    }

    @RequestMapping(value = "/click", method = RequestMethod.GET)
    public String click(@RequestParam("buttonName") String buttonName) {
        return service.handleClick(buttonName);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getAll() {
        Map<String, String> stats = service.getAll();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
