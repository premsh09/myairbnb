package com.oyo.oyo.controller;

import com.oyo.oyo.dto.PropertyDto;
import com.oyo.oyo.entity.Country;
import com.oyo.oyo.entity.Location;
import com.oyo.oyo.entity.Property;
import com.oyo.oyo.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oyo/properties")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

//    @PostMapping("/addProperty")
//    public ResponseEntity<?> addProperty(@RequestBody PropertyDto propertyDto){
//        Property property = propertyService.addProperty(propertyDto);
//        return new ResponseEntity<>(property, HttpStatus.CREATED);
//    }

    @GetMapping("/{locationName}")
    public ResponseEntity<List<Property>> findProperty(@PathVariable String locationName){
       List<Property> properties = propertyService.findPropertyByLocation(locationName);
       return new ResponseEntity<>(properties, HttpStatus.OK);
    }
}
