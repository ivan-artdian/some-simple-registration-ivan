package com.mitrais.test.usermanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "month_of_birth")
    private String monthOfBirth;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "year_of_birth")
    private String yearOfBirth;
    @Column
    private String gender;
    @Column
    private String email;
}
