package com.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: Users
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: drj
 * @date: 2019年1月19日 下午9:14:45
 * 
 * @Copyright: 2019
 *
 */
@ApiModel(value="user对象",description="用户对象说明")
public class Users {
	@ApiModelProperty(name = "userName",value = "用户名",required = false,example = "dairuijie")
	private String userName;
	@ApiModelProperty(name = "age",value = "年龄",required = false,example = "25")
	private Integer age;
	@ApiModelProperty(name = "sex",value = "性别",required = false,example = "true")
	private Boolean sex;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
}
