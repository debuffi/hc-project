package com.example.hc.service;

import java.util.List;

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

    private final JdbcTemplate jdbcTemplate;

    public List<Character> getCharacters() {
        return jdbcTemplate.query(
                "SELECT Name, CharacterIdx, Style FROM Server01.dbo.cabal_character_table WHERE CharacterIdx=56",
                (rs, rowNum) -> new Character(rs.getString("Name"), rs.getInt("CharacterIdx"),
                        rs.getLong("Style"), Integer.parseInt(String.valueOf(rs.getLong("Style") & 7L))));
    }
}
