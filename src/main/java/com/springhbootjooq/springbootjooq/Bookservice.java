package com.springhbootjooq.springbootjooq;

import java.util.List;

public interface Bookservice {


    List<Book> getbooks();

    void insertbook(Book book);
}
