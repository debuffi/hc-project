package com.example.hc.repository.mastery;

import java.util.List;

import com.example.hc.domain.entity.MasteryRuneConfig;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
public interface MasteryRuneConfigRepository extends CrudRepository<MasteryRuneConfig, Long> {
    List<MasteryRuneConfig> findAll();
}
