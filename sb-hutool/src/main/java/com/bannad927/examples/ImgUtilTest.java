package com.bannad927.examples;

import cn.hutool.core.img.Img;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 图片工具
 * @author chengbb
 * @date 2020.6.19
 */
@Slf4j
public class ImgUtilTest {

    public static void main(String[] args) {
        //缩放图片
        /*ImgUtil.scale(FileUtil.file("d:/elastic.jpg"),FileUtil.file("d:/scale_result.jpg"),0.5f);*/

        //剪裁图片
        /*ImgUtil.cut(FileUtil.file("d:/elastic.jpg"),FileUtil.file("d:/cut_result.jpg"),new Rectangle(200, 200, 100, 100));*/

        //按照行列剪裁切片（将图片分为20行和20列）
        //ImgUtil.convert(FileUtil.file("d:/elastic.jpg"), FileUtil.file("d:/convert_result.jpg"));

        //彩色转为黑白
        //ImgUtil.gray(FileUtil.file("d:/elastic.jpg"),FileUtil.file("d:/gray_result.jpg"));

        //添加文字水印
        /*ImgUtil.pressText(FileUtil.file("d:/elastic.jpg"),FileUtil.file("d:/pressText_result.jpg"),
                "版权所有添加文字水印", Color.RED, //文字
                new Font("黑体", Font.BOLD, 100), //字体
                200, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                200, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );*/

        //添加图片水印
        /*ImgUtil.pressImage(FileUtil.file("d:/elastic.jpg"),FileUtil.file("d:/pressImage_result.jpg"),
                ImgUtil.read(FileUtil.file("d:/1.png")), //水印图片
                100, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                100, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.5f
        );*/

        //旋转图片
        /*try {
            Image image = ImgUtil.rotate(ImageIO.read(FileUtil.file("d:/elastic.jpg")), 180);
            ImgUtil.write(image, FileUtil.file("d:/rotate_elastic.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //水平翻转图片
        //ImgUtil.flip(FileUtil.file("d:/elastic.jpg"), FileUtil.file("d:/flip_elastic.jpg"));

        //图像切割
        //Img.from(FileUtil.file("d:/elastic.jpg")).cut(0, 0, 1000).write(FileUtil.file("d:/from_elastic.jpg"));

        //图片压缩
        Img.from(FileUtil.file("d:/elastic.jpg"))
                .setQuality(0.8)//压缩比率
                .write(FileUtil.file("d:/elastic_target.jpg"));

    }
}
