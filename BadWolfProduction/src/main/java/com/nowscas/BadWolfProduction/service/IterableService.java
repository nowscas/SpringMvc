package com.nowscas.BadWolfProduction.service;

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
     * Метод переворачивает коллекцию для вывода на экран записей в порядке их добавления.
     */
    public List revertList (List list) {
        List  result = new ArrayList<>();
        for (ListIterator iterator = list.listIterator(list.size()); iterator.hasPrevious();) {
            result.add(iterator.previous());
        }
        return result;
    }
}
