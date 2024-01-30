package com.commerzbank.library.dto;

import com.commerzbank.library.model.UserType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class PersonDto {
    private String firstName;
    private String lastName;
    private UserTypeDto userType;
}
