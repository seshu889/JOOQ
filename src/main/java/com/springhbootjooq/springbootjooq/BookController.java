package com.springhbootjooq.springbootjooq;

import com.springhbootjooq.springbootjooq.tables.pojos.UsCities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    Bookservice bookservice;

/*@GetMapping("/books")
public List<Book> getbooks(){
    return this.bookservice.getbooks();
}

@PostMapping("/books")
public void savebooks(@RequestBody Book book)
{
     this.bookservice.insertbook(book);
}*/
    @GetMapping("/locations")
   public List<UsCities>getlocations()
   {

return bookservice.getlocations();
   }
@GetMapping("/id")
   public List<UsCities>findAllCitiesdDistanceFromUser(@RequestParam Double userlat,@RequestParam Double userlan)
   {
       return bookservice.findCitiesdDistanceFromUser(userlat,userlan);
   }
    @PostMapping("/uploadExceldata")
    public HashMap<String,Object> uploadExceldata(@RequestParam("file") MultipartFile file)
    {
        return bookservice.uploadExceldata(file);
    }
}
