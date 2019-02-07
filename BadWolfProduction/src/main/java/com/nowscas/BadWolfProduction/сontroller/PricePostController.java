package com.nowscas.BadWolfProduction.сontroller;

import com.nowscas.BadWolfProduction.domain.PricePost;
import com.nowscas.BadWolfProduction.repos.PricePostRepo;
import com.nowscas.BadWolfProduction.service.StringRedactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Класс отвечает за работу с постами цен.
 */
@Controller
@RequestMapping(
        method = {RequestMethod.GET, RequestMethod.POST})
public class PricePostController {

    @Autowired
    private PricePostRepo pricePostRepo;
    @Autowired
    private StringRedactor fileNameRedactor;

    @Value("${upload.priceExamplePath}")
    private String uploadPath;

    @GetMapping("/prices")
    public String getFaqPage(Model model) {

        Iterable<PricePost> pricePosts;
        pricePosts = pricePostRepo.findAll();
        model.addAttribute("pricePosts", pricePosts);
        return "prices";
    }

    /**
     * Метод возвращает страницу добавления новой записи цен.
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addPricePost")
    public String getAddPage() {
        return "addNewPrice";
    }

    /**
     * Метод сохраняет новоую запись цены.
     * @return
     */
    @PostMapping("/addPrice")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addPricePost(
            @RequestParam("file") MultipartFile file,
            @RequestParam String genre,
            @RequestParam String price, Map<String, Object> model
    ) throws IOException {
        PricePost pricePost = new PricePost(genre, price);
        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {
            if (!file.getContentType().contains("audio")) {
                model.put("message", "Выбран не подходящий файл!");
                return "addPricePost";
            }

            String filename = fileNameRedactor.replaceChar(file.getOriginalFilename(), " ", "_");
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + filename;

            file.transferTo(new File(uploadPath +  "/" + resultFilename));
            pricePost.setFilename(resultFilename);
            pricePostRepo.save(pricePost);
            return "redirect:/prices";
        }
        else {
            model.put("message", "Укажите загружаемый файл!");
            return "addPricePost";
        }
    }

    /**
     * Метод удаляет переданную запись цены.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletePrice/{pricePost}")
    public String deletePrice(
            @PathVariable PricePost pricePost,
            Map<String, Object> model
    ) {
        File file = new File(uploadPath + "/" + pricePost.getFilename());
        if (file.delete()) {
            pricePostRepo.delete(pricePost);
            return "redirect:/prices";
        }
        else {
            model.put("message", "Удаляемый файл не найден");
            return "redirect:/prices";
        }
    }
}
