package com.chuchen.chuchenaicodebackend.core;

import com.chuchen.chuchenaicodebackend.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;

/**
 * @author chuchen
 *
 */
@SpringBootTest
class AiCodeGeneratorFacadeTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateAndSaveCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("生成一个登陆页面", CodeGenTypeEnum.HTML);
        Assertions.assertNotNull(file);
    }

    @Test
    void generateAndSaveCodeStream() {
        Flux<String> flux = aiCodeGeneratorFacade.generateAndSaveCodeStream("生成一个登陆页面，不超过五十行代码", CodeGenTypeEnum.MULTI_FILE);
        List<String> result = flux.collectList().block();
        Assertions.assertNotNull(result);
        String join = String.join("", result);
        Assertions.assertNotNull(join);
    }
}