package com.springhbootjooq.springbootjooq;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsCitiesCust {

    private  int id;
    private int idState;
    private String  city;
    private String  county;
    private Double  latitude;
    private Double  longitude;


}
