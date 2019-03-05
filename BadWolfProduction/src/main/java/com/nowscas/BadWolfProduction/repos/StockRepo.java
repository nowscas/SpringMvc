package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.Stock;
import org.springframework.data.repository.CrudRepository;

/**
 * Интерфейс для работы с записями акций в БД.
 */
public interface StockRepo extends CrudRepository<Stock, Long> {
}
