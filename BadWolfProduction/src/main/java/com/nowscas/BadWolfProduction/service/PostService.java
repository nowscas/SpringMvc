package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.repos.MainPagePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private MainPagePostRepo mainPagePostRepo;
    @Autowired
    private StringRedactor fileNameRedactor;
    @Autowired
    private ImageRedactor imageRedactor;
    @Autowired
    private IterableService iterableService;

    @Value("${upload.postImagePath}")
    private String uploadPath;

    /**
     * Метод возвращает все записи новостей.
     * @return
     */
    public Iterable<MainPagePost> getMainPagePosts(){
        Iterable<MainPagePost> posts = mainPagePostRepo.findAll();
        return iterableService.revertList((List)posts);
    }

    /**
     * Метод сохнаряет новую запись новости.
     * @param description
     * @param text
     * @param user
     * @param youtubeLink
     * @param file
     * @return
     * @throws IOException
     */
    public boolean addMainPagePost(String description, String text, User user, String  youtubeLink, MultipartFile file) throws IOException {
        MainPagePost mainPagePost = new MainPagePost(description, text, user);

        if (!youtubeLink.equals("")) {
            mainPagePost.setYoutubeLink(youtubeLink);
        }
        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {
            if (!file.getContentType().contains("image")) {
                return false;
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
                return false;
            }
            mainPagePost.setFilename(resultFilename);
        }
        mainPagePostRepo.save(mainPagePost);
        return true;
    }

    /**
     * Метод сохраняет изменения в записи новости.
     * @param postHeader
     * @param postBody
     * @param youtubeLink
     * @param post
     */
    public void saveChanged(String postHeader, String postBody, String youtubeLink, MainPagePost post) {
        post.setPostHeader(postHeader);
        post.setPostBody(postBody);
        post.setYoutubeLink(youtubeLink);

        mainPagePostRepo.save(post);
    }

    /**
     * Метод удаляет запись новости и файл(если он был).
     * @param mainPagePost
     */
    public void deletePost(MainPagePost mainPagePost) {
        File file = new File(uploadPath + "/" + mainPagePost.getFilename());
        mainPagePostRepo.delete(mainPagePost);
        file.delete();
    }
}
