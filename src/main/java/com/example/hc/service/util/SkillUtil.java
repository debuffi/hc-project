package com.example.hc.service.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.hc.constants.SkillConstants;
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
        SkillConstants.SkillBoundaries type1Boundaries = SkillConstants.type1Boundaries;
        SkillConstants.SkillBoundaries type2Boundaries = SkillConstants.type2Boundaries;
        SkillConstants.SkillBoundaries type3Boundaries = SkillConstants.type3Boundaries;
        SkillConstants.SkillBoundaries type4Boundaries_2 = SkillConstants.type4Boundaries_2;
        SkillConstants.SkillBoundaries type4Boundaries = SkillConstants.type4Boundaries;

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

    public static Map<Integer, List<Integer>> toSkillSetsByType(List<Skill> skills) {
        List<Integer> type1Skills = new ArrayList<>();
        List<Integer> type2Skills = new ArrayList<>();
        List<Integer> type3Skills = new ArrayList<>();
        List<Integer> type4Skills = new ArrayList<>();
        SkillConstants.SkillBoundaries type1Boundaries = SkillConstants.type1Boundaries;
        SkillConstants.SkillBoundaries type2Boundaries = SkillConstants.type2Boundaries;
        SkillConstants.SkillBoundaries type3Boundaries = SkillConstants.type3Boundaries;
        SkillConstants.SkillBoundaries type4Boundaries_2 = SkillConstants.type4Boundaries_2;
        SkillConstants.SkillBoundaries type4Boundaries = SkillConstants.type4Boundaries;

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

    public static boolean inBoundaries(Integer slotId, SkillConstants.SkillBoundaries boundaries) {
        return slotId <= boundaries.getTo() && slotId >= boundaries.getFrom();
    }
}
