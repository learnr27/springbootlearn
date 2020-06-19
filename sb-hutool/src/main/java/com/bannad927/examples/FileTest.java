package com.bannad927.examples;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.Tailer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class FileTest {

    public static void main(String[] args) {
        //1.文件读取-FileReader
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        /*FileReader fileReader = new FileReader("D:/test1.txt");
        String result = fileReader.readString();
        log.info("result:{}", result);*/

        //2.文件写入-FileWriter
        //写入文件分为追加模式和覆盖模式两类，追加模式可以用append方法，覆盖模式可以用write方法，同时也提供了一个write方法，第二个参数是可选覆盖模式。
        /*FileWriter writer = new FileWriter("D:/test1.txt");
        writer.append("追加模式可以用append方法");
        writer.write("文件写入-FileWriter", true);*/

        //3.文件追加-FileAppender 类似于Linux下的tail -f命令
        /**顾名思义，FileAppender类表示文件追加器。此对象持有一个一个文件，在内存中积累一定量的数据后统一追加到文件，此类只有在写入文件时打开文件，并在写入结束后关闭之。因此此类不需要关闭。
         在调用append方法后会缓存于内存，只有超过容量后才会一次性写入文件，因此内存中随时有剩余未写入文件的内容，在最后必须调用flush方法将剩余内容刷入文件。
         也就是说，这是一个支持缓存的文件内容追加器。此类主要用于类似于日志写出这类需求所用。*/
       /* FileAppender appender = new FileAppender(new File("D:/test1.txt"), 16, true);
        appender.append("123");
        appender.append("abc");
        appender.append("xyz");
        appender.flush();
        appender.toString();*/
        //4.文件跟随-Tailer
        //Tailer.CONSOLE_HANDLER表示文件新增内容默认输出到控制台。
        Tailer tailer = new Tailer(FileUtil.file("D:/test1.txt"), Tailer.CONSOLE_HANDLER, 2);
        tailer.start();


    }
}
