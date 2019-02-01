package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.MainPagePost;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс для работы с записями постов в БД.
 */
public interface MainPagePostRepo extends CrudRepository<MainPagePost, Long> {
}
