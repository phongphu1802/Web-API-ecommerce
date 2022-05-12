package com.spring.vn.Repository;

import com.spring.vn.Entity.Transport;

import com.spring.vn.Entity.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportRepository extends JpaRepository<Transport,Integer> {
    public Optional<Transport> findTransportByTransportName(String transportName);
}
