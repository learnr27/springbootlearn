package com.bannad927.learnr02;

import lombok.Data;

/**
 * @author: chengbinbin
 * @date: 2021.1.19
 **/
@Data
public class DownloadCenter {

    /**
     * 下载内容
     */
    private String downloadContent;

    /**
     * 下载参数
     */
    private String downloadParameters;

    /**
     * 类型 1:积分明细下载 2:订单明细下载 ...
     */
    private Integer typesOf;
}
