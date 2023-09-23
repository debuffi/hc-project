package com.example.hc.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.08.2023
 */
@Data
@Entity
@Table(name = "mastery_runes_users")
public class MasteryRunesUsers {

    @EmbeddedId
    private MasteryRunesUsersId id;

    @ManyToOne
    @MapsId("masteryRuneConfigId")
    @JoinColumn(name = "mastery_rune_config_id")
    private MasteryRuneConfig masteryRuneConfig;


    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MasteryRunesUsersId implements Serializable {
        private Long charId;
        private Long masteryRuneConfigId;
    }
}
