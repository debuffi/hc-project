package com.example.hc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.hc.constants.ItemConstants;
import com.example.hc.domain.model.AgentShop;
import com.example.hc.domain.model.Cash;
import com.example.hc.repository.CashRepository;
import com.example.hc.service.util.ItemUtil;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 09.04.2023
 */
@Service
@RequiredArgsConstructor
public class CashService {

    private final CashRepository cashRepository;

    public void patchCash() {
        System.out.println("========= START1 ===============");
        List<Cash> cashList = cashRepository.getCash();
        List<Cash> updatedCashData = processCash(cashList);
        cashRepository.update(updatedCashData);
        System.out.println("========= END1 ===============");
    }

    private List<Cash> processCash(List<Cash> cashList) {
        ArrayList<Cash> result = new ArrayList<>(cashList);
        for (Cash cash : result) {
            Optional.ofNullable(ItemConstants.ITEM_MAPPING.get(cash.getItem().getItemId()))
                    .ifPresent(newItemId -> ItemUtil.setNewItemId(cash.getItem(), newItemId));
        }
        return result;
    }
}
