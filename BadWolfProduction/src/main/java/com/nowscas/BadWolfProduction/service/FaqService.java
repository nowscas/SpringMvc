package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.FaqPost;
import com.nowscas.BadWolfProduction.repos.FaqPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqService {
    @Autowired
    private FaqPostRepo faqPostRepo;

    /**
     * Метод возвращает записи FAQ.
     * @return
     */
    public Iterable<FaqPost> getFaqPosts(){
        Iterable<FaqPost> faqPosts;
        faqPosts = faqPostRepo.findAll();
        return faqPosts;
    }

    /**
     * Метод сохраняет новое FAQ.
     * @return
     */
    public void addFaqQuestion(String question, String answer) {
        FaqPost faqPost = new FaqPost(question, answer);
        faqPostRepo.save(faqPost);
    }

    /**
     * Метод удаляет переданное FAQ.
     */
    public void deleteFaq(FaqPost faqPost) {
        faqPostRepo.delete(faqPost);
    }
}
