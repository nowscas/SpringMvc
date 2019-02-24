package com.nowscas.BadWolfProduction.repos;

import com.nowscas.BadWolfProduction.domain.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepo extends CrudRepository<Stock, Long> {
}
