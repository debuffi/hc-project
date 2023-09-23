package com.example.hc.repository;

import com.example.hc.domain.entity.CraftInsertProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vyacheslav Savinov
 * @since 20.09.2023
 */
@Repository
public interface CraftInsertPropertyRepository extends CrudRepository<CraftInsertProperty, Long> {
}
