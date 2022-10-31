package com.application.javaapplication.tools.checkauth;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Component;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口请求参数验证
 * @author Ariel
 * @created 2022-10-15 03:00:00
 */
@Component
public class RequestParams
{
    // fixme request请求参数，需要从源头设置
    private HttpServletRequest request = null;

    private Map<String, String> requestParamsData;

    /**
     * fixme 检查当前String数组串中是否存在值, 用于指定参数的「POST、GET、PUT」请求
     * @param request - Object HttpServletRequest
     * @param params - String [] arrays
     */
    public void requestCheckauth(HttpServletRequest request, String ...params) throws Exception
    {
        this.request = request;
        this.requestParamsFinder(params);
    }

    /**
     * fixme 验证当前参数是否存在, 根据指定的String数组串来判断当前 Key 是否有值
     * @param params - String [] arrays
     */
    public void requestParamsFinder(String [] params) throws Exception
    {
        // 遍历数组，验证当前参数是否有值存在
        for (String param : params) {
            String currentMsg = "";
            currentMsg = request.getParameter(param);
            if (currentMsg == null || currentMsg.equals("")) {
                throw new Exception("参数" + param + "丢失");
            }
        }
    }

    /**
     * 方法: request.getParameterMap()
     * fixme 参数中包含数组，使用jsonArray进行转化，后续要优化
     *       前端传输的方式是 formData
     * @return Map<String, String>
     */
    public Map<String, String> getRequestParams()
    {
        Enumeration<String> enu=request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> arraylist = new HashMap<>();

        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            boolean containsChar = paraName.contains(String.valueOf("["));
            if (containsChar) {
                String newName = paraName.substring(0,paraName.indexOf("["));
                String intStr = paraName.substring(paraName.indexOf("[")+1);
                String prefixData = intStr.substring(0, intStr.indexOf("]"));
                boolean containsKey = params.containsKey(newName);

                String insertData = "";

                Integer columnInt = Integer.parseInt(prefixData);

                if (containsKey) {
                    String nextColumnData = params.get(newName);
                    JSONArray disposeColumnData = (JSONArray) JSONArray.parse(nextColumnData);
                    disposeColumnData.add(request.getParameter(paraName));
                    JSONArray newColumns = (JSONArray) JSONArray.toJSON(disposeColumnData);
                    insertData = newColumns.toString();
                } else {
                    JSONArray arrayColumn = new JSONArray();
                    arrayColumn.add(request.getParameter(paraName));
                    JSONArray jsonArray = (JSONArray) JSONArray.toJSON(arrayColumn);
                    insertData = jsonArray.toString();
                }
                params.put(newName, insertData);
            } else {
                params.put(paraName, request.getParameter(paraName));
            }
        }

//        for (Map.Entry<String, String> entry: params.entrySet()) {
//            System.out.println( entry.getKey() + ":" + entry.getValue() );
//        }
        this.requestParamsData = params;
        return params;
    }

    /**
     * 方法:: request.getParameterMap() 进行全局搜查
     * fixme:: 后续重新编写
     * @return Map<String, String>
     */
    public Map<String, String> getRequestParamsMap() throws Exception
    {
        Map<String, String[]> map=request.getParameterMap();
        Set<Map.Entry<String, String[]>> keSet=map.entrySet();

        for(Iterator<Map.Entry<String, String[]>> itr = keSet.iterator(); itr.hasNext();)
        {
            Map.Entry me=(Map.Entry)itr.next();
            Object ok=me.getKey();
            Object ov=me.getValue();
            String[] value=new String[1];
            if(ov instanceof String[]){
                value=(String[])ov;
            }else{
                value[0]=ov.toString();
            }

            for(int k=0;k<value.length;k++){
//                System.out.println(ok+"="+value[k]);
            }
        }
        return new HashMap<>();
    }
}
