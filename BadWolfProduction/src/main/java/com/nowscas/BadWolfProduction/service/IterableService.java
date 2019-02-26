package com.nowscas.BadWolfProduction.service;

import com.nowscas.BadWolfProduction.domain.AudioTrack;
import com.nowscas.BadWolfProduction.domain.MainPagePost;
import com.nowscas.BadWolfProduction.domain.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Класс для работы с Iterable коллекциями
 */
@Service
public class IterableService {

    /**
     * Метод переворачивает коллекцию новостей для вывода на экран записей в порядке их добавления.
     */
    public List<MainPagePost> revertPosts (List<MainPagePost> posts) {
        List<MainPagePost> result = new ArrayList<MainPagePost>();
        for (ListIterator iterator = posts.listIterator(posts.size()); iterator.hasPrevious();) {
            result.add((MainPagePost) iterator.previous());
        }
        return result;
    }

    /**
     * Метод переворачивает коллекцию аудиозаписей для вывода на экран записей в порядке их добавления.
     */
    public List<AudioTrack> revertTracks (List<AudioTrack> posts) {
        List<AudioTrack> result = new ArrayList<AudioTrack>();
        for (ListIterator iterator = posts.listIterator(posts.size()); iterator.hasPrevious();) {
            result.add((AudioTrack) iterator.previous());
        }
        return result;
    }

    /**
     * Метод переворчивает коллекцию скидок для вывода на экран записей в порядке их добавления.
     */
    public List<Stock> revertStocks (List<Stock> stocks) {
        List<Stock> result = new ArrayList<Stock>();
        for (ListIterator iterator = stocks.listIterator(stocks.size()); iterator.hasPrevious(); ) {
            result.add((Stock) iterator.previous());
        }
        return result;
    }
}
