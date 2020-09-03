package com.springhbootjooq.springbootjooq;



import org.jooq.DSLContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceimpl implements Bookservice {

    @Autowired
    DSLContext dsl;


    @Override
    public List<Book> getbooks() {
        return dsl.selectFrom(Tables.BOOK).fetchInto(Book.class);

    }

    @Override
    public void insertbook(Book book) {
        dsl.insertInto(Tables.BOOK,Tables.BOOK.TITLE,Tables.BOOK.AUTHOR).values(book.getTitle(), book.getAuthor()).execute();
    }
}
