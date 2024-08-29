package br.xksoberbado.multitenancymongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.UUID;

@Getter
@Setter
@Document
public class Person {

    @Id
    @Field(targetType = FieldType.STRING)
    private UUID id;

    private String name;

    private Integer age;
}
