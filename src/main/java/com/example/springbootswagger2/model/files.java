package com.example.springbootswagger2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private int id;
    private String Filename;
    private String Filetype;
    @Lob
    private byte[] img;

    public files() {
    }

    public files(String filename, String filetype, byte[] img) {
        Filename = filename;
        Filetype = filetype;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public String getFiletype() {
        return Filetype;
    }

    public void setFiletype(String filetype) {
        Filetype = filetype;
    }

    @JsonIgnore
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "files{" +
                "id=" + id +
                ", Filename='" + Filename + '\'' +
                ", Filetype='" + Filetype + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }

}