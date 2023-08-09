package com.example.hc.repository;

import java.util.List;

import com.example.hc.domain.model.Cash;
import com.example.hc.domain.model.Item;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.04.2023
 */
@Repository
@RequiredArgsConstructor
public class CashRepository {

    private final JdbcTemplate intJdbcTemplate;

    public List<Cash> getCash() {
        return intJdbcTemplate.query(
                "SELECT Id, ItemKindIdx FROM CabalCash.dbo.MyCashItem WHERE IsUse = 0",
                (rs, rowNum) -> new Cash(rs.getLong("Id"), new Item(rs.getInt("ItemKindIdx") % 4095, rs.getInt("ItemKindIdx"))));
    }

    public void update(List<Cash> updatedCashData) {
        for (Cash cash : updatedCashData) {
            intJdbcTemplate.update("UPDATE CabalCash.dbo.MyCashItem SET ItemKindIdx = " + cash.getItem().getKindId() + " WHERE Id=" + cash.getId());
        }

    }
}
