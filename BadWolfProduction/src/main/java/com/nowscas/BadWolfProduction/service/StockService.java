package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.Stock;
import com.nowscas.BadWolfProduction.repos.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private StockRepo stockRepo;

    /**
     * Метод сохраняет нувую акцию.
     * @param stockHeader
     * @param text
     */
    public void addStock(String stockHeader, String text) {
        Stock stock = new Stock(stockHeader, text);
        stockRepo.save(stock);
    }

    /**
     * Метод удаляет акцию.
     * @param stock
     */
    public void deleteStock(Stock stock) {
        stockRepo.delete(stock);
    }
}
