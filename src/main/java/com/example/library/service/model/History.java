package com.example.library.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "history")
@Getter
@Setter
@NoArgsConstructor
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reader_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Book book;

    @Column(name = "time_issue", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeIssue;

    @Column(name = "time_return", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeReturn;

}
