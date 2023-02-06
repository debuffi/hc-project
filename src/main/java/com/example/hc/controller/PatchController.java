package com.example.hc.controller;

import java.util.List;

import com.example.hc.domain.model.Skill;
import com.example.hc.service.ItemService;
import com.example.hc.service.SkillService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.02.2023
 */
@RestController
@RequiredArgsConstructor
public class PatchController {

    private final SkillService skillService;
    private final ItemService itemService;

    @GetMapping("/get/{charId}")
    public List<Skill> getSkills(@PathVariable Integer charId) {
        return skillService.get(charId);
    }

    @GetMapping("/patch/skills/{charId}")
    public List<Skill> patchSkills(@PathVariable Integer charId) {
        return skillService.patch(charId);
    }

    @GetMapping("/patch-items")
    public void patchItems() {
        itemService.patch();
    }
}
