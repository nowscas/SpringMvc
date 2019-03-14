package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.Stock;
import com.nowscas.BadWolfProduction.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Класс отвечает за работу с акциями.
 */
@Controller
public class StockController {
    @Autowired
    private StockService stockService;

    /**
     * Метод возвращает страницу добавления акции.
     */
    @GetMapping("/addNewStock")
    public String getNewStockPage() {
        return "addNewStock";
    }

    /**
     * Метод дает команду на сохранение новой акции и возвращает главную странцу.
     */
    @PostMapping("/addNewStock")
    public String addStock(
            @RequestParam String stockHeader,
            @RequestParam String text
    ) {
        stockService.addStock(stockHeader, text);
        return "redirect:/";
    }

    /**
     * Метод дает команду на удаление акции и возвращает главную страницу.
     */
    @GetMapping("/deleteStock/{stock}")
    public String deleteStock(
            @PathVariable Stock stock
    ) {
        stockService.deleteStock(stock);
        return "redirect:/";
    }
}
