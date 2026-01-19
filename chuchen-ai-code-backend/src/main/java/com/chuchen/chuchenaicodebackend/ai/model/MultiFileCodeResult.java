package com.chuchen.chuchenaicodebackend.ai.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * @author chuchen
 * @description 多文件代码生成结果
 */
@Description("生成多个代码文件文件的结果")
@Data
public class MultiFileCodeResult {

    /**
     * html 代码
     */
    @Description("HTML 代码")
    private String htmlCode;

    /**
     * css 代码
     */
    @Description("CSS 代码")
    private String cssCode;

    /**
     * js 代码
     */
    @Description("JS 代码")
    private String jsCode;

    /**
     * 描述
     */
    @Description("描述")
    private String description;
}
