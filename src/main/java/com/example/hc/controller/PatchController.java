package com.example.hc.controller;

import java.util.List;

import com.example.hc.domain.model.Item;
import com.example.hc.domain.model.Skill;
import com.example.hc.service.CashService;
import com.example.hc.service.GuildService;
import com.example.hc.service.ItemRemover;
import com.example.hc.service.ItemService;
import com.example.hc.service.ShopService;
import com.example.hc.service.SkillService;
import com.example.hc.service.restorer.Restorer;

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
    private final Restorer restorer;
    private final CashService cashService;
    private final GuildService guildService;
    private final ShopService shopService;
    private final ItemRemover itemRemover;

    @GetMapping("/get/{charId}")
    public List<Skill> getSkills(@PathVariable Integer charId) {
        return skillService.get(charId);
    }

    // восстановить эквип + инвентарь на чаров
    @GetMapping("/restore/chars/all")
    public void restoreChars() {
        restorer.restoreChars();
    }

    // восстановить хран на акк
    @GetMapping("/restore/accs/all")
    public void restoreAccs() {
        restorer.restoreAccs();
    }

    @GetMapping("/restore/skills/all")
    public void restoreSkills() {
        restorer.restoreSkills();
    }

    @GetMapping("/patch/skills/{charId}/{classId}")
    public List<Skill> patchSkills(@PathVariable Integer charId, @PathVariable Integer classId) {
        return skillService.patch(charId, classId);
    }

    @GetMapping("/patch/skills/all")
    public void patchSkills() {
        skillService.patch();
    }

    @GetMapping("/patch/skills/new/all")
    public void newPatchSkills() {
        skillService.newPatch();
    }



    @GetMapping("/patch/items/chars/{charId}")
    public List<Item> patchItems(@PathVariable Integer charId) {
        return itemService.patchChar(charId);
    }

    @GetMapping("/patch/items/accs/{accId}")
    public List<Item> patchAccs(@PathVariable Integer accId) {
        return itemService.patchAcc(accId);
    }

    @GetMapping("/patch/items/accs/all")
    public void patchAcccItems() {
        itemService.patchAccs();
    }

    @GetMapping("/patch/items/chars/all")
    public void patchCharsItems() {
        itemService.patchChars();
    }




    @GetMapping("/patch/items/rings/chars/all")
    public void patchRingsCharsItems() {
        itemService.patchRingsChars();
    }

    @GetMapping("/patch/cash")
    public void patchCash() {
        cashService.patchCash();
    }

    @GetMapping("/patch/guild")
    public void patchGuild() {
        guildService.patchGuildWarehouse();
    }

    @GetMapping("/patch/shop")
    public void patchShop() {
        shopService.patchShops();
    }

    @GetMapping("/patch/remove/chars")
    public void removeChars() {
        itemRemover.patchChars();
    }

    @GetMapping("patch/remove/accs/")
    public void removeAccs() {
        itemRemover.patchAccs();
    }
}
