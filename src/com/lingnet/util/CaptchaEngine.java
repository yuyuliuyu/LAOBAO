package com.lingnet.util;

import java.awt.Color;
import java.awt.Font;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class CaptchaEngine extends ListImageCaptchaEngine {

    public static final String IMAGE_CAPTCHA_KEY = "imageCaptcha";// ImageCaptcha对象存放在Session中的key
    public static final String CAPTCHA_PARAMETER_NAME = "captcha";// 验证码输入表单名称
    public static final String CAPTCHA_IMAGE_URL = "/captcha.jpeg";// 验证码图片URL
    private static final Integer MIN_WORD_LENGTH = 4;// 验证码最小长度
    private static final Integer MAX_WORD_LENGTH = 4;// 验证码最大长度
    private static final Integer IMAGE_HEIGHT = 28;// 验证码图片高度
    private static final Integer IMAGE_WIDTH = 80;// 验证码图片宽度
    private static final Integer MIN_FONT_SIZE = 16;// 验证码最小字体
    private static final Integer MAX_FONT_SIZE = 16;// 验证码最大字体
    private static final String RANDOM_WORD = "ABCDEFGHKMNOPQRSTUVWXY";// 随机字符
    // 随机背景图片路径,该路径是从\WEB-INF\classes\开始搜寻
    // (1)背景资源位于源文件夹中：/com/test/captcha
    // (2)背景资源位于WebRoot中：../../images/captcha
    
    private static final String IMAGE_PATH ="../../template/system/images/captcha";
    // 验证码随机字体
    private static final Font[] RANDOM_FONT = new Font[] {
            new Font("nyala", Font.BOLD, MIN_FONT_SIZE),
            new Font("Arial", Font.BOLD, MIN_FONT_SIZE),
            new Font("Bell MT", Font.BOLD, MIN_FONT_SIZE),
            new Font("Credit valley", Font.BOLD, MIN_FONT_SIZE),
            new Font("Impact", Font.BOLD, MIN_FONT_SIZE) };

    // 验证码随机颜色
    private static final Color[] RANDOM_COLOR = new Color[] {
            new Color(255, 255, 255), new Color(255, 220, 220),
            new Color(220, 255, 255), new Color(220, 220, 255),
            new Color(255, 255, 220), new Color(220, 255, 220) };

    // 生成验证码
    @Override
    protected void buildInitialFactories() {
        RandomListColorGenerator randomListColorGenerator = new RandomListColorGenerator(
                RANDOM_COLOR);
        BackgroundGenerator backgroundGenerator = new FileReaderRandomBackgroundGenerator(
                IMAGE_WIDTH, IMAGE_HEIGHT,IMAGE_PATH);

        WordGenerator wordGenerator = new RandomWordGenerator(RANDOM_WORD);

        FontGenerator fontGenerator = new RandomFontGenerator(MIN_FONT_SIZE,
                MAX_FONT_SIZE, RANDOM_FONT);

        TextDecorator[] textDecorator = new TextDecorator[] {};

        TextPaster textPaster = new DecoratedRandomTextPaster(MIN_WORD_LENGTH,
                MAX_WORD_LENGTH, randomListColorGenerator, textDecorator);

        WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
                backgroundGenerator, textPaster);

        addFactory(new GimpyFactory(wordGenerator, wordToImage));
    }

}