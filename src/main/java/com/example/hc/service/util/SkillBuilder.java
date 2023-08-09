package com.example.hc.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.hc.constants.SkillConstants;
import com.example.hc.domain.model.Skill;
import com.example.hc.service.util.SkillUtil;

import org.springframework.stereotype.Service;

/**
 * @author Vyacheslav Savinov
 * @since 02.02.2023
 */
@Service
public class SkillBuilder {


    public Map<Integer, Map<Integer, List<Integer>>> getDefaultSkills() {
        List<Skill> blSkills = new ArrayList<>();
        List<Skill> fbSkills = new ArrayList<>();
        List<Skill> fsSkills = new ArrayList<>();
        List<Skill> faSkills = new ArrayList<>();
        List<Skill> waSkills = new ArrayList<>();
        List<Skill> wiSkills = new ArrayList<>();
        List<Skill> glSkills = new ArrayList<>();
        List<Skill> fgSkills = new ArrayList<>();

        addSystemSkillsToList(SkillConstants.BL_4, blSkills);
        addSkillsWithLvl(SkillConstants.BL_1, SkillConstants.BL_1_2, 0, blSkills);
        addSkillsToList(SkillConstants.BL_2, 32, blSkills);
        addPassiveSkillsToList(SkillConstants.BL_3, 82, blSkills);

        addSystemSkillsToList(SkillConstants.FB_4, fbSkills);
        addSkillsWithLvl(SkillConstants.FB_1, SkillConstants.FB_1_2, 0, fbSkills);
        addSkillsToList(SkillConstants.FB_2, 32, fbSkills);
        addPassiveSkillsToList(SkillConstants.FB_3, 82, fbSkills);

        addSystemSkillsToList(SkillConstants.FS_4, fsSkills);
        addSkillsWithLvl(SkillConstants.FS_1, SkillConstants.FS_1_2, 0, fsSkills);
        addSkillsToList(SkillConstants.FS_2, 32, fsSkills);
        addPassiveSkillsToList(SkillConstants.FS_3, 82, fsSkills);

        addSystemSkillsToList(SkillConstants.FA_4, faSkills);
        addSkillsWithLvl(SkillConstants.FA_1, SkillConstants.FA_1_2, 0, faSkills);
        addSkillsToList(SkillConstants.FA_2, 32, faSkills);
        addPassiveSkillsToList(SkillConstants.FA_3, 82, faSkills);

        addSystemSkillsToList(SkillConstants.WA_4, waSkills);
        addSkillsWithLvl(SkillConstants.WA_1, SkillConstants.WA_1_2, 0, waSkills);
        addSkillsToList(SkillConstants.WA_2, 32, waSkills);
        addPassiveSkillsToList(SkillConstants.WA_3, 82, waSkills);

        addSystemSkillsToList(SkillConstants.WI_4, wiSkills);
        addSkillsWithLvl(SkillConstants.WI_1, SkillConstants.WI_1_2, 0, wiSkills);
        addSkillsToList(SkillConstants.WI_2, 32, wiSkills);
        addPassiveSkillsToList(SkillConstants.WI_3, 82, wiSkills);

        addSystemSkillsToList(SkillConstants.GL_4, glSkills);
        addSkillsWithLvl(SkillConstants.GL_1, SkillConstants.GL_1_2, 0, glSkills);
        addSkillsToList(SkillConstants.GL_2, 32, glSkills);
        addPassiveSkillsToList(SkillConstants.GL_3, 82, glSkills);

        addSystemSkillsToList(SkillConstants.FG_4, fgSkills);
        addSkillsWithLvl(SkillConstants.FG_1, SkillConstants.FG_1_2, 0, fgSkills);
        addSkillsToList(SkillConstants.FG_2, 32, fgSkills);
        addPassiveSkillsToList(SkillConstants.FG_3, 82, fgSkills);

        String blBinary = SkillUtil.toBinary(blSkills);
        String fbBinary = SkillUtil.toBinary(fbSkills);
        String fsBinary = SkillUtil.toBinary(fsSkills);
        String faBinary = SkillUtil.toBinary(faSkills);
        String waBinary = SkillUtil.toBinary(waSkills);
        String wiBinary = SkillUtil.toBinary(wiSkills);
        String glBinary = SkillUtil.toBinary(glSkills);
        String fgBinary = SkillUtil.toBinary(fgSkills);

        System.out.println("=========================");
        System.out.println("WA: ");
        System.out.println(waBinary);
        System.out.println("BL: ");
        System.out.println(blBinary);
        System.out.println("WI: ");
        System.out.println(wiBinary);
        System.out.println("FA: ");
        System.out.println(faBinary);
        System.out.println("FS: ");
        System.out.println(fsBinary);
        System.out.println("FB: ");
        System.out.println(fbBinary);
        System.out.println("GL: ");
        System.out.println(glBinary);
        System.out.println("FG: ");
        System.out.println(fgBinary);
        System.out.println("=========================");

        return Map.of(
                1, SkillUtil.toSkillSetsByType(waSkills),
                2, SkillUtil.toSkillSetsByType(blSkills),
                3, SkillUtil.toSkillSetsByType(wiSkills),
                4, SkillUtil.toSkillSetsByType(faSkills),
                5, SkillUtil.toSkillSetsByType(fsSkills),
                6, SkillUtil.toSkillSetsByType(fbSkills),
                7, SkillUtil.toSkillSetsByType(glSkills),
                8, SkillUtil.toSkillSetsByType(fgSkills));
    }

    private static void addSkillsWithLvl(List<Integer> skillIds, List<SkillConstants.SkillLvl> skillLvls, int slotId, List<Skill> result) {
        for (SkillConstants.SkillLvl skillLvl : skillLvls) {
            result.add(new Skill(skillLvl.getId(), "", skillLvl.getLvl(), slotId));
            slotId++;
        }
        for (Integer skillId : skillIds) {
            result.add(new Skill(skillId, "", 1, slotId));
            slotId++;
        }
    }

    private static void addPassiveSkillsToList(List<SkillConstants.SkillLvl> skillLvls, int slotId, List<Skill> result) {
        for (SkillConstants.SkillLvl skillLvl : skillLvls) {
            result.add(new Skill(skillLvl.getId(), "", skillLvl.getLvl(), slotId));
            slotId++;
        }
    }

    private static void addSystemSkillsToList(List<SkillConstants.SkillSlotLvl> skillSlotLvls, List<Skill> result) {
        for (SkillConstants.SkillSlotLvl skillSlotLvl : skillSlotLvls) {
            result.add(new Skill(skillSlotLvl.getId(), "", skillSlotLvl.getLvl(), skillSlotLvl.getSlotId()));
        }
    }

    private static void addSkillsToList(List<Integer> skillIds, int slotId, List<Skill> result) {
        for (Integer skillId : skillIds) {
            result.add(new Skill(skillId, "", 1, slotId));
            slotId++;
        }
    }
}
