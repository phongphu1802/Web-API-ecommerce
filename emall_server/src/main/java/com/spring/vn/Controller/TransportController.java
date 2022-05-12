package com.spring.vn.Controller;

import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.Transport;
import com.spring.vn.Entity.TypeProduct;
import com.spring.vn.Services.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/transport")
public class TransportController {
    @Autowired
    private TransportService transportService;

    @GetMapping("")
    //this request is: http://localhost:8080/api/transport
    ResponseEntity<ResponseObject> getAllTypeMember() {
        List<Transport> transports = transportService.getAllTransport();
        return transports.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find transport", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query transport successfully", transports)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/transport/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable int id) {
        Optional<Transport> transport = transportService.findTransportID(id);
        return transport.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query transport successfully", transport)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find transport with id = " + id, "")
                );
    }

    //Insert new Transport with POST method
    @PostMapping("/insert")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/transport/insert
    ResponseEntity<ResponseObject> insertTransport(@RequestBody Transport newTransport) {
        //Test Name
        Optional<Transport> transport = transportService.findTransportByTransportName(newTransport.getTransportName());
        return transport.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Transportname was used","")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Insert transport successfully", transportService.addTransport(newTransport))
                );
    }

    @PutMapping("/{id}")
        //Postman : Raw, JSON
        //Update, upset = update if found, otherwise insert
        //this request is: http://localhost:8080/api/transport/{id}
    ResponseEntity<ResponseObject> updateTransport(@RequestBody Transport newTransport, @PathVariable int id){
        Optional<Transport> updateTransport = Optional.ofNullable(transportService.findTransportID(id)
                .map(Transport -> {
                    Transport.setTransportName(newTransport.getTransportName());
                    Transport.setTransportFee(newTransport.getTransportFee());
                    Transport.setState(newTransport.getState());
                    return transportService.addTransport(Transport);
                }).orElseGet(() -> {
                    newTransport.setTransportID(id);
                    return transportService.addTransport(newTransport);
                }));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update transport successfully",updateTransport)
        );
    }

    //Delete a Transport
    @DeleteMapping("/{id}")
    //this request is: http://localhost:8080/api/transport/{id}
    ResponseEntity<ResponseObject> deleteTransport(@PathVariable int id){
        boolean exists = transportService.findTransportByID(id);
        if(exists){
            transportService.deleteTransport(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete transport successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can find transport to delete","")
        );
    }
}
