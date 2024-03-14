package com.example.hc.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.DataOutputStream;
import java.net.Socket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vyacheslav Savinov
 * @since 02.10.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer userId;
    private Integer currentHp;
    private Integer maxHp;
    private Integer groupId;
    @JsonIgnore
    private DataOutputStream output;
}
