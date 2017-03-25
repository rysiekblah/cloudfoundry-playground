package com.github.rysiekblah.hashmap.controller;

import com.github.rysiekblah.hashmap.exception.InstanceException;
import com.github.rysiekblah.hashmap.model.ErrorMessage;
import com.github.rysiekblah.hashmap.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Tomasz_Kozlowski on 2/27/2017.
 */
@RestController
@RequestMapping("/hashmap")
public class MapController {

    @Autowired
    private MapService service;

    @RequestMapping(value = "/{bindingId}/size", method = RequestMethod.GET)
    public ResponseEntity<Integer> size(@PathVariable("bindingId") String bindingId) {
        return new ResponseEntity<>(service.getInstance(bindingId).size(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{bindingId}/isempty", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isEmpty(@PathVariable("bindingId") String bindingId) {
        return new ResponseEntity<Boolean>(service.getInstance(bindingId).isEmpty(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{bindingId}/containskey/{key}")
    public boolean containsKey(
            @PathVariable("bindingId") String bindingId,
            @PathVariable("key") String key) {
        return service.getInstance(bindingId).containsKey(key);
    }


    public boolean containsValue(Object value) {
        return false;
    }

    @RequestMapping(value = "/{bindingId}/get", method = RequestMethod.GET)
    public ResponseEntity<String> get(
            @PathVariable("bindingId") String bindingId,
            @RequestParam("key") String key) {
        String val = service.getInstance(bindingId).get(key);
        return new ResponseEntity<>(val, val == null ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @RequestMapping(value = "/{bindingId}/put", method = RequestMethod.POST)
    public ResponseEntity<String> put(
            @PathVariable("bindingId") String bindingId,
            @RequestParam("key") String key,
            @RequestParam("value") String value) {
        service.getInstance(bindingId).put(key, value);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @RequestMapping(value = "/{bindingId}/remove", method = RequestMethod.PUT)
    public void remove(
            @PathVariable("bindingId") String bindingId,
            @RequestParam("key") String key) {
        service.getInstance(bindingId).remove(key);
    }

    @RequestMapping(value = "/{bindingId}")
    public ResponseEntity<Map<String, String>> getAll(@PathVariable("bindingId") String bindingId) {
        Map<String, String> content = service.getInstance(bindingId);
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @ExceptionHandler(InstanceException.class)
    public ResponseEntity<ErrorMessage> handleInstanceException(InstanceException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    public void putAll(Map m) {
//
//    }
//
//    public void clear() {
//
//    }
//
//    public Set keySet() {
//        return null;
//    }
//
//    public Collection values() {
//        return null;
//    }
//
//    public Set<Map.Entry> entrySet() {
//        return null;
//    }

}
