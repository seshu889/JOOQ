package com.springhbootjooq.springbootjooq;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements  bookDao {

    @Autowired
    DSLContext dsl;
}
