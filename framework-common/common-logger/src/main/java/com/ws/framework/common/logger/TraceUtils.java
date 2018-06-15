package com.ws.framework.common.logger; /**
 * Created by Administrator on 2018/6/14.
 */

import org.apache.log4j.MDC;

import java.util.Random;

/**
 * @author wangsi
 * @create 2018-06-14 14:20
 **/
public class TraceUtils {

    public static final String TRACE_ID_KEY = "trace_id";
    public static final int TRACE_ID_LENGTH = 8;

    private static final Random RANDOM = new Random();

    public static void beginTrace(){
        String traceId = random(TRACE_ID_LENGTH);
        MDC.put(TRACE_ID_KEY,traceId);
    }


    public static void beginTrace(String traceId){
        MDC.put(TRACE_ID_KEY,traceId);
    }

    public static void endTrace(){
        MDC.remove(TRACE_ID_KEY);
    }

    public static String getTraceId(){
        return (String) MDC.get(TRACE_ID_KEY);

    }

    private static String random(int count) {

        char[] buffer = new char[count];
        int end = 'z' + 1;
        int start = ' ';

        int gap = end - start;

        while (count-- != 0)
        {
            char ch = (char) (RANDOM.nextInt(gap));

            if (Character.isLetter(ch) || Character.isDigit(ch))
            {
                if (ch >= 56320 && ch <= 57343)
                {
                    if (count == 0)
                    {
                        count++;
                    } else
                    {
                        // low surrogate, insert high surrogate after putting it in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + RANDOM.nextInt(128));
                    }
                } else if (ch >= 55296 && ch <= 56191)
                {
                    if (count == 0)
                    {
                        count++;
                    } else
                    {
                        // high surrogate, insert low surrogate before putting it in
                        buffer[count] = (char) (56320 + RANDOM.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if (ch >= 56192 && ch <= 56319)
                {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else
                {
                    buffer[count] = ch;
                }
            } else
            {
                count++;
            }
        }
        return new String(buffer);
    }

    public static void main(String[] args) {
        System.out.println(random(TRACE_ID_LENGTH));
    }

}
