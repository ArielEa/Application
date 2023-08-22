package com.application.javaapplication.operation.Wms;

import com.application.javaapplication.entity.Wms;
import com.application.javaapplication.entity.tools.Column;
import com.application.javaapplication.tools.Contains;
import com.application.javaapplication.tools.dosql.RowMapperUtils;
import com.application.javaapplication.tools.dosql.SqlDoctrineExtends;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Configuration
public class WmsOperationObject
{
    @Autowired
    private SqlDoctrineExtends EM;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RowMapperUtils rowMapperUtils;

    private final Logger logger = LogManager.getLogger(WmsOperationObject.class);

    public <T> List<T> list ()
            throws Exception
    {
        List<Wms> wms = null;

        String Alias = "wms";

        Column list  = (Column) applicationContext.getBean("custom_table_fields");

        Map<Integer, String> SelectColumn = list.customTableFields( Wms.class, "wms" );

        MyRowMapper();

        try {
            wms = EM.getTableName( Wms.class ).createQueryBuilder( Alias )
//                    .select( SelectColumn )
                    .where(Alias + ".wms_bn", Contains.EM_EQ, "wms_bn")
                    .setParameter("wms_bn", "self-wms")
                    .orderBy(Alias + ".id", "DESC")
                    .getQuery().getResult();

//            EM.getTableName( Wms.class ).createQueryBuilder( Alias ).select( SelectColumn );

//            wms = EM.getTableName( Wms.class ).createQueryBuilder( Alias ).select( SelectColumn ).getQuery().getResult(MyRowMapper());

        } catch ( Exception sqlException ) {
            logger.info( sqlException.getMessage() );
        }

        System.out.println(wms);

        return (List<T>) wms;
    }

    public RowMapper<Wms> MyRowMapper()
    {
        return new RowMapper<Wms>() {
            Wms wms = null;
            @Override
            public Wms mapRow(ResultSet resultSet, int i) throws SQLException
            {
                wms = new Wms()
                        .setId( resultSet.getInt( "id" ) )
                        .setWmsName( resultSet.getString("wms_name") )
                        .setWmsBn( resultSet.getString("wms_bn") )
                        .setWmsAngle( resultSet.getInt("wms_angle"))
                        .setWmsAttr( resultSet.getInt( "wms_attr" ) )
                        .setWmsType( resultSet.getInt( "wms_type" ) )
                        .setCreated( resultSet.getTimestamp("created") )
                        .setAccountCode( resultSet.getString("account_code") );
                return wms;
            }
        };
    }
}
