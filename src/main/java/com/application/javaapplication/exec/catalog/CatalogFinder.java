package com.application.javaapplication.exec.catalog;

import com.application.javaapplication.exec.BaseFinder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CatalogFinder extends BaseFinder
{
    /**
     * 作用:: 查询项目目录列表，并且确认条件
     * @return
     */
    public Map<String, String> finder()
    {
        return new HashMap<>();
    }

    /**
     * 作用:: 个性化当前单个查询
     * @return
     */
    private String findOneByCondition()
    {
        return "";
    }
}
