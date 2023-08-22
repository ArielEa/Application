package com.application.javaapplication.operation.Warehouse;

import com.application.javaapplication.conttroller.DemoController;
import com.application.javaapplication.entity.Warehouse;
import com.application.javaapplication.entity.tools.Column;
import com.application.javaapplication.tools.dosql.SqlDoctrineExtends;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WarehouseOperation
{
    @Autowired
    private SqlDoctrineExtends EM;

    @Autowired
    private ApplicationContext applicationContext;

    private Logger logger = LogManager.getLogger(DemoController.class);

    public <k, v> Map<k, v> list()
    {
        List<Warehouse> warehouse = null;

        String Alias = "warehouse";

        Column list  = (Column) applicationContext.getBean("custom_table_fields");

        Map<Integer, String> SelectColumn = list.customTableFields( Warehouse.class, Alias );

        System.out.println( SelectColumn );

        try {
            EM.getTableName( Warehouse.class )
                    .createQueryBuilder( Alias )
                    .select( SelectColumn );

            warehouse = EM.getQuery().getResult();


        }catch ( SQLException sqlException ) {
            logger.info("数据库错误" + sqlException.getMessage());
        } catch (Exception e) {
            logger.info( "异常错误:" + e.getMessage());
        }

//        System.out.println(warehouse);

        return (Map<k, v>) new HashMap<>();
    }

    public RowMapper<Warehouse> MyRowMapper()
    {
        return new RowMapper<Warehouse>() {
            Warehouse warehouse = null;
            @Override
            public Warehouse mapRow(ResultSet resultSet, int i) throws SQLException
            {
                warehouse = new Warehouse()
                        .setId( resultSet.getInt( "id" ) )
                        .setWarehouseName( resultSet.getString("warehouse_name") )
                        .setWarehouseBn( resultSet.getString("warehouse_bn") )
                        .setWarehouseOwner( resultSet.getString("warehouse_owner") );
                return warehouse;
            }
        };
    }
}
