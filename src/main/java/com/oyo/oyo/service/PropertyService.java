package com.oyo.oyo.service;

import com.oyo.oyo.dto.PropertyDto;
import com.oyo.oyo.entity.Property;
import com.oyo.oyo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    public List<Property> findPropertyByLocation(String locationName) {
        List<Property> properties = propertyRepository.findPropertyByLocation(locationName);
        return properties;
    }

//    public Property addProperty(PropertyDto propertyDto
//                                ) {
//        Property property = new Property();
//        property.setId(propertyDto.getId());
//        property.setPropertyName(propertyDto.getPropertyName());
//        property.setBedrooms(propertyDto.getBedrooms());
//        property.setBathrooms(propertyDto.getBathrooms());
//        property.setGuests(propertyDto.getGuests());
//        property.setNightlyPrice(propertyDto.getNightlyPrice());
//        Property savedProperty = propertyRepository.save(property);
//        return savedProperty;
//    }
}
