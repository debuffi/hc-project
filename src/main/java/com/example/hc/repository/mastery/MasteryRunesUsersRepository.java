package com.example.hc.repository.mastery;

import java.util.List;

import com.example.hc.domain.entity.MasteryRunesUsers;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
public interface MasteryRunesUsersRepository extends CrudRepository<MasteryRunesUsers, MasteryRunesUsers.MasteryRunesUsersId> {

    @Query(value = "SELECT t.id.masteryRuneConfigId FROM MasteryRunesUsers t WHERE t.id.charId=:charId")
    List<Long> findRuneConfigIdsByCharId(Long charId);

    @Query(value = "SELECT t.id.masteryRuneConfigId FROM MasteryRunesUsers t WHERE t.id.charId=:charId AND t.masteryRuneConfig.masteryRune.forceId=:forceId")
    Long findRuneConfigIdsByCharIdAndForceId(Long charId, Long forceId);

    @Modifying
    @Query("UPDATE MasteryRunesUsers" +
    " SET id.masteryRuneConfigId=(SELECT mrc.id FROM MasteryRuneConfig mrc JOIN MasteryRune mr ON mrc.masteryRuneId = mr.id WHERE mr.forceId=:forceId AND mrc.lvl=:newLvl)" +
        "  WHERE id.masteryRuneConfigId=:currentConfigId AND id.charId=:charId")
    void updateForceLvl(Long charId, Long forceId, Long currentConfigId, int newLvl);

}
