package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.Role;
import com.nowscas.BadWolfProduction.domain.User;
import com.nowscas.BadWolfProduction.service.ImageRedactor;
import com.nowscas.BadWolfProduction.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImageRedactor imageRedactor;

    @Value("${upload.imagePath}")
    private String uploadPath;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("file") MultipartFile file,
            User user, Map<String, Object> model) throws IOException {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {

            if (!file.getContentType().contains("image")) {
                model.put("message", "Выбран не подходящий файл!");
                return "registration";
            }
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            try {
                File output = new File(uploadPath +  "/" + resultFilename);
                ImageIO.write(imageRedactor.resizeImage(file.getBytes(), 40, 40), "png", output);
            }
            catch (NullPointerException e) {
                model.put("message", "Не подходящий формат изображения!");
                return "registration";
            }
            user.setFilename(resultFilename);
        }
        else {
            user.setFilename("defaultImage.jpg");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
