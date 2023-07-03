package com.application.javaapplication.tools.dosql;

import com.application.javaapplication.annotationCustomer.AnnotationUtil;
import com.application.javaapplication.annotationCustomer.CustomTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Component
public class RowMapperUtils
{
    @Autowired
    private AnnotationUtil annotationUtil;

    public <createBuilderElement> RowMapper<createBuilderElement> createCommonRowMapper(Class<createBuilderElement> element)
    {
        Map elementColumns = annotationUtil.customTableFields(element);

        return new RowMapper<createBuilderElement>() {
            @Override
            public createBuilderElement mapRow(ResultSet resultSet, int i) throws SQLException {

                createBuilderElement createBuilderElement = null;

                return createBuilderElement;
            }
        };
    }
}
