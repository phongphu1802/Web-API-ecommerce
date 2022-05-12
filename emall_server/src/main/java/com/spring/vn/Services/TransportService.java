package com.spring.vn.Services;

import java.util.List;
import java.util.Optional;

import com.spring.vn.Entity.Transport;
import com.spring.vn.Entity.TypeMember;
import com.spring.vn.Repository.TransportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TransportService {
    @Autowired TransportRepository transportRepository;
    public Optional<Transport> findTransportByTransportName(String transportName){return transportRepository.findTransportByTransportName(transportName);}
    public Transport addTransport(Transport tranSport){
        return transportRepository.save(tranSport);
    }
    public List<Transport> getAllTransport(){
        return transportRepository.findAll();
    }
    public Optional<Transport> findTransportID(int transportID){
        return transportRepository.findById(transportID);
    }
    public Boolean findTransportByID(int id){
        return transportRepository.existsById(id);
    }
    public boolean deleteTransport(int transportID){
        transportRepository.deleteById(transportID);
        return true;
    }
}
