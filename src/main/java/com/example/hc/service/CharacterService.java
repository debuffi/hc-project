package com.example.hc.service;

import java.util.List;

import com.example.hc.domain.model.Account;
import com.example.hc.domain.model.Character;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 02.02.2023
 */
@Service
@RequiredArgsConstructor
public class CharacterService {

    private final JdbcTemplate intJdbcTemplate;
    private final JdbcTemplate mainJdbcTemplate;

    public List<Account> getAccounts() {
        return intJdbcTemplate.query(
                "SELECT UserNum FROM Account.dbo.cabal_auth_table",
                (rs, rowNum) -> new Account(rs.getInt("UserNum")));
    }

    public List<Character> getCharacters() {
        return intJdbcTemplate.query(
                "SELECT Name, CharacterIdx, Style FROM Server01.dbo.cabal_character_table",
                (rs, rowNum) -> new Character(rs.getString("Name"), rs.getInt("CharacterIdx"),
                        rs.getLong("Style"), Integer.parseInt(String.valueOf(rs.getLong("Style") & 7L))));
    }

    public Character getCharacterById(Integer charId) {
        return intJdbcTemplate.queryForObject(
                "SELECT Name, CharacterIdx, Style FROM Server01.dbo.cabal_character_table WHERE CharacterIdx=" + charId,
                (rs, rowNum) -> new Character(rs.getString("Name"), rs.getInt("CharacterIdx"),
                        rs.getLong("Style"), Integer.parseInt(String.valueOf(rs.getLong("Style") & 7L))));
    }

    public Character getMainCharacter(Integer charId) {
        try {
            return mainJdbcTemplate.queryForObject(
                    "SELECT Name, CharacterIdx, Style FROM Server01.dbo.cabal_character_table WHERE CharacterIdx=" + charId,
                    (rs, rowNum) -> new Character(rs.getString("Name"), rs.getInt("CharacterIdx"),
                            rs.getLong("Style"), Integer.parseInt(String.valueOf(rs.getLong("Style") & 7L))));
        } catch (Exception e) {
            return null;
        }
    }
}
