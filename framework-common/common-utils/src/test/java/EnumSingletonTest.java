/**
 * Created by Administrator on 2018/6/22.
 */


import com.ws.framework.common.utils.DBConnection;
import com.ws.framework.common.utils.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wangsi
 * @create 2018-06-22 12:26
 **/
public class EnumSingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        DBConnection dbConnection1 = Singleton.INSTANCE.getDbConnection();
        DBConnection dbConnection2 = Singleton.INSTANCE.getDbConnection();
        System.out.println("两个实例是否相同:" + (dbConnection1==dbConnection2));


//        Constructor<Singleton> constructors = Singleton.class.getDeclaredConstructor();
        Constructor<Singleton> constructors = Singleton.class.getDeclaredConstructor(String.class,int.class);
        constructors.setAccessible(true);
//        Singleton dbConnection3 = constructors.newInstance();
        DBConnection dbConnection3 = constructors.newInstance("ss" ,1).getDbConnection();

        System.out.println(dbConnection1 + "\n" + dbConnection2 + "\n" + dbConnection3);







    }
}
