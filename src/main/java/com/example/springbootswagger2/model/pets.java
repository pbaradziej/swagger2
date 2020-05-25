package com.example.springbootswagger2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    public pets() {
    }

    public pets(String name, String sex, Date birth) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
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
