package com.example.hc.repository;

import java.util.List;

import com.example.hc.domain.model.AgentShop;
import com.example.hc.service.util.ItemUtil;

import org.apache.commons.codec.binary.Hex;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Vyacheslav Savinov
 * @since 03.04.2023
 */
@Repository
@RequiredArgsConstructor
public class ShopRepository {

    private final JdbcTemplate intJdbcTemplate;

    public List<AgentShop> getAgentShop(String tableName) {
        return intJdbcTemplate.query(
                "SELECT UserNum, ItemUnit, SlotIdx FROM Server01.dbo." + tableName,
                (rs, rowNum) -> new AgentShop(ItemUtil.toModel(rs.getString("ItemUnit")), rs.getInt("UserNum"),
                        rs.getInt("SlotIdx")));
    }


    @SneakyThrows
    public void update(List<AgentShop> agentShops, String tableName) {
        for (AgentShop agentShop : agentShops) {
            String itemAsBytes = ItemUtil.toBinary(agentShop.getItem());
            byte[] bytes = Hex.decodeHex(itemAsBytes);
            intJdbcTemplate.update("UPDATE Server01.dbo." + tableName + " SET ItemUnit = ? WHERE UserNum=" + agentShop.getUserNum() + " AND SlotIdx=" + agentShop.getSlotId(), bytes);
        }
    }
}
