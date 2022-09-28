package com.javadevmap.elasticsearch.controllers;

import com.javadevmap.elasticsearch.result.Result;
import com.javadevmap.elasticsearch.result.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ElasticSearch
 */
@RestController
@RequestMapping(value = "/elastic")
public class ElasticSearchController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result<String> hello() {
        Result<String> result = new Result<>(ResultCode.OK, "Hello ElasticSearch!");
        return result;
    }
}
