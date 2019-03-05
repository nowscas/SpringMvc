package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.PricePost;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс для работы с записями цен в БД.
 */
public interface PricePostRepo extends CrudRepository<PricePost, Long> {
}
