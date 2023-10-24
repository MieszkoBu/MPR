package com.example.Lab2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String marka;
    String model;
    int rok_produkcji;

    public Car(String marka,String model, int rok_produkcji) {
        this.marka = marka;
        this.model=model;
        this.rok_produkcji=rok_produkcji;
    }

    public Car() {

    }

    public long getId() {
        return id;
    }

    public int getRok_produkcji() {
        return rok_produkcji;
    }

    public String getModel() {
        return model;
    }

    public String getMarka() {
        return marka;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRok_produkcji(int rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

}
