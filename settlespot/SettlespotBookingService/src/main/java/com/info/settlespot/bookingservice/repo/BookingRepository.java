package com.info.settlespot.bookingservice.repo;

import com.info.settlespot.bookingservice.entity.Booking;
import com.info.settlespot.bookingservice.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByTenantId(Integer tenantId);
    List<Booking> findByHostId(Integer hostId);
    List<Booking> findByHostIdAndStatus(Integer hostId, BookingStatus status);
    List<Booking> findByPropertyId(Integer propertyId);
}