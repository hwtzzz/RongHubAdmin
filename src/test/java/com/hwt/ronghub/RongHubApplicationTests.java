package com.hwt.ronghub;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.sun.deploy.net.HttpResponse;
import com.sun.deploy.net.HttpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RongHubApplicationTests {

    /**
     * 堆成加密解密
     */
    @Test
    void AESTest() {
        String content = "123456";

        // 创建AES实例，使用默认密钥
        SymmetricCrypto aes = new SymmetricCrypto("AES");

        // 加密
        String encryptStr = aes.encryptHex(content);
        System.out.println(encryptStr);

        // 解密
        String decryptStr = aes.decryptStr(encryptStr);
        System.out.println(decryptStr);

    }

}
