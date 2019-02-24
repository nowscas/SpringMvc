package com.nowscas.BadWolfProduction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Сущность записи акций.
 */
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String stockHeader;
    private String stockBody;

    public Stock() {
    }

    public Stock(String stockHeader, String stockBody) {
        this.stockHeader = stockHeader;
        this.stockBody = stockBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockHeader() {
        return stockHeader;
    }

    public void setStockHeader(String stockHeader) {
        this.stockHeader = stockHeader;
    }

    public String getStockBody() {
        return stockBody;
    }

    public void setStockBody(String stockBody) {
        this.stockBody = stockBody;
    }
}
