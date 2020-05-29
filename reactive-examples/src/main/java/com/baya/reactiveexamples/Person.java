package com.baya.reactiveexamples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String firstName;
    private String lastName;

    public String sayMyName() {
        return "My Name is " + firstName + lastName + ".";
    }
}
