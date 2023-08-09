package com.example.hc.service.restorer;

import java.util.List;
import java.util.Objects;

import com.example.hc.domain.model.Account;
import com.example.hc.domain.model.Character;
import com.example.hc.service.CharacterService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.02.2023
 */
@Service
@RequiredArgsConstructor
public class Restorer {

    private final JdbcTemplate intJdbcTemplate;
    private final JdbcTemplate mainJdbcTemplate;
    private final CharacterService characterService;

    public void restoreChars() {
        List<Character> characters = characterService.getCharacters();

        for (Character character : characters) {
            final Integer charId = character.getCharIdx();
            System.out.println("START ============ " + charId);
            try {
                byte[] invBytes = mainJdbcTemplate.queryForObject(
                        "SELECT Data FROM Server01.dbo.cabal_Inventory_table WHERE CharacterIdx=" + charId,
                        (rs, rowNum) -> rs.getBytes(1));
                intJdbcTemplate.update("UPDATE Server01.dbo.cabal_Inventory_table SET Data = ? WHERE CharacterIdx=" + charId, invBytes);

            } catch (Exception e) {
                System.out.println("INV " + charId);
            }
            try {
                byte[] eq = mainJdbcTemplate.queryForObject(
                        "SELECT Data FROM Server01.dbo.cabal_equipment_table WHERE CharacterIdx=" + charId,
                        (rs, rowNum) -> rs.getBytes(1));
                intJdbcTemplate.update("UPDATE Server01.dbo.cabal_equipment_table SET Data = ? WHERE CharacterIdx=" + charId, eq);

            } catch (Exception e) {
                System.out.println("EQ " + charId);
            }
            System.out.println("END ============ " + charId);
        }
    }

    public void restoreAccs() {
        List<Account> accounts = characterService.getAccounts();

        for (Account account : accounts) {
            Integer userNum = account.getUserNum();
            System.out.println("START ============ " + userNum);
            try {
                byte[] ext = mainJdbcTemplate.queryForObject(
                        "SELECT Data FROM Server01.dbo.cabal_warehouse_table WHERE UserNum=" + userNum,
                        (rs, rowNum) -> rs.getBytes(1));
                intJdbcTemplate.update("UPDATE Server01.dbo.cabal_warehouse_table SET Data = ? WHERE UserNum=" + userNum, ext);
            } catch (Exception e) {
                System.out.println("EXT " + userNum);
            }
            try {
                byte[] ext1 = mainJdbcTemplate.queryForObject(
                        "SELECT Data FROM Server01.dbo.cabal_warehouse_ext_table WHERE UserNum=" + userNum,
                        (rs, rowNum) -> rs.getBytes(1));
                intJdbcTemplate.update("UPDATE Server01.dbo.cabal_warehouse_ext_table SET Data = ? WHERE UserNum=" + userNum, ext1);
            } catch (Exception e) {
                System.out.println("EXT1 " + userNum);
            }

            System.out.println("END ============ " + userNum);
        }
    }

    public void restoreSkills() {
        List<Character> characters = characterService.getCharacters();

        for (Character character : characters) {
            final Integer charId = character.getCharIdx();
            System.out.println("START ============ " + charId);
            try {
                final Character mainCharacter = characterService.getMainCharacter(charId);
                if (mainCharacter == null || !Objects.equals(mainCharacter.getClassId(), character.getClassId())) {
                    continue;
                }
                byte[] skillBytes = mainJdbcTemplate.queryForObject(
                        "SELECT Data FROM Server01.dbo.cabal_skilllist_table WHERE CharacterIdx=" + charId,
                        (rs, rowNum) -> rs.getBytes(1));
                if (skillBytes.length == 0) continue;
                intJdbcTemplate.update("UPDATE Server01.dbo.cabal_skilllist_table SET Data = ? WHERE CharacterIdx=" + charId, skillBytes);
            } catch (Exception e) {
                System.out.println("INV " + charId);
            }
            System.out.println("END ============ " + charId);
        }
    }

}
