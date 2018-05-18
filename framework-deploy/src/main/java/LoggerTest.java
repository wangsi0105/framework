/**
 * Created by Administrator on 2018/4/20.
 */

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangsi
 * @create 2018-04-20 10:29
 **/
public class LoggerTest {

    static Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        System.out.println("Hello World!");
    }

}
