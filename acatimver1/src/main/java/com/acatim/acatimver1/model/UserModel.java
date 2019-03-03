package com.acatim.acatimver1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User", //
uniqueConstraints = { //
        @UniqueConstraint(name = "USER_UK", columnNames = {"User_Name", "email"}) })
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;
    
    @Column(name = "full_name")
    @NotEmpty(message = "*Please provide your full name")
    private String fullName;
    
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    
    @Column(name = "user_name")
    @NotEmpty(message = "*Please provide your user name")
    private String userName;
    
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    
    @Column(name = "create_date")
    @NotEmpty(message = "*Please provide your create date")
    private String createDate;
    
    @Column(name = "phone")
    @NotEmpty(message = "*Please provide your phone")
    private String phone;
    
    @Column(name = "address")
    @NotEmpty(message = "*Please provide your address")
    private String address;
   
    @Column(name = "active")
    private boolean active;
    
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;

}
