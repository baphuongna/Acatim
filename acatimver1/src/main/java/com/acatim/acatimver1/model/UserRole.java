package com.acatim.acatimver1.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class UserRole {
 
    @Id
    @GeneratedValue
    private int id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel appUser;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Role appRole;

     
}