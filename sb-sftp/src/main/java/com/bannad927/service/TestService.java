package com.bannad927.service;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.bannad927.entity.RefundInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author chengbb
 * @date 2020.7.15
 */
@Service
@Slf4j
public class TestService {


    /**
     * 行 Row
     * 列 Column
     */
    @PostConstruct
    public void refundInfo() {
        FileReader fileReader = new FileReader("C:\\Users\\EDZ\\Desktop\\sftp\\TPMX\\XMYSHW_refundInfo_20200703.txt");
        String result = fileReader.readString();

        if (!StrUtil.contains(result, "今日无退票信息")) {
            String[] rows = result.split("\r\r\n");

            int rowsLength = rows.length;
            for (int i = 1; i < rowsLength; i++) {
                String[] columns = rows[i].split("\\|");
                RefundInfo refundInfo = new RefundInfo();
                refundInfo.setGroupNumber(columns[0]);
                refundInfo.setEnterpriseUserNumber(columns[1]);
                refundInfo.setMerchantName(columns[2]);
                refundInfo.setMerchantInstructionId(columns[3]);
                refundInfo.setOrderNumber(columns[4]);
                refundInfo.setOrderDate(columns[5]);
                refundInfo.setRefundAmount(columns[6]);
                refundInfo.setRefundDate(columns[7]);
                refundInfo.setBankAccountNumber(columns[8]);
                refundInfo.setBankAccountName(columns[9]);
                refundInfo.setRefundBankNumber(columns[10]);
                refundInfo.setRefundBankName(columns[11]);
                refundInfo.setReasonForRefund(columns[12]);
                refundInfo.setRefundType(columns[13]);
                log.info("refundInfo:{}", JSONUtil.toJsonStr(refundInfo));
            }

        }


        log.info("result:{}", result);
    }
}
