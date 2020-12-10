package com.bannad927.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author chengbb
 * @date 2020.7.15
 */
@Data
@Component
@PropertySource({"sftp.properties"})
public class SftpProperties {

    @Value("${sftp.client.host}")
    private String host;

    @Value("${sftp.client.port}")
    private Integer port;

    @Value("${sftp.client.protocol}")
    private String protocol;

    @Value("${sftp.client.username}")
    private String username;

    @Value("${sftp.client.password}")
    private String password;

    @Value("${sftp.client.root}")
    private String root;

    @Value("${sftp.client.privateKey}")
    private String privateKey;

    @Value("${sftp.client.passphrase}")
    private String passphrase;

    @Value("${sftp.client.sessionStrictHostKeyChecking}")
    private String sessionStrictHostKeyChecking;

    @Value("${sftp.client.sessionConnectTimeout}")
    private Integer sessionConnectTimeout;

    @Value("${sftp.client.channelConnectedTimeout}")
    private Integer channelConnectedTimeout;

}
