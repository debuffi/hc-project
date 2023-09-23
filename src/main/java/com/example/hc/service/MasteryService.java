package com.example.hc.service;

import com.example.hc.domain.dto.MasteryRuneCharDto;
import com.example.hc.domain.entity.Mastery;
import com.example.hc.domain.entity.MasteryRune;
import com.example.hc.domain.entity.MasteryRuneConfig;
import com.example.hc.domain.mapper.MasteryMapper;
import com.example.hc.repository.mastery.MasteryRepository;
import com.example.hc.repository.mastery.MasteryRuneConfigRepository;
import com.example.hc.repository.mastery.MasteryRuneRepository;
import com.example.hc.repository.mastery.MasteryRunesUsersRepository;
import com.example.hc.repository.mastery.MasteryUserPointsRepository;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@Service
@RequiredArgsConstructor
public class MasteryService {

    private final MasteryRepository masteryRepository;
    private final MasteryRuneRepository masteryRuneRepository;
    private final MasteryRuneConfigRepository masteryRuneConfigRepository;
    private final MasteryRunesUsersRepository masteryRunesUsersRepository;
    private final MasteryUserPointsRepository masteryUserPointsRepository;

    private Map<Long, MasteryRune> masteryRuneMap = new HashMap<>();
    private Map<Long, MasteryRuneConfig> masteryRuneConfigMap = new HashMap<>();

    @PostConstruct
    public void init() {
        masteryRuneMap = masteryRuneRepository.findAll().stream().collect(Collectors.toMap(MasteryRune::getId, Function.identity()));
        masteryRuneConfigMap = masteryRuneConfigRepository.findAll().stream().collect(Collectors.toMap(MasteryRuneConfig::getId, Function.identity()));
    }

    public void refreshCache() {
        masteryRuneMap = masteryRuneRepository.findAll().stream().collect(Collectors.toMap(MasteryRune::getId, Function.identity()));
        masteryRuneConfigMap = masteryRuneConfigRepository.findAll().stream().collect(Collectors.toMap(MasteryRuneConfig::getId, Function.identity()));
    }

    public Mastery getMasteryInfo() {
        return masteryRepository.findById(1L).get();
    }

    public List<MasteryRuneCharDto> getMasteryRuneCharList(Long charId) {
        return masteryRunesUsersRepository.findRuneConfigIdsByCharId(charId)
            .stream()
            .map(runeConfigId -> masteryRuneConfigMap.get(runeConfigId))
            .map(runeConfig -> MasteryMapper.toDto(runeConfig, masteryRuneMap.get(runeConfig.getMasteryRuneId())))
            .toList();
    }

    public String getMasteryPointsByCharId(Long charId) {
        val userPoints = masteryUserPointsRepository.findPointsByCharId(charId);
        return userPoints.getPoints() + " " + userPoints.getLevelPoints();
    }

    @Transactional
    public void incPointByCharId(Long charId) {
        masteryUserPointsRepository.incPointsByCharId(charId);
    }

    @Transactional
    public List<MasteryRuneCharDto> incForceLvl(final Long charId, final Long forceId) {
        val masteryRuneConfigId = masteryRunesUsersRepository.findRuneConfigIdsByCharIdAndForceId(charId, forceId);
        val masteryRuneConfig = masteryRuneConfigMap.get(masteryRuneConfigId);
        var points = masteryUserPointsRepository.findPointsByCharId(charId).getPoints();
        if (points < masteryRuneConfig.getNextLvlCost()) {
            throw new RuntimeException();
        }

        points = points - masteryRuneConfig.getNextLvlCost();
        masteryUserPointsRepository.updatePointsByCharId(charId, points);
        masteryRunesUsersRepository.updateForceLvl(charId, forceId, masteryRuneConfigId, masteryRuneConfig.getLvl() + 1);

        return getMasteryRuneCharList(charId);
    }
}
