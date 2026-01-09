package com.info.settlespot.propertyservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.settlespot.propertyservice.entity.Property;
import com.info.settlespot.propertyservice.enums.PropertyType;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    // 1. Find by Host (My Dashboard)
    List<Property> findByHostId(Integer hostId);

    // 2. Find by City (Broad Search)
    List<Property> findByCity(String city);

    // 3. Find by Area (Specific Locality Search)
    // e.g., User searches "Hinjewadi"
    List<Property> findByArea(String area);

    // 4. Find by City AND Area (Refined Search)
    // e.g., "Pune" -> "Wakad"
    List<Property> findByCityAndArea(String city, String area);

    // 5. Find by Type
    List<Property> findByPropertyType(PropertyType propertyType);
    
    // 6. Find by City and Type
    List<Property> findByCityAndPropertyType(String city, PropertyType propertyType);
    
    List<Property> findByCityAndPropertyTypeAndArea(String city, PropertyType propertyType, String area);

}