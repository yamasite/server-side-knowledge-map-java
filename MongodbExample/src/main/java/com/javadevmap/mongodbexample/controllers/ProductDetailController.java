package com.javadevmap.mongodbexample.controllers;

import com.javadevmap.mongodbexample.model.Product;
import com.javadevmap.mongodbexample.repository.ProductMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品详情页面
 */
@Controller
@RequestMapping(value="/product")
public class ProductDetailController {
    @Autowired
    private ProductMongoRepository productRepository;

    @RequestMapping(value="/{productId}/detail")
    public ModelAndView getProductDesc(@PathVariable("productId") Integer productId) {

        Product product = productRepository.findOne(productId);
        ModelAndView modelAndView = new ModelAndView("detail");
        if(null == product){
            modelAndView.addObject("tips",String.format("商品id为 %s 不存在",productId));
        }else{
            modelAndView.addObject("tips",String.format("商品 %s 的商品详情页面",product.getName()));
            modelAndView.addObject("desc",product.getHtmlDetail());
        }
        return modelAndView;
    }
}
