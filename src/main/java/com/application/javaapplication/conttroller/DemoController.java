package com.application.javaapplication.conttroller;

import com.application.javaapplication.annotationCustomer.AnnotationUtil;
import com.application.javaapplication.enums.*;
import org.apache.log4j.LogManager;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/api")
public class DemoController
{

    public static String ABC;

    @Value("${spring.profiles.active}")
    public void TestA(String str) {
        ABC = str;
    }

    @Autowired
    private CommonEnum commonEnum;

    @Autowired
    private EnumsUtil enumsUtil;

    @Autowired
    private AnnotationUtil annotationUtil;

    @Value(value = "${spring.profiles.active}")
    private String EnvironmentMode;

    @Value("${doctrine.prefix.alias}")
    private String EntityAlias;

    @Value("${fsm.server}")
    private String fsm;

    private static org.apache.log4j.Logger logger = LogManager.getLogger(DemoController.class);

    public void init()
    {
        String str = "This is a " + EnvironmentMode + " environment";

        System.err.println( str );
        System.err.println( fsm );

        logger.info("print: _____" + str + "++++" + EntityAlias);
    }

    @GetMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException {

        System.out.println();

        String value = CallEnum.STRING_TEST_A.getDisplayName();
//        String value = "";

        String inputKey = request.getParameter("abd");

        this.test("a", "b","c");

        return "Index Page : out put : " + inputKey + "value = " + value;
    }

    @ResponseBody
    @RequestMapping("/post_test")
    public String postTest(HttpServletRequest request) {
        String postKey = request.getParameter("post");

        return "Post key 123 : " + postKey;
    }

    @RequestMapping("/redis_test")
    @ResponseBody
    public String redisStr(HttpServletRequest request)
    {
        commonEnum.setEnumMap();

        String a = enumsUtil.enumsMap().getTest();

        String message = "";

        return "redis Message : " + message + "a" + a;
    }

    public void test ( String a, String ...b )
    {

        System.out.println(a);

        for (int i = 0; i < 2; i++) {
            System.out.println(b[i]);
        }
    }
}
