package com.example.springbootswagger2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private int id;
    @ApiModelProperty
    private String name;
    @ApiModelProperty(example = "F")
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.sql.Date", example = "1970-01-01")
    private Date birth;
    @ApiModelProperty(readOnly = true)
    private int breed_id;


    public pets() {
    }

    public pets(String name, String sex, Date birth, int breed_id) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.breed_id = breed_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getBreed_id() { return breed_id; }

    public void setBreed_id(int breed_id) { this.breed_id = breed_id; }

    @Override
    public String toString() {
        return "pets{" +
                "id_pet=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                '}';
    }

}
