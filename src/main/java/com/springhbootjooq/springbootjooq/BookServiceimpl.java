package com.springhbootjooq.springbootjooq;




import com.springhbootjooq.springbootjooq.tables.pojos.UsCities;
import com.springhbootjooq.springbootjooq.tables.records.UsCitiesRecord;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.jooq.DSLContext;

import org.jooq.Result;
import org.jooq.impl.DSL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.springhbootjooq.springbootjooq.Tables.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceimpl implements Bookservice {

    @Autowired
    DSLContext dsl;



    @Override
    public List<UsCities> getlocations() {
        List<UsCities>cities;
        Result<UsCitiesRecord> fetch= dsl.selectFrom(US_CITIES).fetch();
        if (fetch != null) {
            ModelMapper modelMapper = new ModelMapper();
            cities = fetch.stream().map(UsCitiesRecord -> {
                return modelMapper.map(UsCitiesRecord, UsCities.class);
            }).collect(Collectors.toList());
        } else {
            cities = new ArrayList<>();
        }
        return cities;
    }

    @Override
    public List<UsCities> findCitiesdDistanceFromUser(Double userlat, Double userlan) {


        return null;
    }

    @Override
    public HashMap<String, Object> uploadExceldata(MultipartFile file) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        List<UsCitiesCust> cities = new ArrayList<>();
        Workbook workbook;

        File rootFolder = new File("files");
        if(!rootFolder.exists()){
            rootFolder.mkdir();
        }
        try {
            byte[]  bytes = file.getBytes();
            Path path = Paths.get(rootFolder.getAbsolutePath() + "/" + file.getOriginalFilename());
            Files.write(path,bytes);

            System.out.println(""+ path);
            System.out.println(""+ file.getOriginalFilename());

        } catch (IOException e) {
           e.printStackTrace();
        }

        try {
           workbook= WorkbookFactory.create(new File(rootFolder.getAbsolutePath() + "/" + file.getOriginalFilename()));
           DataFormatter dataFormatter = new DataFormatter();
           Sheet sheet=workbook.getSheetAt(0);
            System.out.println(sheet.getSheetName());
            for(Row row:sheet)
            {
               if(row.getRowNum()==0)
               {
               }
               else
               {
                   UsCitiesCust usCities = new UsCitiesCust();

                   for(Cell cell:row)
                   {
                       String cellValue=dataFormatter.formatCellValue(cell);
                       System.out.println(cellValue);

                       switch(cell.getColumnIndex())
                       {
                           case 0:
                           if(!cellValue.isEmpty())
                           {
                              // usCities.setId(Integer.parseInt(cellValue));
                             //  System.out.println("case 0:"+ id);
                           }
                           break;
                           case 1:
                               if(!cellValue.isEmpty())
                               {
                                   usCities.setIdState(Integer.parseInt(cellValue));
                               }
                               break;
                           case 2:
                               if(!cellValue.isEmpty())
                               {
                                   usCities.setCity(cellValue);
                               }
                               break;
                           case 3:
                               if(!cellValue.isEmpty())
                               {
                                   usCities.setCounty(cellValue);
                               }
                               break;
                           case 4:
                               if(!cellValue.isEmpty())
                               {
                                   usCities.setLatitude(Double.parseDouble(cellValue));
                               }
                               break;
                           case 5:
                               if(!cellValue.isEmpty())
                               {
                                   usCities.setLongitude(Double.parseDouble(cellValue));
                               }
                               break;
                       }
                   }

                   cities.add(usCities);
               }
            }
            workbook.close();
        }catch (EncryptedDocumentException | InvalidFormatException | IOException  e) {
        e.printStackTrace();
        }
        for (UsCitiesCust usCities:
             cities) {
             dsl.insertInto(US_CITIES,US_CITIES.ID_STATE,US_CITIES.CITY,
                    US_CITIES.COUNTY,US_CITIES.LATITUDE,US_CITIES.LONGITUDE).
                    values(usCities.getIdState(), usCities.getCity(), usCities.getCounty(),
                            usCities.getLatitude(), usCities.getLongitude()).
                    execute();
        }

        response.put("status","");
         return response;
    }


/*    @Override
    public List<Book> getbooks() {
        return dsl.selectFrom(Tables.BOOK).fetchInto(Book.class);

    }

    @Override
    public void insertbook(Book book) {
        dsl.insertInto(Tables.BOOK,Tables.BOOK.TITLE,Tables.BOOK.AUTHOR).
                values(book.getTitle(), book.getAuthor()).execute();
    }*/

  /*  @Override
    public String updateuser(User user) {
        dsl.update(Tables.USER)
                .set(Tables.USER.FIRST_NAME,user.getFirst_name())
                .set(Tables.USER.LAST_NAME,user.getLast_name())
                .where(Tables.USER.ID.eq(user.getId()))
                .execute();
        return "record updated";
    }*/

  /*  List<User> userdetails = dsl.select(Tables.USER.ID, Tables.USER.FIRST_NAME, Tables.USER.LAST_NAME, Tables.USER.PHONE_NUM)
            .from(Tables.USER)
            .where(Tables.USER.USERNAME.eq(username))
            .fetchInto(User.class)*/


}
