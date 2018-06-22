/**
 * Created by Administrator on 2018/5/22.
 */

import com.ws.framework.web.security.FWWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author wangsi
 * @create 2018-05-22 19:21
 **/
public class Test {



    public static void main(String[] args) {


        ApplicationContext context = getConfig();

        FWWebSecurityManager fwWebSecurityManager= (FWWebSecurityManager) context.getBean("fwWebSecurityManager");

        System.out.println("ssss");
        System.out.println("get properties:" + System.getProperty("test.url"));

    }









    public static ApplicationContext getConfig() {
        /**
         *
         */

        /**
         * 启动的时候报 java.lang.NoClassDefFoundError: javax/servlet/http/HttpServletRequest
         * 该类存在于javax.servlet-api，依赖自framework-web，因为framework-web设置该包的scope为provide,直接丢弃
         *
         * A–>B–>C。当前项目为A，A依赖于B，B依赖于C。知道B在A项目中的scope，那么怎么知道C在A中的scope呢？答案是：
            当C是test或者provided时，C直接被丢弃，A不依赖C；
            否则A依赖C，C的scope继承于B的scope。
         */

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/biz-*.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/biz-spring.xml","classpath:spring/biz-dubbo-spring.xml"});

        return context;
    }
}
