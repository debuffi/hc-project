package com.example.hc.controller;

import java.util.Map;

import com.example.hc.service.ClientDataService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.09.2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/data/")
public class ClientDataController {

    private final ClientDataService clientDataService;

    @GetMapping("/{userNum}")
    public Map<Integer, Integer> getBattleStyleChars(@PathVariable Integer userNum) {
        return clientDataService.getBattleStyleChars(userNum);
    }
}
