package com.javadevmap.auth.controllers;

import java.security.MessageDigest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javadevmap.auth.result.Result;
import com.javadevmap.auth.result.ResultCode;

@RestController
@RequestMapping("/auth")
public class AuthController {
	class Admin{
		public int id = 1;
		public String name = "admin";
		public String password = "password";
	}
	
	private Admin admin = new Admin();
	
	@RequestMapping(value="/gettoken",method=RequestMethod.GET)
	public Result<String> getToken(@RequestParam("name") String name,@RequestParam("password") String password) {
		if(name!=null && password!=null && name.equals(admin.name) && password.equals(admin.password)) {
			String token = generateToken();
			Result<String> result = new Result<>(ResultCode.OK,token);
			return result;
		}else {
			Result<String> result = new Result<>(ResultCode.Bad_Request);
			return result;
		}
	}
	
	@RequestMapping(value="/validtoken",method=RequestMethod.GET)
	public Result<String> validToken(@RequestParam("token") String token) {
		if(token != null && token.equals(generateToken())) {
			Result<String> result = new Result<>(ResultCode.OK);
			return result;
		}
		else {
			Result<String> result = new Result<>(ResultCode.Bad_Request);
			return result;
		}
	}
	
	private String generateToken() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(admin.id).append(admin.name).append(admin.password);
		try {
			return md5Encode(stringBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
