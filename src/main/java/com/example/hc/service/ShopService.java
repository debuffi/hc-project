package com.example.hc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.hc.constants.ItemConstants;
import com.example.hc.domain.model.AgentShop;
import com.example.hc.repository.ShopRepository;
import com.example.hc.service.util.ItemUtil;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 03.04.2023
 */
@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public void patchShops() {
        System.out.println("========= START1 ===============");
        String tableName1 = "cabal_agentshop_done_table";
        List<AgentShop> agentShops = shopRepository.getAgentShop(tableName1);
        List<AgentShop> updatedShopData = processShop(agentShops);
        shopRepository.update(updatedShopData, tableName1);
        System.out.println("========= END1 ===============");

        System.out.println("========= START2 ===============");
        String tableName2 = "cabal_agentshop_sale_table";
        List<AgentShop> agentShops2 = shopRepository.getAgentShop(tableName2);
        List<AgentShop> updatedShopData2 = processShop(agentShops2);
        shopRepository.update(updatedShopData2, tableName2);
        System.out.println("========= END2 ===============");
    }

    private List<AgentShop> processShop(List<AgentShop> agentShops) {
        ArrayList<AgentShop> result = new ArrayList<>(agentShops);
        for (AgentShop agentShop : result) {
            Optional.ofNullable(
                            ItemConstants.ITEM_MAPPING.get(agentShop.getItem().getItemId())
                    )
                    .ifPresent(newItemId -> ItemUtil.setNewItemId(agentShop.getItem(), newItemId));
        }
        return result;
    }
}
