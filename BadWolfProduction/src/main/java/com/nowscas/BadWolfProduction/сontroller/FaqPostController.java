package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.FaqPost;
import com.nowscas.BadWolfProduction.repos.FaqPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private FaqPostRepo faqPostRepo;

    /**
     * Метод возвращает страницу FAQ.
     * @param model
     * @return
     */
    @GetMapping("/faq")
    public String getFaqPage(Model model) {

        Iterable<FaqPost> faqPosts;
        faqPosts = faqPostRepo.findAll();
        model.addAttribute("faqPosts", faqPosts);
        return "FAQ";
    }

    /**
     * Метод возвращает страницу добавления нового FAQ.
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addFaqPost")
    public String getAddPage() {
        return "addNewFaq";
    }

    /**
     * Метод сохраняет новое FAQ.
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addNewFaq")
    public String addFaqQuestion(
            @RequestParam String question,
            @RequestParam String answer
    ) {
        FaqPost faqPost = new FaqPost(question, answer);
        faqPostRepo.save(faqPost);
        return "redirect:/faq";
    }

    /**
     * Метод удаляет переданное FAQ.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteFaq/{faqPost}")
    public String deleteFaq(@PathVariable FaqPost faqPost) {
        faqPostRepo.delete(faqPost);
        return "redirect:/faq";
    }
}
