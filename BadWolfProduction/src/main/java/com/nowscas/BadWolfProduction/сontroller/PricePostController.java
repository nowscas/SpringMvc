package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.PricePost;
import com.nowscas.BadWolfProduction.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Класс отвечает за работу с постами цен.
 */
@Controller
@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
public class PricePostController {
    @Autowired
    PriceService priceService;

    /**
     * Метод получает записи цен и возвращает страницу со всеми ценами.
     * @param model
     * @return
     */
    @GetMapping("/prices")
    public String getFaqPage(Model model) {
        model.addAttribute("pricePosts", priceService.getPricePosts());
        return "prices";
    }

    /**
     * Метод возвращает страницу добавления новой записи цен.
     * @return
     */
    @GetMapping("/addPricePost")
    public String getAddPage() {
        return "addNewPrice";
    }

    /**
     * Метод дает команду на сохранение цены в БД и возвращает страницу цен.
     * @return
     */
    @PostMapping("/addPrice")
    public String addPricePost(
            @RequestParam("file") MultipartFile file,
            @RequestParam String genre,
            @RequestParam String price, Map<String, Object> model
    ) throws IOException {
        if (priceService.addPricePost(file, genre, price)) {
            return "redirect:/prices";
        } else {
            model.put("message", "Выбран не подходящий файл!");
            return "addPricePost";
        }
    }

    /**
     * Метод дает команду на удаление переданной цены и возвращает страницу цен.
     */
    @GetMapping("/deletePrice/{pricePost}")
    public String deletePrice(
            @PathVariable PricePost pricePost,
            Map<String, Object> model
    ) {
        priceService.deletePrice(pricePost);
        return "redirect:/prices";
    }
}
