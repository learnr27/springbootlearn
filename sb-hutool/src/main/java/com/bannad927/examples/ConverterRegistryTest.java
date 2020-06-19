package com.bannad927.examples;

import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

/**
 * 自定义类型转换
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class ConverterRegistryTest {

    public static void main(String[] args) {
        /*int a = 3423;
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        String result = converterRegistry.convert(String.class, a);
        Assert.assertEquals("34233", result);*/

        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        //此处做为示例自定义String转换，因为Hutool中已经提供String转换，请尽量不要替换
        //替换可能引发关联转换异常（例如覆盖String转换会影响全局）
        converterRegistry.putCustom(String.class, CustomConverter.class);

        int a = 454553;
        String result = converterRegistry.convert(String.class, a);
        Assert.assertEquals("Custom: 454553", result);
    }

    public static class CustomConverter implements Converter<String> {

        @Override
        public String convert(Object value, String defaultValue) throws IllegalArgumentException {
            return "Custom: " + value.toString();
        }
    }
}
