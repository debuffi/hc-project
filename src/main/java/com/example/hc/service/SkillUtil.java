package com.example.hc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.hc.constants.NewCharSkills;
import com.example.hc.domain.model.Skill;
import static com.example.hc.constants.SkillsMsg.SKILLS;

import lombok.experimental.UtilityClass;

/**
 * @author Vyacheslav Savinov
 * @since 02.02.2023
 */
@UtilityClass
public class SkillUtil {

    public String toBinary(List<Skill> skills) {
        StringBuilder stringBuilder = new StringBuilder();
        skills.forEach(skill -> {
            String skillId = toBinary(skill.getId(), 4);
            String skillLvl = toBinary(skill.getLvl(), 2);
            String skillSlotId = toBinary(skill.getSlotId(), 4);
            String binarySkill = skillId + skillLvl + skillSlotId;
            stringBuilder.append(binarySkill);
        });
        return stringBuilder.toString();
    }

    public String toBinary(Integer var, Integer countBytes) {
        return Arrays.stream(String.format("%0" + countBytes + "X", var).split("(?<=\\G.{2})")).collect(ArrayList::new,
                (list, e) -> list.add(0, e),
                (list1, list2) -> list1.addAll(0, list2)).stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    public List<Skill> toModel(String[] skillsAsArray) {
        List<Skill> result = new ArrayList<>();
        for (String skill : skillsAsArray) {
            String[] skillArray = skill.split("(?<=\\G.{2})");
            int skillId = Integer.parseInt(skillArray[1] + skillArray[0], 16);
            int skillLvl = Integer.parseInt(skillArray[2], 16);
            int skillSlotId = Integer.parseInt(skillArray[4] + skillArray[3], 16);
            String skillMsg = SKILLS.stream().filter(s -> s.contains("skill" + String.format("%04d", skillId))).findFirst().get();
            String skillName = skillMsg.substring(skillMsg.indexOf("cont=\"") + 6, skillMsg.lastIndexOf("\""));
            result.add(new Skill(skillId, skillName, skillLvl, skillSlotId));
        }
        return result;
    }

    public static Map<Integer, List<Skill>> toSkillsByType(List<Skill> skills) {
        List<Skill> type1Skills = new ArrayList<>();
        List<Skill> type2Skills = new ArrayList<>();
        List<Skill> type3Skills = new ArrayList<>();
        List<Skill> type4Skills = new ArrayList<>();
        NewCharSkills.SkillBoundaries type1Boundaries = NewCharSkills.type1Boundaries;
        NewCharSkills.SkillBoundaries type2Boundaries = NewCharSkills.type2Boundaries;
        NewCharSkills.SkillBoundaries type3Boundaries = NewCharSkills.type3Boundaries;
        NewCharSkills.SkillBoundaries type4Boundaries_2 = NewCharSkills.type4Boundaries_2;
        NewCharSkills.SkillBoundaries type4Boundaries = NewCharSkills.type4Boundaries;

        for (Skill skill : skills) {
            final Integer slotId = skill.getSlotId();
            if (inBoundaries(slotId, type1Boundaries)) {
                type1Skills.add(skill);
            } else if (inBoundaries(slotId, type2Boundaries)) {
                type2Skills.add(skill);
            } else if (inBoundaries(slotId, type3Boundaries)) {
                type3Skills.add(skill);
            } else if (inBoundaries(slotId, type4Boundaries)) {
                type4Skills.add(skill);
            } else if (inBoundaries(slotId, type4Boundaries_2)) {
                type4Skills.add(skill);
            }
        }

        return Map.of(1, type1Skills, 2, type2Skills, 3, type3Skills, 4, type4Skills);
    }

    public static Map<Integer, Set<Integer>> toSkillSetsByType(List<Skill> skills) {
        Set<Integer> type1Skills = new HashSet<>();
        Set<Integer> type2Skills = new HashSet<>();
        Set<Integer> type3Skills = new HashSet<>();
        Set<Integer> type4Skills = new HashSet<>();
        NewCharSkills.SkillBoundaries type1Boundaries = NewCharSkills.type1Boundaries;
        NewCharSkills.SkillBoundaries type2Boundaries = NewCharSkills.type2Boundaries;
        NewCharSkills.SkillBoundaries type3Boundaries = NewCharSkills.type3Boundaries;
        NewCharSkills.SkillBoundaries type4Boundaries_2 = NewCharSkills.type4Boundaries_2;
        NewCharSkills.SkillBoundaries type4Boundaries = NewCharSkills.type4Boundaries;

        for (Skill skill : skills) {
            final Integer slotId = skill.getSlotId();
            if (inBoundaries(slotId, type1Boundaries)) {
                type1Skills.add(skill.getId());
            } else if (inBoundaries(slotId, type2Boundaries)) {
                type2Skills.add(skill.getId());
            } else if (inBoundaries(slotId, type3Boundaries)) {
                type3Skills.add(skill.getId());
            } else if (inBoundaries(slotId, type4Boundaries)) {
                type4Skills.add(skill.getId());
            } else if (inBoundaries(slotId, type4Boundaries_2)) {
                type4Skills.add(skill.getId());
            }
        }

        return Map.of(1, type1Skills, 2, type2Skills, 3, type3Skills, 4, type4Skills);
    }

    public static boolean inBoundaries(Integer slotId, NewCharSkills.SkillBoundaries boundaries) {
        return slotId <= boundaries.getTo() && slotId >= boundaries.getFrom();
    }
}
