package com.chuchen.chuchenaicodebackend.ai.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

/**
 * @author chuchen
 * @description 单文件代码生成结果
 */
@Description("生成 HTML 代码文件的结果")
@Data
public class HtmlCodeResult {

    /**
     * html 代码
     */
    @Description("HTML 代码")
    private String htmlCode;

    /**
     * 描述
     */
    @Description("描述")
    private String description;
}
