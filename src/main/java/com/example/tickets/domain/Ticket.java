package com.example.tickets.domain;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String price;
    private String date;
    private Integer client_id;

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Ticket(String price, String date) {
    }
}
