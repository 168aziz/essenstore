package com.essenstore.converter;


import com.essenstore.entity.Gender;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class GenderFormatter implements Formatter<Gender> {

    @Override
    public Gender parse(String name, Locale locale) {
        Gender gender = Gender.EMPTY;
        switch (name.toLowerCase()) {
            case "men":
            case "man":
                gender = Gender.MAN;
                break;
            case "women":
            case "woman":
                gender = Gender.WOMAN;
                break;
            case "boys":
            case "boy":
                gender = Gender.BOY;
                break;
            case "girls":
            case "girl":
                gender = Gender.GIRL;
                break;
        }
        return gender;
    }

    @Override
    public String print(Gender gender, Locale locale) {
        return gender.toString();
    }

}