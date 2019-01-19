package com.api.util;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

/**
 * 
 * @ClassName:  RedisUtil   
 * @Description:TODO(������һ�仰��������������)   
 * @author: drj 
 * @date:   2018��11��10�� ����10:25:32   
 *     
 * @Copyright: 2018 
 *
 */
@Component
public class RedisUtil {
    private static String addr;
    private static int port;

    //���������ļ�
    private static ResourceBundle rb = ResourceBundle.getBundle("db-config");

    //��ʼ������
    static {
        addr = rb.getString("jedis.addr");
        port = Integer.parseInt(rb.getString("jedis.port"));
    }

    //��ȡJedisʵ��
    public synchronized static Jedis getJedis() {
        //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis(addr, port);

        return jedis;
    }

    //�ͷ�Jedis��Դ
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}