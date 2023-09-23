package com.example.hc.repository.mastery;

import com.example.hc.domain.entity.MasteryUserPoints;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
public interface MasteryUserPointsRepository extends CrudRepository<MasteryUserPoints, Long> {

    @Query("SELECT t FROM MasteryUserPoints t WHERE t.charId=:charId")
    MasteryUserPoints findPointsByCharId(Long charId);

    @Modifying
    @Query("UPDATE MasteryUserPoints SET points = points + 1 WHERE charId=:charId")
    void incPointsByCharId(Long charId);

    @Modifying
    @Query("UPDATE MasteryUserPoints SET points=:points WHERE charId=:charId")
    void updatePointsByCharId(Long charId, Long points);
}
