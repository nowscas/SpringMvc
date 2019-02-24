package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.Stock;
import com.nowscas.BadWolfProduction.repos.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Класс отвечает за работу с акциями.
 */
@Controller
public class StockController {
    @Autowired
    private StockRepo stockRepo;

    /**
     * Метод возвращает страницу добавления акции.
     */
    @GetMapping("/addNewStock")
    public String getNewStockPage() {
        return "addNewStock";
    }

    /**
     * Метод сохраняет новую акцию и возвращает главную странцу.
     */
    @PostMapping("/addNewStock")
    public String addStock(
//   ???         @AuthenticationPrincipal User user,
            @RequestParam String stockHeader,
            @RequestParam String text
    ) {
        Stock stock = new Stock(stockHeader, text);
        stockRepo.save(stock);
        return "redirect:/";
    }
}
