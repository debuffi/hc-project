package com.example.hc.service;

import com.example.hc.domain.dto.UserInfo;
import jakarta.annotation.PostConstruct;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Vyacheslav Savinov
 * @since 02.10.2023
 */

@Slf4j
@Component
public class TcpServerService {

    private static final int PORT = 8075;
    private final ConcurrentHashMap<Socket, UserInfo> healthData = new ConcurrentHashMap<>();

    //@PostConstruct
    public void init() {
        CompletableFuture.runAsync(this::start);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                log.info("Create clientSocket");
                CompletableFuture.runAsync(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            log.error("Failed to start ServerSocket: {}", e.getMessage(), e);
        }
    }

    private void handleClient(Socket clientSocket) {
        try (DataInputStream input = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {

            int initUserId = input.readInt();

            UserInfo userInfo = healthData.get(clientSocket);
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setUserId(initUserId);
                userInfo.setOutput(output);
                userInfo.setGroupId(77);
                healthData.put(clientSocket, userInfo);
                log.info("CREATE SOCKET: userId: {}", initUserId);
            }

            while (true) {
                int availableBytes = input.available();

                while (availableBytes < 1) {
                    Thread.sleep(10);
                    availableBytes = input.available();
                }
                UserInfo info = healthData.get(clientSocket);

                if (availableBytes == 1) {
                    if (input.readByte() == 1) {
                        info.setGroupId(null);
                    }
                }

                if (availableBytes == 5) {
                    byte tmp = input.readByte();
                    Integer groupId = input.readInt();
                    info.setGroupId(groupId);
                    notifyCharsInGroup(info);
                } else if (availableBytes > 7) {
                    int currentHP = input.readInt();
                    int maxHP = input.readInt();
                    UserInfo info1 = healthData.get(clientSocket);
                    info1.setCurrentHp(currentHP);
                    info1.setMaxHp(maxHP);
                    log.info("NOTIFY ALL, USER INFO: {}, {}, {}", info1.getUserId(), currentHP, maxHP);
                    notifyCharsInGroup(info1);
                }
            }
        } catch (IOException | InterruptedException e) {
            log.error("Failed to handleClient: {}", e.getMessage(), e);
        }
    }

    private void notifyCharsInGroup(UserInfo userInfo) throws IOException {
        Integer groupId = userInfo.getGroupId();
        Integer userId = userInfo.getUserId();
        Integer currentHp = userInfo.getCurrentHp();
        Integer maxHp = userInfo.getMaxHp();
        for (Map.Entry<Socket, UserInfo> entry : healthData.entrySet()) {
            if (Objects.equals(entry.getValue().getGroupId(), groupId) && !Objects.equals(userId, entry.getValue().getUserId())) {
                DataOutputStream output = entry.getValue().getOutput();
                output.writeInt(userId);
                output.writeInt(currentHp);
                output.writeInt(maxHp);
            }
        }
    }

    public List<UserInfo> getHealthData() {
        return healthData.values().stream().toList();
    }
}
