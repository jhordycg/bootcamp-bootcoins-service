package com.bootcamp.bootcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Bootcoin")
public class Bootcoin {

    private @Id String id;
    private String mail;
    private String documentType;
    private String documentNumber;
    private String phone;

}
