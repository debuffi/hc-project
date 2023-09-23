package com.example.hc.domain.mapper;

import java.nio.charset.StandardCharsets;

import com.example.hc.domain.dto.MasteryRuneCharDto;
import com.example.hc.domain.entity.MasteryRune;
import com.example.hc.domain.entity.MasteryRuneConfig;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@UtilityClass
public class MasteryMapper {

    @SneakyThrows
    public MasteryRuneCharDto toDto(MasteryRuneConfig config, MasteryRune rune) {
        final MasteryRuneCharDto dto = new MasteryRuneCharDto();
        dto.setType(rune.getType());
        String win1251 = new String(rune.getName().getBytes(StandardCharsets.ISO_8859_1), "Windows-1251");
        dto.setName(win1251);
        dto.setValue(config.getValue());
        dto.setCurrentLvl(config.getLvl());
        dto.setMaxLvl(rune.getMaxLvl());
        dto.setForceId(rune.getForceId());
        dto.setNextValue(config.getNextValue());
        dto.setNextLvlCost(config.getNextLvlCost());
        return dto;
    }
}
