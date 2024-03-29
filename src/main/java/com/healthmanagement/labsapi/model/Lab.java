package com.healthmanagement.labsapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "lab")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Status status;
    private String username;
    private OffsetDateTime registerDate;
}
