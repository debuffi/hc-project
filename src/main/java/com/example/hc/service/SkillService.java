package com.example.hc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.hc.constants.SkillConstants;
import com.example.hc.domain.model.Character;
import com.example.hc.domain.model.Skill;
import com.example.hc.service.util.SkillBuilder;
import com.example.hc.service.util.SkillUtil;

import org.apache.commons.codec.binary.Hex;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@Service
@RequiredArgsConstructor
public class SkillService {

    Map<Integer, Map<Integer, List<Integer>>> defaultSkills;
    Map<Integer, Set<Integer>> bookIds = SkillConstants.getBookIdsByClassId();
    Map<Integer, Integer> oldNewSkillMapping = SkillConstants.oldNewSkillMapping;
    Set<Integer> ignoredSlotIds = SkillConstants.ignoredSlotIds;

    private final JdbcTemplate intJdbcTemplate;
    private final CharacterService characterService;
    private final SkillBuilder skillsBuilder;

    @PostConstruct
    public void init() {
        defaultSkills = skillsBuilder.getDefaultSkills();
    }

    @SneakyThrows
    public List<Skill> get(Integer charId) {
        List<Skill> skills = getSkillsByCharId(charId);
        skills.forEach(System.out::println);
        return skills;
    }

    public void newPatch() {
        List<Character> characters = characterService.getCharacters();
        for (Character character : characters) {
            Integer charIdx = character.getCharIdx();
            Integer classId = character.getClassId();
            System.out.println("=======START UPD NEW PATCH SKILL ========== " + charIdx);
            if (classId == 1 || classId == 2) {
                List<Skill> skills = getSkillsByCharId(charIdx);
                for (Skill skill : skills) {
                    if (skill.getId() == 201) {
                        skill.setId(161);
                    } else if (skill.getId() == 200) {
                        skill.setId(160);
                    }
                }
                String binaryAsString = SkillUtil.toBinary(skills);
                updateSkillsByCharId(charIdx, binaryAsString);
                System.out.println("=======END========== " + charIdx);
            }
        }
    }

    public void patch() {
        List<Character> characters = List.of(characterService.getCharacterById(72));
        for (Character character : characters) {
            final Character mainCharacter = characterService.getMainCharacter(character.getCharIdx());
            if (character.getCharIdx() < 58 || mainCharacter == null || !Objects.equals(mainCharacter.getClassId(), character.getClassId())) {
                System.out.println("skip " + character.getCharIdx());
                continue;
            }
            patch(character.getCharIdx(), character.getClassId());
        }
    }

    @SneakyThrows
    public List<Skill> patch(Integer charId, Integer classId) {
        System.out.println("=======START========== " + charId);
        List<Skill> skills = getSkillsByCharId(charId);

        Set<Integer> booksByClassId = bookIds.get(classId);
        Map<Integer, List<Integer>> defaultSkillsByType = defaultSkills.get(classId);
        Map<Integer, List<Skill>> skillsByType = SkillUtil.toSkillsByType(skills);

        Set<Integer> skillIds = skills.stream().map(Skill::getId).collect(Collectors.toSet());
        for (Skill skill : skills) {
            Integer newSkillId = oldNewSkillMapping.get(skill.getId());
            if (newSkillId != null && !skillIds.contains(newSkillId)) {
                skill.setId(newSkillId);
            }
        }

        processSkills(skillsByType.get(1), defaultSkillsByType.get(1), SkillConstants.type1Boundaries, booksByClassId, false);
        processSkills(skillsByType.get(2), defaultSkillsByType.get(2), SkillConstants.type2Boundaries, booksByClassId, false);
        processSkills(skillsByType.get(3), defaultSkillsByType.get(3), SkillConstants.type3Boundaries, booksByClassId, true);

        List<Skill> updatedSkills = skillsByType.values().stream().flatMap(Collection::stream).toList();
        String binaryAsString = SkillUtil.toBinary(updatedSkills);
        updateSkillsByCharId(charId, binaryAsString);
        System.out.println("=======END========== " + charId);
        return skills;
    }

    private void processSkills(List<Skill> skills, List<Integer> defaultSkillIds,
                               SkillConstants.SkillBoundaries boundaries, Set<Integer> bookIds, boolean passive) {

        List<Integer> allSlotIds = IntStream.range(boundaries.getFrom(), boundaries.getTo() + 1).boxed().toList();

        skills.removeIf(skill -> !defaultSkillIds.contains(skill.getId()) && !bookIds.contains(skill.getId()));

        List<Integer> busySkillIds = skills.stream().map(Skill::getId).toList();
        List<Integer> busySlotIds = skills.stream().map(Skill::getSlotId).toList();
        List<Integer> freeSlotIds = new ArrayList<>(allSlotIds);
        List<Integer> freeSkillIds = new ArrayList<>(defaultSkillIds);
        freeSkillIds.removeAll(busySkillIds);
        freeSlotIds.removeAll(busySlotIds);

        if (passive) {
            skills.forEach(skill -> skill.setLvl(SkillConstants.getLvlBySkillId(skill.getId())));
        }

        for (int i = 0; i < freeSlotIds.size(); i++) {
            if (freeSkillIds.isEmpty()) return;
            Integer skillId = freeSkillIds.get(freeSkillIds.size() - i - 1);
            skills.add(new Skill(skillId, "", SkillConstants.getLvlBySkillId(skillId) == null ? 1 : SkillConstants.getLvlBySkillId(skillId), freeSlotIds.get(i)));
            if (freeSkillIds.size() - i - 1 == 0) return;
        }
    }

    @SneakyThrows
    private void updateSkillsByCharId(Integer charId, String binarySkills) {
        byte[] skillBytes = Hex.decodeHex(binarySkills);
        intJdbcTemplate.update("UPDATE Server01.dbo.cabal_skilllist_table SET Data = ? WHERE CharacterIdx=" + charId, skillBytes);
    }

    private List<Skill> getSkillsByCharId(Integer charId) {
        byte[] skillBytes = intJdbcTemplate.queryForObject(
                "SELECT Data FROM Server01.dbo.cabal_skilllist_table WHERE CharacterIdx=" + charId,
                (rs, rowNum) -> rs.getBytes(1));
        if (skillBytes.length == 0) return new ArrayList<>();
        String binaryAsString = Hex.encodeHexString(skillBytes);
        return SkillUtil.toModel(binaryAsString.split("(?<=\\G.{10})"));
    }
}
