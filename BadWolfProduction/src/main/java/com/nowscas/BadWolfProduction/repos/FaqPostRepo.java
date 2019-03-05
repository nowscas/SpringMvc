package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.FaqPost;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс для работы с FAQ записями в БД.
 */
public interface FaqPostRepo extends CrudRepository<FaqPost, Long> {
}
