package com.info.settlespot.propertyservice.repo;

import com.info.settlespot.propertyservice.entity.Property;
import com.info.settlespot.propertyservice.enums.PropertyApprovalStatus;
import com.info.settlespot.propertyservice.enums.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    List<Property> findByHostId(Integer hostId);
    List<Property> findByApprovalStatus(PropertyApprovalStatus status);
    List<Property> findByApprovalStatusAndIsAvailable(PropertyApprovalStatus status, boolean available);
    List<Property> findByCity(String city);
    List<Property> findByArea(String area);
    List<Property> findByCityAndArea(String city, String area);
    List<Property> findByPropertyType(PropertyType propertyType);
    List<Property> findByCityAndPropertyType(String city, PropertyType propertyType);
    List<Property> findByCityAndPropertyTypeAndArea(String city, PropertyType propertyType, String area);

    @Query("SELECT p FROM Property p WHERE " +
            "(:city IS NULL OR p.city = :city) AND " +
            "(:type IS NULL OR CAST(p.propertyType AS string) = :type) AND " +
            "(:minPrice IS NULL OR p.rentAmount >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.rentAmount <= :maxPrice)")
    List<Property> findByFilters(@Param("city") String city,
                                 @Param("type") String type,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);
}