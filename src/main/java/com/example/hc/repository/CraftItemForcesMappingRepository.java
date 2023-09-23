package com.example.hc.repository;

import com.example.hc.domain.entity.CraftInsertProperty;
import com.example.hc.domain.entity.CraftItemForcesMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vyacheslav Savinov
 * @since 22.09.2023
 */
@Repository
public interface CraftItemForcesMappingRepository extends CrudRepository<CraftItemForcesMapping, Long> {

    @Query(nativeQuery = true,
    value = "SELECT t.level FROM craft_item_forces_mapping t" +
        " INNER JOIN craft_item_mapping t2 ON t2.item_type = t.item_type " +
        "WHERE t2.item_id=:itemId AND t.force_id=:forceId")
    Integer findLevelByItemIdAndForceId(Integer itemId, Integer forceId);

}