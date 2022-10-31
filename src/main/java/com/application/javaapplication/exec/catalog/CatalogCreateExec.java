package com.application.javaapplication.exec.catalog;

import com.application.javaapplication.entity.ProjectList;
import com.application.javaapplication.tools.dosql.SqlDoctrineExtends;
import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * fixme 生成目录文件 projectList
 */
@Component
public class CatalogCreateExec
{
    @Autowired
    private SqlDoctrineExtends EM;

    /**
     * 作用::创建并且验证新的项目
     * @param params
     * @throws Exception
     */
    public void GenerateNewProject(Map<String, String> params) throws Exception
    {
        checkProjectAttribute(params);
        insertNewProject(params);
    }

    /**
     * 作用::验证当前项目种类的数据是否完善
     * fixme 根据不同的种类做不同的错误预警
     * @param params
     * @throws Exception
     */
    private void checkProjectAttribute(Map<String, String> params) throws Exception
    {
        String attribute = params.get("attribute");

        // fixme 项目种类， 1 常规项目， 2 表格，3 单页
        switch (attribute) {
            case "1":
                // 已经做过验证，无需重复验证
                break;
            case "2":
                String copySourceName = params.get("copy_source_name");
                String copyNewName = params.get("copy_new_name");

                if (copySourceName == null || copySourceName.equals("")) {
                    throw new Exception("请选择需要复制的项目名");
                }
                if (copyNewName == null || copyNewName.equals("")) {
                    throw new Exception("请输入新的项目名");
                }
                break;
            case "3":
                // 暂未开放
                break;
            default:
                throw new Exception("无效的状态");
        }
    }

    /**
     * 作用::开始往数据库表插入新的数据
     * @param params
     * @throws Exception
     */
    private void insertNewProject(Map<String, String> params) throws Exception
    {
        ProjectList projectList = new ProjectList();

        DateTime currentTime = new DateTime();

        String ProjectCode = getProjectCode();

        projectList.setName(params.get("name"));
        projectList.setCode(ProjectCode);
        projectList.setAttribute(Integer.parseInt(params.get("attribute")));
        projectList.setDescription(params.get("description"));
        projectList.setNature(params.get("nature"));
        projectList.setCopy_new_name(params.get("copy_new_name"));
        projectList.setCopy_source_name(params.get("copy_source_name"));
        projectList.setCreated(currentTime);
        projectList.setModified(currentTime);

        if (!params.get("source").equals("")) {
            projectList.setSource(Integer.parseInt(params.get("source")));
        }
        EM.persist(projectList);
        EM.flush();
    }

    private String getProjectCode()
    {
        return "P"+RandomStringUtils.randomNumeric(16).toString();
    }
}
