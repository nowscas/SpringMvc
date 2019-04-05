package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Класс отвечает за работу с постами.
 */
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    // Пока убрал страницу новостей
//    /**
//     * Метод получает записи новостей и возвращает страницу со всеми новостями.
//     * @return
//     */
//    @GetMapping("/news")
//    public String getAllPosts(Model model) {
//        model.addAttribute("posts", postService.getMainPagePosts());
//        return "allNews";
//    }

    /**
     * Метод возвращает страницу добавления новой новости.
     * @return
     */
    @GetMapping("/addNewPost")
    public String getNewPostPage(){
        return "addNewPost";
    }

    /**
     * Метод дает команду на сохранение новости в БД и возвращает главную страницу.
     * @param user
     * @param description
     * @param text
     * @param model
     * @return
     */
    @PostMapping("/addNewPost")
    public String addPost(
            @AuthenticationPrincipal User user,
            @RequestParam String description,
            @RequestParam String text, Map<String, Object> model,
            @RequestParam String youtubeLink,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (postService.addMainPagePost(description, text, user, youtubeLink, file)) {
            return "redirect:/";
        } else {
            model.put("message", "Выбран не подходящий файл!");
            return "addNewPost";
        }

    }

    /**
     * Метод возвращает страницу редактирования указанного поста.
     * @param mainPagePost
     * @param model
     * @return
     */
    @GetMapping("/editPost/{mainPagePost}")
    public String getPostEditPage(@PathVariable MainPagePost mainPagePost, Model model) {
        model.addAttribute("post", mainPagePost);
        return "postEdit";
    }

    /**
     * Метод дает команду на сохранение отредактированной записи и возвращает главную страницу.
     * @param postHeader
     * @param postBody
     * @param post
     * @return
     */
    @PostMapping("/changePost")
    public String postSave(
            @RequestParam String postHeader,
            @RequestParam String postBody,
            @RequestParam(required = false) String youtubeLink,
            @RequestParam("id") MainPagePost post
    ) {
        postService.saveChanged(postHeader, postBody, youtubeLink, post);
        return "redirect:/";
    }

    /**
     * Метод дает команду на удаление переданной новости и возвращает страницу новостей.
     * @param mainPagePost
     * @param model
     * @return
     */
    @GetMapping("/deletePost/{mainPagePost}")
    public String deleteTrack(
            @PathVariable MainPagePost mainPagePost,
            Model model
    ) {
        postService.deletePost(mainPagePost);
        return "redirect:/";
    }
}
