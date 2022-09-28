package com.javadevmap.mybatis.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class DomainUser {
	private Integer id;

	@NotBlank(message="地址不能为空")
    private String address;

    @NotNull(message="年龄不能为空")
	@Min(value=0,message="输入年龄小于最小值")
	@Max(value=150,message="输入年龄大于最大值")
    private Integer age;

    @NotBlank(message="姓名不能为空")
    private String name;

    @Length(min=7,max=11,message = "输入号码错误")
    private String phoneNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
    
    
}
