package com.example.library.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "reader_expired")
@Getter
@Setter
@NoArgsConstructor
public class ReaderExpired implements Serializable {

    @Column(name = "reader_id")
    private Long getReaderId() {
        return this.reader.getId();
    }


    @Column(name = "book_id")
    private Long getBookId() {
        return this.book.getId();
    }

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

    @Column(name = "time_issue")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeIssue;

    @Column(name = "time_return")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeReturn;

    @Column(name = "expired")
    private Integer expired;
}
