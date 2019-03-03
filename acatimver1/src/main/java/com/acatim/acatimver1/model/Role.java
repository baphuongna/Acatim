package com.acatim.acatimver1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role", //
uniqueConstraints = { //
        @UniqueConstraint(name = "ROLE_UK", columnNames = "role_name") })
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private int id;
    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;
}
