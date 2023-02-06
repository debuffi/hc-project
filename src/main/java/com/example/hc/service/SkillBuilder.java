package com.example.hc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.hc.constants.NewCharSkills;
import com.example.hc.domain.model.Skill;

import org.springframework.stereotype.Service;

/**
 * @author Vyacheslav Savinov
 * @since 02.02.2023
 */
@Service
public class SkillBuilder {


    public Map<Integer, Map<Integer, Set<Integer>>> getDefaultSkills() {
        List<Skill> blSkills = new ArrayList<>();
        List<Skill> fbSkills = new ArrayList<>();
        List<Skill> fsSkills = new ArrayList<>();
        List<Skill> faSkills = new ArrayList<>();
        List<Skill> waSkills = new ArrayList<>();
        List<Skill> wiSkills = new ArrayList<>();
        List<Skill> glSkills = new ArrayList<>();
        List<Skill> fgSkills = new ArrayList<>();

        addSystemSkillsToList(NewCharSkills.BL_4, blSkills);
        addSkillsWithLvl(NewCharSkills.BL_1, NewCharSkills.BL_1_2, 0, blSkills);
        addSkillsToList(NewCharSkills.BL_2, 32, blSkills);
        addPassiveSkillsToList(NewCharSkills.BL_3, 82, blSkills);

        addSystemSkillsToList(NewCharSkills.FB_4, fbSkills);
        addSkillsWithLvl(NewCharSkills.FB_1, NewCharSkills.FB_1_2, 0, fbSkills);
        addSkillsToList(NewCharSkills.FB_2, 32, fbSkills);
        addPassiveSkillsToList(NewCharSkills.FB_3, 82, fbSkills);

        addSystemSkillsToList(NewCharSkills.FS_4, fsSkills);
        addSkillsWithLvl(NewCharSkills.FS_1, NewCharSkills.FS_1_2, 0, fsSkills);
        addSkillsToList(NewCharSkills.FS_2, 32, fsSkills);
        addPassiveSkillsToList(NewCharSkills.FS_3, 82, fsSkills);

        addSystemSkillsToList(NewCharSkills.FA_4, faSkills);
        addSkillsWithLvl(NewCharSkills.FA_1, NewCharSkills.FA_1_2, 0, faSkills);
        addSkillsToList(NewCharSkills.FA_2, 32, faSkills);
        addPassiveSkillsToList(NewCharSkills.FA_3, 82, faSkills);

        addSystemSkillsToList(NewCharSkills.WA_4, waSkills);
        addSkillsWithLvl(NewCharSkills.WA_1, NewCharSkills.WA_1_2, 0, waSkills);
        addSkillsToList(NewCharSkills.WA_2, 32, waSkills);
        addPassiveSkillsToList(NewCharSkills.WA_3, 82, waSkills);

        addSystemSkillsToList(NewCharSkills.WI_4, wiSkills);
        addSkillsWithLvl(NewCharSkills.WI_1, NewCharSkills.WI_1_2, 0, wiSkills);
        addSkillsToList(NewCharSkills.WI_2, 32, wiSkills);
        addPassiveSkillsToList(NewCharSkills.WI_3, 82, wiSkills);

        addSystemSkillsToList(NewCharSkills.GL_4, glSkills);
        addSkillsWithLvl(NewCharSkills.GL_1, NewCharSkills.GL_1_2, 0, glSkills);
        addSkillsToList(NewCharSkills.GL_2, 32, glSkills);
        addPassiveSkillsToList(NewCharSkills.GL_3, 82, glSkills);

        addSystemSkillsToList(NewCharSkills.FG_4, fgSkills);
        addSkillsWithLvl(NewCharSkills.FG_1, NewCharSkills.FG_1_2, 0, fgSkills);
        addSkillsToList(NewCharSkills.FG_2, 32, fgSkills);
        addPassiveSkillsToList(NewCharSkills.FG_3, 82, fgSkills);

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

    private static void addSkillsWithLvl(List<Integer> skillIds, List<NewCharSkills.SkillLvl> skillLvls, int slotId, List<Skill> result) {
        for (NewCharSkills.SkillLvl skillLvl : skillLvls) {
            result.add(new Skill(skillLvl.getId(), "", skillLvl.getLvl(), slotId));
            slotId++;
        }
        for (Integer skillId : skillIds) {
            result.add(new Skill(skillId, "", 20, slotId));
            slotId++;
        }
    }

    private static void addPassiveSkillsToList(List<NewCharSkills.SkillLvl> skillLvls, int slotId, List<Skill> result) {
        for (NewCharSkills.SkillLvl skillLvl : skillLvls) {
            result.add(new Skill(skillLvl.getId(), "", skillLvl.getLvl(), slotId));
            slotId++;
        }
    }

    private static void addSystemSkillsToList(List<NewCharSkills.SkillSlotLvl> skillSlotLvls, List<Skill> result) {
        for (NewCharSkills.SkillSlotLvl skillSlotLvl : skillSlotLvls) {
            result.add(new Skill(skillSlotLvl.getId(), "", skillSlotLvl.getLvl(), skillSlotLvl.getSlotId()));
        }
    }

    private static void addSkillsToList(List<Integer> skillIds, int slotId, List<Skill> result) {
        for (Integer skillId : skillIds) {
            result.add(new Skill(skillId, "", 20, slotId));
            slotId++;
        }
    }
}
