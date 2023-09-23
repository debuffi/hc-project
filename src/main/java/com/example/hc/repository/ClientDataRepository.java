package com.example.hc.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.09.2023
 */
@Repository
@RequiredArgsConstructor
public class ClientDataRepository {

    private final JdbcTemplate intJdbcTemplate;

    public Map<Integer, Integer> getBattleStyleChars(final Integer userNum) {
        final String charIds = IntStream.range(0, 7).map(x -> 8 * userNum + x).boxed().map(String::valueOf)
                .collect(Collectors.joining(", "));

        List<Integer> guildCharIds = intJdbcTemplate.query(
                "SELECT CharacterIndex FROM Server01.dbo.GuildMember WHERE GuildNo IN (SELECT DISTINCT GuildNo FROM Server01.dbo.GuildMember WHERE CharacterIndex IN (" + charIds + "))",
                (rs, rowNum) -> rs.getInt("CharacterIndex"));

        List<Integer> friendCharIds = intJdbcTemplate.query(
                "SELECT RegisterCharIdx FROM Server01.dbo.chat_buddy_table WHERE RegisteeCharIdx IN (" + charIds + ")",
                (rs, rowNum) -> rs.getInt("RegisterCharIdx"));

        friendCharIds.addAll(guildCharIds);
        String collect = friendCharIds.stream().distinct().map(String::valueOf)
                .collect(Collectors.joining(", "));
        Map<Integer, Integer> resultMap = new HashMap<>();
        intJdbcTemplate.query(
                "SELECT CharacterIdx, ClassStyle FROM Server01.dbo.cabal_character_table WHERE ClassStyle IN (7,8) AND CharacterIdx IN (" + collect + ")",
                (RowMapper<Void>) (rs, rowNum) -> {
                    int characterIdx = rs.getInt("CharacterIdx");
                    int classStyle = rs.getInt("ClassStyle");
                    resultMap.put(characterIdx, classStyle);
                    return null;
                });
        return resultMap;
    }
}