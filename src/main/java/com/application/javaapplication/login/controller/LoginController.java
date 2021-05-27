package com.application.javaapplication.login.controller;

import com.application.javaapplication.Utils.UtilsController;
import com.application.javaapplication.conttroller.VerifyController;
import com.application.javaapplication.entity.enums.UserEnums;
import com.application.javaapplication.login.utils.CommonUtils;
import com.application.javaapplication.login.utils.LoginJdbcUtils;
import com.application.javaapplication.login.utils.UpdateLoginInfoUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

import com.application.javaapplication.login.enums.loginEnums;
import com.application.javaapplication.entity.User;

@Controller
@RequestMapping("/login")
@CrossOrigin
public class LoginController extends VerifyController
{
    // 需要操作数据库存储token
    // admin_user - token, loin_in_time, login_out_time
    // 手机，三方登录，我没权限签约

    @Autowired
    private LoginJdbcUtils loginJdbcUtils;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UtilsController utilsController;

    @GetMapping("/login_in")
    @ResponseBody
    @CrossOrigin
    @ExceptionHandler
    public Map<String, User> loginIn(HttpServletRequest request, HttpServletResponse response ) throws Exception
    {
        Map<String, User> list = new HashMap<String, User>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.equals("") || StringUtils.isEmpty(username)) {
            return utilsController.doErrorHandle( loginEnums.LOGIN_EMPTY_ERROR.getExplain(), loginEnums.LOGIN_EMPTY_ERROR.getCode() );
        }
        if (password == null || password.equals("") || StringUtils.isEmpty(password)) {
            return utilsController.doErrorHandle( loginEnums.LOGIN_EMPTY_PASSWORD.getExplain(), loginEnums.LOGIN_EMPTY_ERROR.getCode() );
        }
        username = username.trim();
        password = password.trim();

        try {
            this.verifyUserSecret( username );
        } catch (Exception e) {
            return utilsController.doErrorHandle(loginEnums.VERIFY_SECRET_INVALID.getExplain(), loginEnums.VERIFY_SECRET_INVALID.getCode());
        }

        String passwordEncodeStr = commonUtils.Md5Sign(URLEncoder.encode(password, "UTF-8"));

        List<User> adminUserInfo = loginJdbcUtils.getLoginInfo(username);

        if (adminUserInfo.size() == 0) {
            return utilsController.doErrorHandle( loginEnums.LOGIN_INVALID_ERROR.getExplain(), loginEnums.LOGIN_INVALID_ERROR.getCode());
        }
        if (adminUserInfo.size() > 1) {
            return utilsController.doErrorHandle( loginEnums.ADMIN_DATA_NUMS_ERROR.getExplain(), loginEnums.ADMIN_DATA_NUMS_ERROR.getCode() );
        }
        User adminSingleInfo = adminUserInfo.get(0);

        if (!passwordEncodeStr.equals(adminSingleInfo.getPassword())) {
            return utilsController.doErrorHandle(loginEnums.LOGIN_ERROR_PASSWORD.getExplain(), loginEnums.LOGIN_ERROR_PASSWORD.getCode());
        }

        String CurrentTime = commonUtils.getCurrentTime();
        Date actionTime = commonUtils.getDateTime(CurrentTime);

        String token = commonUtils.generateToken(adminSingleInfo.getAdminName(), adminSingleInfo.getOwnerCode(), CurrentTime);
        UpdateLoginInfoUtils updateLoginInfoUtils = (UpdateLoginInfoUtils) applicationContext.getBean("login.jdbc.action");

        Map<String, String> doUpdateList = new HashMap<String, String>(){
            {
                put(UserEnums.TOKEN.getValue(), token);
                put(UserEnums.UPDATE_TIME.getValue(), CurrentTime);
                put(UserEnums.LOGIN_TIME.getValue(), CurrentTime);
            }
        };
        Map<String, String> doConditionList = new HashMap<String, String>() {
            {
                put(UserEnums.OWNER_CODE.getValue(), adminSingleInfo.getOwnerCode());
                put(UserEnums.ID.getValue(), Integer.toString(adminSingleInfo.getId()));
            }
        };
        updateLoginInfoUtils.updateLogin(doUpdateList, doConditionList);

        Timestamp actionTamp = new Timestamp(actionTime.getTime());

        adminSingleInfo.setToken(token);
        adminSingleInfo.setUpdateTime(actionTamp);
        adminSingleInfo.setLoginTime( actionTamp );
        adminSingleInfo.setLoginOut(new Timestamp( (new Date()).getTime() ));

        return new HashMap<String, User>() {{
            put("userInfo", adminSingleInfo);
        }};
    }

    @GetMapping("/login_out")
    @ResponseBody
    @CrossOrigin
    public Map<String, String> loginOut( HttpServletRequest request, HttpServletResponse response )
    {
        Map<String, String> list = new HashMap<String, String>();
        list.put("a", "我已经退出了登录");
        return list;
    }
}
