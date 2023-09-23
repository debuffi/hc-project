package com.example.hc.service;

import java.util.Map;

import com.example.hc.repository.ClientDataRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 01.09.2023
 */
@Service
@RequiredArgsConstructor
public class ClientDataService {

    private final ClientDataRepository clientDataRepository;

    public Map<Integer, Integer> getBattleStyleChars(Integer userNum) {
        return clientDataRepository.getBattleStyleChars(userNum);
    }
}
