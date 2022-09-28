package com.javadevmap.mongodbexample.controllers;

import com.javadevmap.mongodbexample.model.Product;
import com.javadevmap.mongodbexample.repository.ProductMongoRepository;
import com.javadevmap.mongodbexample.result.Result;
import com.javadevmap.mongodbexample.result.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * mongodb 
 */
@RestController
@RequestMapping(value = "/mongodb")
public class MongodbController {


    @Autowired
    private ProductMongoRepository productRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result<String> hello() {
        Result<String> result = new Result<>(ResultCode.OK, "hello mongodb!");
        return result;
    }

    @RequestMapping("/addProduct")
    @ResponseBody
    public Result<String> addProduct(@RequestBody Product productModel) {
        System.out.println( "addProduct() called with: productModel = [" + productModel + "]");
        Product product = new Product();

        if(null == productModel){
            return new Result<>(ResultCode.Bad_Request);
        }

        BeanUtils.copyProperties(productModel,product);
        Product beanResult = productRepository.save(product);
        System.out.println(" save result is "+beanResult);
        return new Result<>(200,"success");
    }



}
