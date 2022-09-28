package com.javadevmap.mysqlexample.controllers;

import com.javadevmap.mysqlexample.domain.ProductDomain;
import com.javadevmap.mysqlexample.result.Result;
import com.javadevmap.mysqlexample.result.ResultCode;
import com.javadevmap.mysqlexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * mysql
 */
@RestController
@RequestMapping(value = "/mysql")
public class MysqlController {


    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/{proId}", method = RequestMethod.GET)
    public Result<ProductDomain> product(@PathVariable int proId) {

        Result<ProductDomain> result =null;
        ProductDomain domain= productService.findProductById(proId);
        if (null != domain) {
            result = new Result(ResultCode.OK, domain);
        }else{
            result=new Result(ResultCode.Not_Found,"未找到");
        }
        return result;
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result<String> hello() {
        Result<String> result = new Result<>(ResultCode.OK, "Hello Mysql!");

        return result;
    }
}
