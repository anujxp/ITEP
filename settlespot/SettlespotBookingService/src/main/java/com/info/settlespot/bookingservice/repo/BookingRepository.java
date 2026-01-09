package com.info.settlespot.bookingservice.repo;

import com.info.settlespot.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByTenantId(Integer tenantId);
    List<Booking> findByHostId(Integer hostId);
}