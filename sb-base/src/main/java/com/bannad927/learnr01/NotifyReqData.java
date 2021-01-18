package com.bannad927.learnr01;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class NotifyReqData implements Serializable {
    private static final long serialVersionUID = -1;

    public String templateCode;

    public Map<String, Object> parameters;
}
