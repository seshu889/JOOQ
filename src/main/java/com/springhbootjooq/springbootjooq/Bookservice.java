package com.springhbootjooq.springbootjooq;

import com.springhbootjooq.springbootjooq.tables.pojos.UsCities;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface Bookservice {
    List<UsCities> getlocations();

    List<UsCities> findCitiesdDistanceFromUser(Double userlat, Double userlan);

    HashMap<String, Object> uploadExceldata(MultipartFile file);


    /*List<Book> getbooks();

    void insertbook(Book book);*/
}
