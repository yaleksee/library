package com.example.library.facade;

import com.example.library.service.*;
import com.example.library.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by alekseenkoyuri1989@gmail.com
 * on 3/22/20
 */
@PropertySource("classpath:private.properties")
@Service
@RequiredArgsConstructor
public class Librarian {
    private final ReaderService readerService;
    private final HistoryService historyService;
    private final BookService bookService;
    private final IssuedService issuedService;
    private final ExpiredService expiredService;


    @Value(value = "${standard}")
    private int standard;
    @Value(value = "${newBook}")
    private int newBook;
    @Value(value = "${limitCount}")
    private int limitCount;
    @Value(value = "${minimum}")
    private int minimum;

    @Transactional
    public Issued giveOutBook(@NotNull Long bookId, @NotNull Long readerId) {
        final Book book = bookService.searchOne(bookId);
        final Reader reader = readerService.searchOne(readerId);
        final History history = new History();
        final Issued issued = new Issued();

        Integer periodIssue = standard;
        Date createdDate = book.getCreated();
        Period period = Period.between(convertToLocalDateViaSqlDate(createdDate), LocalDate.now());
        if (Math.abs(period.getMonths()) <= 3) {
            periodIssue = newBook;
        }
        final Collection<Book> books = bookService.findBookByNameAndIsbn(book.getName(), book.getIsbn());
        if (books.size() < limitCount) {
            periodIssue = minimum;
        }

        Calendar instance = Calendar.getInstance();
        instance.setTime(createdDate); //устанавливаем дату, с которой будет производить операции
        instance.add(Calendar.DAY_OF_MONTH, periodIssue);// прибавляем 3 дня к установленной дате
        Date timeMustReturn = (Date) instance.getTime(); // получаем измененную дату

        issued.setBook(book);
        issued.setReader(reader);
        issued.setTimeIssue(createdDate);
        issued.setTimeMustReturn(timeMustReturn);

        history.setBook(book);
        history.setReader(reader);
        history.setTimeIssue(createdDate);
        history.setIssued(issued);

        issuedService.create(issued);
        historyService.create(history);

        return issued;
    }

    @Transactional
    public History takeBookBack(@NotNull Long issuedId) {
        final Issued issued = issuedService.searchOne(issuedId);
        final History history = historyService.findByIssued(issued);
        // выясняем опазадал ли наш читатель со сроком
        // если да то заносим его в список должников
        // в любом случае удаляем из таблицы книги на руках и вносим информацию в запись история
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        if (issued.getTimeMustReturn().getTime() < date.getTime()) {
            final ReaderExpired readerExpired = new ReaderExpired();
            readerExpired.setBook(issued.getBook());
            readerExpired.setReader(issued.getReader());
            readerExpired.setTimeIssue(issued.getTimeIssue());
            readerExpired.setTimeReturn(date);
            long diff = issued.getTimeIssue().getTime() - date.getTime();
            readerExpired.setExpired((int) (diff / (24 * 60 * 60 * 1000)));
            expiredService.create(readerExpired);
        }
        issuedService.delete(issued.getId());
        history.setTimeReturn(date);
        historyService.update(history.getId(), history);
        return history;
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
