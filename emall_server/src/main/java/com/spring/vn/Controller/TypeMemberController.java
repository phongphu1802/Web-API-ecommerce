package com.spring.vn.Controller;

import com.spring.vn.Entity.ResponseObject;
import com.spring.vn.Entity.TypeMember;
import com.spring.vn.Services.TypeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/typemember")
public class TypeMemberController {
    @Autowired
    private TypeMemberService typeMemberService;

    @GetMapping("")
    //this request is: http://localhost:8080/api/typemember
    ResponseEntity<ResponseObject> getAllTypeMember() {
        List<TypeMember> typeMembers = typeMemberService.getAllTypeMember();
        return typeMembers.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find typemember", "")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query typemember successfully", typeMembers)
                );
    }

    //Find By ID
    @GetMapping("/{id}")
    //this request is: http://localhost:8080/api/typemember/{id}
    ResponseEntity<ResponseObject> findByID(@PathVariable Long id) {
        Optional<TypeMember> typeMember = typeMemberService.findTypeMemberID(id);
        return typeMember.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query typemember successfully", typeMember)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find typemember with id = " + id, "")
                );
    }

    //Insert new TypeMember with POST method
    @PostMapping("/insert")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/typemember/insert
    ResponseEntity<ResponseObject> insertTypeMember(@RequestBody TypeMember newTypeMember) {
        //Test Name
        Optional<TypeMember> typeMember = typeMemberService.findTypeMemberByTypeMemberName(newTypeMember.getTypeName());
        return typeMember.isPresent() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Typemembername was used","")
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert typemembername successfully", typeMemberService.addTypeMember(newTypeMember))
                );
    }

    //Update, upset = update if found, otherwise insert
    @PutMapping("/{id}")
    //Postman : Raw, JSON
    //this request is: http://localhost:8080/api/typemember/{id}
    ResponseEntity<ResponseObject> updateTypeMember(@RequestBody TypeMember newTypeMember, @PathVariable long id){
        Optional<TypeMember> updateTypeMember = Optional.ofNullable(typeMemberService.findTypeMemberID(id)
                .map(TypeMember -> {
                    TypeMember.setTypeName(newTypeMember.getTypeName());
                    TypeMember.setState(newTypeMember.getState());
                    return typeMemberService.addTypeMember(TypeMember);
                }).orElseGet(() -> {
                    newTypeMember.setTypeMemberID(id);
                    return typeMemberService.addTypeMember(newTypeMember);
                }));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update typemember successfully",updateTypeMember)
        );
    }

    //Delete a TypeMember
    @DeleteMapping("/{id}")
    //this request is: http://localhost:8080/api/typemember/{id}
    ResponseEntity<ResponseObject> deleteTypeMember(@PathVariable long id){
        boolean exists = typeMemberService.findTypeMemberByID(id);
        if(exists){
            typeMemberService.deleteTypeMember(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete typemember successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Can find typemember to delete","")
        );
    }
}
