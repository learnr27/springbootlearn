package com.bannad927.examples;

import cn.hutool.setting.Setting;
import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置文件可以放在任意位置，具体Setting类如何寻在在构造方法中提供了多种读取方式，具体稍后介绍。现在说下配置文件的具体格式 Setting配置文件类似于Properties文件，规则如下：
 * <p>
 * 注释用#开头表示，只支持单行注释，空行和无法正常被识别的键值对也会被忽略，可作为注释，但是建议显式指定注释。同时在value之后不允许有注释，会被当作value的一部分。
 * 键值对使用key = value 表示，key和value在读取时会trim掉空格，所以不用担心空格。
 * 分组为中括号括起来的内容（例如配置文件中的[demo]），中括号以下的行都为此分组的内容，无分组相当于空字符分组，即[]。若某个key是name，分组是group，加上分组后的key相当于group.name。
 * 支持变量，默认变量命名为 ${变量名}，变量只能识别读入行的变量，例如第6行的变量在第三行无法读取，例如配置文件中的${driver}会被替换为com.mysql.jdbc.Driver，为了性能，Setting创建的时候构造方法会指定是否开启变量替换，默认不开启。
 *
 * @author chengbb
 * @date 2020.6.18
 */
@Slf4j
public class SettingTest {

    public static void main(String[] args) {
        //Setting初始化
        //读取classpath下的XXX.setting，不使用变量
        //Setting setting = new Setting("XXX.setting");
        //读取classpath下的config目录下的XXX.setting，不使用变量
        Setting setting = new Setting("config/example.setting");
        //读取绝对路径文件/home/looly/XXX.setting（没有就创建，关于touc请查阅FileUtil）
        //第二个参数为自定义的编码，请保持与Setting文件的编码一致
        //第三个参数为是否使用变量，如果为true，则配置文件中的每个key都以被之后的条目中的value引用形式为 ${key}
        // setting = new Setting(FileUtil.touc("/home/looly/XXX.setting"), CharsetUtil.CHARSET_UTF_8, true);
        //读取与SettingDemo.class文件同包下的XXX.setting
        // setting = new Setting("XXX.setting", SettingDemo.class,CharsetUtil.CHARSET_UTF_8, true);

        //Setting读取配置参数
        log.info("获取key为name的值:{}", setting.getStr("name"));
        log.info("获取分组为group下key为name的值:{}", setting.getByGroup("name", "group1"));
        log.info("当获取的值为空（null或者空白字符时，包括多个空格），返回默认值:{}", setting.getStr("name", "默认值"));
        log.info("完整的带有key、分组和默认值的获得值得方法:{}", setting.getStr("name", "group1", "默认值"));
        log.info("如果想获得其它类型的值，可以调用相应的getXXX方法，参数相似:{}", setting.getWithLog("name"));
        log.info("有时候需要在key对应value不存在的时候（没有这项设置的时候）告知户，故有此方法打印一个debug日志:{}", setting.getByGroupWithLog("name", "group1"));
        log.info("获取分组下所有配置键值对，组成新的Setting:{}", setting.getSetting("group1"));

        //重新加载配置和保存配置
       /* //重新读取配置文件
        setting.reload();
        //在配置文件变更时自动加载
        setting.autoLoad(true);
        //当通过代码加入新的键值对的时候，调用store会保存到文件，但是会盖原来的文件，并丢失注释
        setting.set("name1", "value");
        setting.store("/home/looly/XXX.setting");
        //获得所有分组名
        setting.getGroups();
        //将key-value映射为对象，原理是原理是调用对象对应的setXX方法
        UserVO userVo = new UserVo();
        setting.toBean(userVo);
        //设定变量名的正则表达式。
        //Setting的变量替换是通过正则查找替换的，如果Setting中的变量名其他冲突，可以改变变量的定义方式
        //整个正则匹配变量名，分组1匹配key的名字
        setting.setVarRegex("\\$\\{(.*?)\\}");*/

        //Properties扩展-Props
        /**
         对于Properties的广泛使用使我也无能为力，有时候遇到Properties文件又想方便的读写也不容易，
         于是对Properties做了简单的封装，提供了方便的构造方法（与Setting一致），
         并提供了与Setting一致的getXXX方法来扩展Properties类，Props类继承自Properties，所以可以兼容Properties类。
         */

        Props props = new Props("config/test.properties");
        log.info("user:{}", props.getProperty("user"));
        log.info("driver:{}", props.getStr("driver"));
    }




}
