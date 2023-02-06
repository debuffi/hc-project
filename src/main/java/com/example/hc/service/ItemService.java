package com.example.hc.service;

import java.util.List;

import com.example.hc.domain.model.Character;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final JdbcTemplate jdbcTemplate;
    private final CharacterService characterService;

    public void patch() {
        List<Character> characters = characterService.getCharacters();
    }
}

