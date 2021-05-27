package com.application.javaapplication.login.utils;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.application.javaapplication.tools.model.toolServiceModel;

import java.security.MessageDigest;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 存储公用工具的方法
 * 比如生成 token、session、 accessKey
 * 不涉及数据的部分
 */
@Component
public class CommonUtils
{
    @Autowired
    private toolServiceModel toolServiceModel;

    @Autowired
    private ApplicationContext applicationContext; // bean/ containBean/ getBean

    private String SignMethod = "md5";

    /**
     * generate token
     * @param AdminName String
     * @param OwnerCode String
     * @return String
     */
    public String generateToken(String AdminName, String OwnerCode, String Timezone) throws Exception
    {
        Map<String, String> list = new HashMap<>();
        list.put("AdminName", AdminName);
        list.put("OwnerCode", OwnerCode);
        list.put("Timetamp", Timezone);
        list.put("AppKey", toolServiceModel.SystemKey);
        list.put("AppVersion", String.valueOf(toolServiceModel.SystemVersion));
        return sign(list);
    }

    /**
     * Map HasMap Sort Mode
     * sign secret verify
     * @return String
     */
    private String sign( Map<String, String> map ) throws Exception
    {
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());

        Collections.sort(
                list,
                new Comparator<Map.Entry<String,String>>() {
                    @Override
                    public int compare(Map.Entry<String, String> object1, Map.Entry<String, String> object2) {
                        // ASC SORT [ object 1 => object 2 ]
                        return object1.getValue().compareTo(object2.getValue());
                    }
                });

        String line = toolServiceModel.SystemSecret + "&";

        for( Map.Entry<String, String> mapping : list ) {
            line += mapping.getKey() + "=" + mapping.getValue() + "&";
        }

        line += toolServiceModel.SystemSecret;

        String Sign = "";

        Sign = Md5Sign(line);

        return Sign;
    }

    /**
     *  md5 签名
     * @param line String
     * @return String
     * @throws Exception
     */
    public String Md5Sign( String line ) throws Exception
    {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char str[];
        try {
            byte[] btInput = line.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            str = new char[md.length * 2];

            int k = 0;

            for (int i = 0; i < md.length; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new String(str);
    }

    public String getCurrentTime() throws Exception
    {
        String CurrentTime = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CurrentTime = dateFormat.format(new Date());

        return CurrentTime;
    }

    public Date getDateTime(String standardTime ) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式时间对象
        Date parseTime = sdf.parse(standardTime);
        return parseTime;
    }

    public String getStringTime(Date CurrentTime)
    {
        return "";
    }
}
