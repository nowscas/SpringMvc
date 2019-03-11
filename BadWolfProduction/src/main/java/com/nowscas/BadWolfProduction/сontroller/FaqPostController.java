package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.FaqPost;
import com.nowscas.BadWolfProduction.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Класс отвечает за работу с постами FAQ.
 */
@Controller
public class FaqPostController {

    @Autowired
    private FaqService faqService;

    /**
     * Метод получает записи FAQ и возвращает страницу FAQ.
     * @param model
     * @return
     */
    @GetMapping("/faq")
    public String getFaqPage(Model model) {
        model.addAttribute("faqPosts", faqService.getFaqPosts());
        return "FAQ";
    }

    /**
     * Метод возвращает страницу добавления нового FAQ.
     * @return
     */
    @GetMapping("/addFaqPost")
    public String getAddPage() {
        return "addNewFaq";
    }

    /**
     * Метод дает команду на сохранение нового FAQ и возвращает страницу FAQ.
     * @return
     */
    @PostMapping("/addNewFaq")
    public String addFaqQuestion(@RequestParam String question, @RequestParam String answer) {
        faqService.addFaqQuestion(question, answer);
        return "redirect:/faq";
    }

    /**
     * Метод дает команду на удаление переданного FAQ и возвращает страницу FAQ.
     */
    @GetMapping("/deleteFaq/{faqPost}")
    public String deleteFaq(@PathVariable FaqPost faqPost) {
        faqService.deleteFaq(faqPost);
        return "redirect:/faq";
    }
}
