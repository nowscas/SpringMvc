package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.PricePost;
import com.nowscas.BadWolfProduction.repos.PricePostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PriceService {
    @Autowired
    private PricePostRepo pricePostRepo;
    @Autowired
    private IterableService iterableService;
    @Autowired
    private StringRedactor fileNameRedactor;

    @Value("${upload.priceExamplePath}")
    private String uploadPath;

    /**
     * Метод возвращает все записи цен.
     * @return
     */
    public Iterable<PricePost> getPricePosts() {
        Iterable<PricePost> pricePosts = pricePostRepo.findAll();
        return iterableService.revertList((List)pricePosts);
    }

    /**
     * Метод сохнаряет новую запись цены.
     * @param file
     * @param genre
     * @param price
     * @return
     * @throws IOException
     */
    public boolean addPricePost(MultipartFile file, String genre, String price) throws IOException {
        PricePost pricePost = new PricePost(genre, price);
        if (file.getSize() != 0 && !file.getOriginalFilename().isEmpty()) {
            if (!file.getContentType().contains("audio")) {
                return false;
            }

            String filename = fileNameRedactor.replaceChar(file.getOriginalFilename(), " ", "_");
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + filename;

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            pricePost.setFilename(resultFilename);
            pricePostRepo.save(pricePost);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод удаляет запись цены и файл(если он был).
     * @param pricePost
     */
    public void deletePrice(PricePost pricePost) {
        File file = new File(uploadPath + "/" + pricePost.getFilename());
        pricePostRepo.delete(pricePost);
        file.delete();
    }
}
