package com.example.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Person {
    
    @Id
    @GeneratedValue
    private long id;


    private String kode;
    private String gejala;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // public String getNo() {
    //     return this.no;
    // }

    // public void setNo(String no) {
    //     this.no = no;
    // }

    public String getKode() {
        return this.kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getGejala() {
        return this.gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

}