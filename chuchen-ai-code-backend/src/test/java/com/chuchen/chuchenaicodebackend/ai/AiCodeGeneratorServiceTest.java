package com.chuchen.chuchenaicodebackend.ai;

import com.chuchen.chuchenaicodebackend.ai.model.HtmlCodeResult;
import com.chuchen.chuchenaicodebackend.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chuchen
 *
 */
@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        HtmlCodeResult htmlCode = aiCodeGeneratorService.generateHtmlCode("做一个程序员小王的博客，不超过 20 行");
        Assertions.assertNotNull(htmlCode);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult generateMultiFileCode = aiCodeGeneratorService.generateMultiFileCode("做一个程序员小王的留言板，不超过 50 行");
        Assertions.assertNotNull(generateMultiFileCode);
    }
}