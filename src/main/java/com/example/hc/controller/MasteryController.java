package com.example.hc.controller;

import java.util.List;
import java.util.Map;

import com.example.hc.domain.dto.MasteryRuneCharDto;
import com.example.hc.domain.entity.Mastery;
import com.example.hc.service.MasteryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mastery/")
public class MasteryController {

    private final MasteryService masteryService;

    @GetMapping
    public Mastery getMasteryInfo() {
        return masteryService.getMasteryInfo();
    }

    @GetMapping("/chars/{charId}")
    public List<MasteryRuneCharDto> getMasteryRuneCharList(@PathVariable Long charId) {
        return masteryService.getMasteryRuneCharList(charId);
    }

    @GetMapping("/chars/{charId}/forces/{forceId}")
    public List<MasteryRuneCharDto> incForceLvl(@PathVariable Long charId, @PathVariable Long forceId) {
        return masteryService.incForceLvl(charId, forceId);
    }

    @GetMapping("/chars/{charId}/points")
    public String getMasteryPointsByCharId(@PathVariable Long charId) {
        return masteryService.getMasteryPointsByCharId(charId);
    }

    @GetMapping("/cache/refresh")
    public Map<String, Object> refreshCache() {
        masteryService.refreshCache();
        return Map.of("status", "OK");
    }


    //TODO TEST
    @GetMapping("/chars/{charId}/points/inc")
    public void incPoint(@PathVariable Long charId){
        masteryService.incPointByCharId(charId);
    }
}
