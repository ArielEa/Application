package com.application.javaapplication.genernal.controller;

import com.application.javaapplication.conttroller.BaseController;
import com.application.javaapplication.entity.Wms;
import com.application.javaapplication.operation.Wms.WmsOperationObject;
import com.application.javaapplication.tools.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.application.javaapplication.tools.model.toolServiceModel;
import com.application.javaapplication.login.enums.loginEnums;

@Controller
@RequestMapping(value = "/wms")
@CrossOrigin
@ResponseBody
public class WmsController extends BaseController
{
    @Autowired
    private toolServiceModel toolServiceModel;

    @Autowired
    private WmsOperationObject wmsOperationObject;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET )
    public <T> List<T> WmsList(HttpServletRequest request)
    {
        List<Wms> wms = null;
        try {
            wms = wmsOperationObject.list();
        }catch (Exception e) {
            System.err.println( e.getMessage() );
        }

        String  message = "";
        int[] messageInfo;
//        try {
//            throw new Exception("Test Exception message");
//        } catch (Exception e) {
//            e.printStackTrace();
//            message = e.getMessage();
//            messageInfo = new int[]{1, 2, 3, 4}; // 往里面塞你要处理的错误数据， 然后
//        }
//        System.error.println( "获取secret : " + toolServiceModel.getSystemSecret() );

        System.out.println(wms);

        return (List<T>) wms;
    }

    @ApiOperation(value = "GET BODY更新数据")
    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
    public ResponseEntity<ModelMap> modelResource(
            @ApiParam(value="用户",required=true) @ModelAttribute Result result,
            @ApiParam(name = "id", value = "编号", required = true) @PathVariable String id
    )
    {
        System.out.println(result);
        ModelMap modelMap = new ModelMap();
        modelMap.put("status", HttpStatus.OK.value());
        modelMap.put("timestamps",System.currentTimeMillis());
        modelMap.put("msg", HttpStatus.OK.getReasonPhrase());
        modelMap.put("user", result);
        modelMap.put("apiversion", 2);
        return ResponseEntity.status(HttpStatus.OK).body(modelMap);
    }
}
