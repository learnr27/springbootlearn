package com.bannad927.examples;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * IO工具类
 * IO工具类的存在主要针对InputStream、OutputStream、Reader、Writer封装简化，并对NIO相关操作做封装简化
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class IoUtilTest {

    public static void main(String[] args) {
        //1.拷贝
       /* BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
        BufferedOutputStream out = FileUtil.getOutputStream("d:/test1.txt");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        log.info("copySize:{}", copySize);*/

        //2.Stream转Reader、Writer
        /**IoUtil.getReader：将InputStream转为BufferedReader用于读取字符流，它是部分readXXX方法的基础。
         IoUtil.getWriter：将OutputStream转为OutputStreamWriter用于写入字符流，它是部分writeXXX的基础*/

        //3.读取流中的内容
        /**BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
         FastByteArrayOutputStream read = IoUtil.read(in);
         read方法有诸多的重载方法，根据参数不同，可以读取不同对象中的内容，这包括：
         InputStream
         Reader
         FileChannel
         这三个重载大部分返回String字符串，为字符流读取提供极大便利。

         readXXX方法主要针对返回值做一些处理，例如：

         readBytes 返回byte数组（读取图片等）
         readHex 读取16进制字符串
         readObj 读取序列化对象（反序列化）
         readLines 按行读取
         toStream方法则是将某些对象转换为流对象，便于在某些情况下操作：

         String 转换为ByteArrayInputStream
         File 转换为FileInputStream*/

        //4.写入到流
        /**IoUtil.write方法有两个重载方法，一个直接调用OutputStream.write方法，另一个用于将对象转换为字符串（调用toString方法），然后写入到流中。
         IoUtil.writeObjects 用于将可序列化对象序列化后写入到流中。
         write方法并没有提供writeXXX，需要自己转换为String或byte[]。
         */
        //5.关闭
        IoUtil.close(null);

    }
}
