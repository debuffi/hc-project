package com.example.hc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import com.example.hc.constants.NewCharSkills;
import com.example.hc.domain.model.Skill;

import org.apache.commons.codec.binary.Hex;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@Service
@RequiredArgsConstructor
public class SkillService {

    private final JdbcTemplate jdbcTemplate;
    private final CharacterService characterService;
    private final SkillBuilder skillsBuilder;

    @SneakyThrows
    public List<Skill> get(Integer charId) {
        List<Skill> skills = getSkillsByCharId(charId);
        skills.forEach(System.out::println);
        return skills;
    }

    @SneakyThrows
    public List<Skill> patch(Integer charId) {
        Map<Integer, Map<Integer, Set<Integer>>> defaultSkills = skillsBuilder.getDefaultSkills();
        Map<Integer, Integer> oldNewSkillMapping = NewCharSkills.oldNewSkillMapping;
        Set<Integer> ignoredSlotIds = NewCharSkills.ignoredSlotIds;
//        List<Character> characters = characterService.getCharacters();

        List<Skill> skills = getSkillsByCharId(charId);
        Integer classId = 2;


        Map<Integer, Set<Integer>> defaultSkillsByType = defaultSkills.get(classId);
        Map<Integer, List<Skill>> skillsByType = SkillUtil.toSkillsByType(skills);
        processSkills(skillsByType.get(1), defaultSkillsByType.get(1), NewCharSkills.type1Boundaries);

        for (Skill skill : skills) {
            Integer newSkillId = oldNewSkillMapping.get(skill.getId());
            if (newSkillId != null) {
                skill.setId(newSkillId);
            }
        }

        for (Skill skill : skills) {
            if (ignoredSlotIds.contains(skill.getSlotId())) {
                continue;
            }


        }

        List<Skill> updatedSkills = List.of();
        String binaryAsString = SkillUtil.toBinary(updatedSkills);
        updateSkillsByCharId(56, binaryAsString);
        System.out.println("OLD SKILLS ==============================");
        skills.forEach(System.out::println);
        System.out.println("NEW SKILLS ==============================");
        updatedSkills.forEach(System.out::println);
        System.out.println("==============================");

        return skills;
    }

    private void processSkills(List<Skill> skills, Set<Integer> defaultSkillIds, NewCharSkills.SkillBoundaries boundaries) {
        List<Integer> integers = IntStream.range(boundaries.getFrom(), boundaries.getTo()).boxed().toList();
        for (Skill skill : skills) {
            if (!defaultSkillIds.contains(skill.getId())) {
                skills.remove(skill);
            }
        }


    }

    @SneakyThrows
    private void updateSkillsByCharId(Integer charId, String binarySkills) {
        byte[] skillBytes = Hex.decodeHex(binarySkills);
        jdbcTemplate.update("UPDATE Server01.dbo.cabal_skilllist_table SET Data = ? WHERE CharacterIdx=" + charId, skillBytes);
    }

    private List<Skill> getSkillsByCharId(Integer charId) {
        byte[] skillBytes = jdbcTemplate.queryForObject(
                "SELECT Data FROM Server01.dbo.cabal_skilllist_table WHERE CharacterIdx=" + charId,
                (rs, rowNum) -> rs.getBytes(1));
        String binaryAsString = Hex.encodeHexString(skillBytes);
        return SkillUtil.toModel(binaryAsString.split("(?<=\\G.{10})"));
    }
}
