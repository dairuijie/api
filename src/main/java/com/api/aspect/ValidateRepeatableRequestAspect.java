package com.api.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.api.util.HttpContextUtils;
import com.api.util.RedisUtil;

/**
 * 
 * @ClassName:  ValidateRepeatableRequestAspect   
 * @Description:TODO(注解类)   
 * @author: drj 
 * @date:   2018年11月30日 下午10:20:01   
 *     
 * @Copyright: 2018 
 *
 */
@Component
@Aspect
public class ValidateRepeatableRequestAspect {
	private static final Logger logger = LoggerFactory.getLogger(ValidateRepeatableRequestAspect.class);
	@Autowired
    private RedisUtil jedis;
	
	private String redisHashKey = "token";
	@Pointcut("@annotation(com.api.annotation.ValidateRepeatableRequest)")
	public void validateRepeatableRequest() {
    }

	/**
	 * 方法前执行
	 * @param joinPoint
	 * @throws Throwable
	 */
    @Before("validateRepeatableRequest()")
    public void before(JoinPoint joinPoint) throws Exception   {

        // 获取http请求对象
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String busikey = request.getParameter(redisHashKey);
        if (StringUtils.isBlank(busikey)) {
            JSONObject json = getParams(joinPoint);
            if(json != null) {
            	busikey = (String) json.get(redisHashKey);
            }
        }
        if (StringUtils.isBlank(busikey)) {
            throw new Exception("当前请求不合法，请刷新后重试！");
        }
        synchronized (this) {
            Long delCount = jedis.getJedis().decr(redisHashKey); //hdel(redisHashKey, busikey);
            if (delCount == null || delCount != 1) {
                logger.info("接口重复提交，或者页面长时间不操作导致token失效！");
                throw new RuntimeException("当前页面数据已更新，请刷新后重试！");
            }
        }
    }

    /**
     * 获取json参数
     * 
     * @param joinPoint
     * @return
     */
    private JSONObject getParams(JoinPoint joinPoint) {
        // 获取参数值
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return null;
        }
        JSONObject params = new JSONObject();
        // 对象接收参数
        try {
            String data = JSON.toJSONString(joinPoint.getArgs()[0]);
            params = JSON.parseObject(data);
        }
        // 普通参数传入
        catch (JSONException e) {
            // 获取参数名
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
                params.put(methodSignature.getParameterNames()[i], args[i]);
            }
        }
        return params;
    }

}
