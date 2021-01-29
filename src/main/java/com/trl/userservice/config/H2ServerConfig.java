package com.trl.userservice.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
@ConditionalOnProperty(value = "h2.server.enabled")
public class H2ServerConfig {

    @Value("${h2.server.tcp.port}")
    private String serverPort;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryDatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", serverPort);
    }
}
