package com.example.hc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.hc.constants.ItemConstants;
import com.example.hc.domain.model.Guild;
import com.example.hc.domain.model.Item;
import com.example.hc.repository.GuildRepository;
import com.example.hc.service.util.ItemUtil;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.04.2023
 */
@Service
@RequiredArgsConstructor
public class GuildService {

    private final GuildRepository guildRepository;

    public void patchGuildWarehouse() {
        System.out.println("========= START1 ===============");
        List<Guild> guilds = guildRepository.getGuildWarehouses();
        List<Guild> updated = processGuilds(guilds);
        guildRepository.update(updated);
        System.out.println("========= END1 ===============");
    }

  private List<Guild> processGuilds(List<Guild> guilds) {
        ArrayList<Guild> result = new ArrayList<>(guilds);
        for (Guild guild : result) {
            for (Item item : guild.getItems()) {
                Optional.ofNullable(ItemConstants.ITEM_MAPPING.get(item.getItemId()))
                        .ifPresent(newItemId -> ItemUtil.setNewItemId(item, newItemId));
            }
        }
        return result;
    }
}
