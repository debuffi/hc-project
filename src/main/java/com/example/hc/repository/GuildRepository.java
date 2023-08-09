package com.example.hc.repository;

import java.util.List;
import java.util.Objects;

import com.example.hc.domain.model.Guild;
import com.example.hc.service.util.ItemUtil;

import org.apache.commons.codec.binary.Hex;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Vyacheslav Savinov
 * @since 09.04.2023
 */
@Repository
@RequiredArgsConstructor
public class GuildRepository {

    private final JdbcTemplate intJdbcTemplate;

    public List<Guild> getGuildWarehouses() {
        return intJdbcTemplate.query(
                "SELECT guildNo, items FROM Server01.dbo.guild_warehouse_table",
                (rs, rowNum) -> new Guild(rs.getLong("guildNo"), ItemUtil.toGuildModel(Hex.encodeHexString(rs.getBytes("items")).split("(?<=\\G.{40})"))));

    }

    @SneakyThrows
    public void update(List<Guild> updated) {
        for (Guild guild : updated) {
            String aasd = Objects.equals(ItemUtil.toGuildBinary(guild.getItems()), "") ? "00" : ItemUtil.toGuildBinary(guild.getItems());
            byte[] bytes = Hex.decodeHex(aasd);
            intJdbcTemplate.update("UPDATE Server01.dbo.guild_warehouse_table SET items = ? WHERE guildNo=" + guild.getId(), bytes);
        }

    }
}
