package com.telecom.codetest.controller;

import com.telecom.codetest.Application;
import com.telecom.codetest.models.Customer;
import com.telecom.codetest.models.Phone;
import com.telecom.codetest.models.Status;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(Controller.class)
class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        Customer[] customers = new Customer[10];
        Set<Phone>[] phoneSets = new HashSet[10];
        int initialPhone = 400000000;
        for (int i = 0; i < 50; i++) {
            int customerIndex = i % 10;
            if (customers[customerIndex] == null) {
                customers[customerIndex] = new Customer(customerIndex, RandomStringUtils.randomAlphabetic(5));
                phoneSets[customerIndex] = new HashSet<>();
            }
            String phoneNumber = "0" + initialPhone;
            Phone phone = new Phone(phoneNumber, Status.ACTIVE);
            phoneSets[customerIndex].add(phone);
            initialPhone++;
        }
        for (int i = 0; i < 10; i++) {
            customers[i].setPhoneNumbers(phoneSets[i]);
            Application.customerSet.add(customers[i]);
        }
    }

    @Test
    void getAllPhoneNumbers() throws Exception {
        mvc.perform(get("/api/v1/phone/get/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(50)));

    }

    @Test
    void getPhoneNumbersByCustomerId() throws Exception{
        mvc.perform(get("/api/v1/phone/get/customer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void activatePhoneNumber() throws Exception{
        mvc.perform(put("/api/v1/phone/activate")
                .content("0400000000") // Ideally this should be json body
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}