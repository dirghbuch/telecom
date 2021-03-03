package com.telecom.codetest.controller;

import com.telecom.codetest.Application;
import com.telecom.codetest.models.Customer;
import com.telecom.codetest.models.Phone;
import com.telecom.codetest.models.Status;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @GetMapping("/phone/get/all")
    public Set<Phone> getAllPhoneNumbers(){
        // Pagination can be added to retrieve in chunks
        Set<Phone> phoneSet = new HashSet<>();
        for (Customer customer:Application.customerSet) {
            phoneSet.addAll(customer.getPhoneNumbers());
        }
        return phoneSet;
    }

    @GetMapping("/phone/get/customer/{id}")
    public Set<Phone> getPhoneNumbersByCustomerId(@PathVariable(value = "id") int id){
        Set<Phone> phoneSet = new HashSet<>();
        Customer customerFound = Application.customerSet.stream()
                .filter(customer -> id ==customer.getId())
                .findAny()
                .orElse(null);
        if (customerFound != null) {
            phoneSet = customerFound.getPhoneNumbers();
        }
        return phoneSet;
    }

    @PutMapping("/phone/activate")
    public boolean activatePhone(@RequestBody String phone){
        Phone phoneNumber = Application.customerSet.stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .filter(thisPhone -> thisPhone.getValue().equals(phone))
                .findAny()
                .orElse(null);
        if(phoneNumber != null) {
            phoneNumber.setStatus(Status.ACTIVE);
            return true;
        }
        return false;
    }

}
