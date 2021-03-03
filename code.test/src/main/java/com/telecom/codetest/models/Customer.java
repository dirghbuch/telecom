package com.telecom.codetest.models;

import java.util.Set;

public class Customer {
    int id;
    String name;
    Set<Phone> phones;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Phone> getPhoneNumbers() {
        return phones;
    }

    public void setPhoneNumbers(Set<Phone> phones) {
        this.phones = phones;
    }
}
