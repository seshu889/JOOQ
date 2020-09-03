package com.springhbootjooq.springbootjooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    Bookservice bookservice;

@GetMapping("/books")
public List<Book> getbooks(){
    return this.bookservice.getbooks();
}

@PostMapping("/books")
public void savebooks(@RequestBody Book book)
{
     this.bookservice.insertbook(book);
}
}
