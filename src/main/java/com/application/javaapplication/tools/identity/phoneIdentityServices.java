package com.application.javaapplication.tools.identity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("phone.identity.services")
public class phoneIdentityServices
{
    // 从文本中获取手机号
    @Transactional
    public String getPhoneNumberFromText( String sParam ) throws Exception
    {
//        String phoneNumber = getPhone( "这里是手机号 13916485431, 从中间提取出来" );

        if(sParam.length()<=0) {
            return "没有";
        }
        Pattern pattern = Pattern.compile("(1|861)(3|5|8)\\d{9}$*");

        Matcher matcher = pattern.matcher(sParam);

        StringBuffer bf = new StringBuffer();

        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();

        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }
}
