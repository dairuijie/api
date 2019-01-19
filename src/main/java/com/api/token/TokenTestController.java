package com.api.token;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.api.Users;
import com.api.util.RedisUtil;
import com.api.util.ToolsUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import redis.clients.jedis.Jedis;
/**
 * 
 * @ClassName:  TokenTestController   
 * @Description:TODO(测试类)
 * @author: drj 
 * @date:   2019年1月19日 下午10:24:01   
 *     
 * @Copyright: 2019 
 *
 */
@Controller
@RequestMapping("/test")
public class TokenTestController {
	private static Jedis jedis = RedisUtil.getJedis();

	@RequestMapping(value = "/getMyToken", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取token数据", notes = "获取token数据", tags = "token", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "成功", response = ResponseBody.class), })
	public String getMyToken(HttpServletResponse response, @RequestBody Users user) {
		String token = jedis.get("token");
		if (token != null && !token.equals("")) {
			return token;
		} else {
			jedis.setex("token", 1 * 60 * 10, ToolsUtil.GetGUID());
			return jedis.get("token");
		}
	}

	@RequestMapping(value = "/getMyData", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取user数据", notes = "通过最新token获取数据", tags = "userData", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "唯一标示token", required = true, dataType = "String"), })
	@ApiResponses({ @ApiResponse(code = 200, message = "成功", response = ResponseBody.class), })
	public String getMyData(@RequestBody String token) {
		System.err.println(token);
		if (token != null && token.equals(jedis.get("token"))) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", "drj");
			params.put("age", 24);
			return JSONObject.toJSONString(params);
		} else {
			return "没有权限";
		}

	}
}
