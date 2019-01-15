package com.nowscas.BadWolfProduction.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Класс для операций с изображениями.
 */
@Service
public class ImageRedactor {

    /**
     * Метод возвращает изображение указанного размера.
     * @param bytes
     * @param height
     * @param width
     * @return
     * @throws IOException
     */
    public BufferedImage resizeImage(byte[] bytes, int height, int width) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        BufferedImage bufferedImage = ImageIO.read(bis);
        return resize(bufferedImage, height, width);
    }

    /**
     * Метод изменяет размер изображения.
     * @param img
     * @param height
     * @param width
     * @return
     */
    private BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
