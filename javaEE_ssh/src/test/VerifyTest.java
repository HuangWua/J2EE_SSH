package test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;

/**
 * @ProjectName: javaEE_ssh
 * @Package: test
 * @ClassName: VerifyTest
 * @Author: duan
 * @Description: 验证码测试
 * @Date: 2019-05-30 19:01
 * @Version: 1.0
 */

public class VerifyTest {

    public static void main(String[] args) {
        ShearCaptcha shearCaptcha= CaptchaUtil.createShearCaptcha(200,100);
        shearCaptcha.write("G:/test/verify.png");
        System.out.println(shearCaptcha.getCode());
        System.out.println(shearCaptcha.verify("12345"));
    }

}
