package com.example.library.service.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isNew;

    @Column(nullable = false)
    private Boolean isHandle;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;

    @Column(nullable = false)
    private Integer count;
}
