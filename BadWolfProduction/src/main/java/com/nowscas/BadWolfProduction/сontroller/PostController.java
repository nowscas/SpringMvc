package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.repos.MainPagePostRepo;
import com.nowscas.BadWolfProduction.service.ImageRedactor;
import com.nowscas.BadWolfProduction.service.StringRedactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Класс отвечает за работу с постами.
 */
@Controller
public class PostController {
    @Autowired
    private MainPagePostRepo mainPagePostRepo;
    @Autowired
    private ImageRedactor imageRedactor;
    @Autowired
    private StringRedactor fileNameRedactor;

    @Value("${upload.postImagePath}")
    private String uploadPath;

    /**
     * Метод возвращает страницу добавления нового поста.
     * @return
     */
    @GetMapping("/addNewPost")
    public String getNewPostTemplate(){
        return "addNewPost";
    }

    /**
     * Метод сохраняет новый пост в БД и возвращает главную страницу.
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
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        MainPagePost mainPagePost = new MainPagePost(description, text, user);

        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {
            if (!file.getContentType().contains("image")) {
                model.put("message", "Выбран не подходящий файл!");
                return "addNewPost";
            }

            String filename = fileNameRedactor.replaceChar(file.getOriginalFilename(), " ", "_");
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + filename;

            try {
                File output = new File(uploadPath +  "/" + resultFilename);
                ImageIO.write(imageRedactor.resizeImage(file.getBytes(), 400, 500), "png", output);
            }
            catch (NullPointerException e) {
                model.put("message", "Не подходящий формат изображения!");
                return "addNewPost";
            }
            mainPagePost.setFilename(resultFilename);
        }
        mainPagePostRepo.save(mainPagePost);

        Iterable<MainPagePost> posts = mainPagePostRepo.findAll();
        model.put("posts", posts);
        return "redirect:/";
    }

    /**
     * Метод возвращает страницу со всеми постами.
     * @return
     */
    @GetMapping("/allPosts")
    public String getAllPosts(Model model) {
        Iterable<MainPagePost> posts;
        posts = mainPagePostRepo.findAll();
        model.addAttribute("posts", posts);
        return "allNews";
    }

    /**
     * Метод возвращает страницу редактирования указанного поста.
     * @param mainPagePost
     * @param model
     * @return
     */
    @GetMapping("/editPost/{mainPagePost}")
    public String getPostEditPage(
            @PathVariable MainPagePost mainPagePost,
            Model model
    ) {
        model.addAttribute("post", mainPagePost);
        return "postEdit";
    }

    /**
     * Метод сохраняет отредактированную запись
     * @param postHeader
     * @param postBody
     * @param post
     * @return
     */
    @PostMapping("/changePost")
    public String postSave(
            @RequestParam String postHeader,
            @RequestParam String postBody,
            @RequestParam("id") MainPagePost post
    ) {
        post.setPostHeader(postHeader);
        post.setPostBody(postBody);

        mainPagePostRepo.save(post);
        return "redirect:/";
    }

    /**
     * Метод удаляет новость.
     * @param mainPagePost
     * @param model
     * @return
     */
    @GetMapping("/deletePost/{mainPagePost}")
    public String deleteTrack(
            @PathVariable MainPagePost mainPagePost,
            Model model
    ) {
        File file = new File(uploadPath + "/" + mainPagePost.getFilename());
        if (file.delete()) {
            mainPagePostRepo.delete(mainPagePost);
            return "redirect:/allPosts";
        }
        else {
            Iterable<MainPagePost> posts;
            posts = mainPagePostRepo.findAll();
            model.addAttribute("posts", posts);
            model.addAttribute("message", "Удаляемый файл не найден");
            return "allNews";
        }
    }
}
