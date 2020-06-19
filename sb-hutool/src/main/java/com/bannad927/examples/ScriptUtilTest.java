package com.bannad927.examples;

import cn.hutool.script.ScriptRuntimeException;
import cn.hutool.script.ScriptUtil;
import lombok.extern.slf4j.Slf4j;

import javax.script.CompiledScript;
import javax.script.ScriptException;

/**
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class ScriptUtilTest {
    public static void main(String[] args) {
        ScriptUtil.eval("print('Script test!');");
        ScriptUtil.eval("print('alert(0)');");

        CompiledScript script = ScriptUtil.compile("print('Script test!');");
        try {
            Object obj = script.eval();
            log.info("obj:{}", obj);
        } catch (ScriptException e) {
            throw new ScriptRuntimeException(e);
        }
    }
}
