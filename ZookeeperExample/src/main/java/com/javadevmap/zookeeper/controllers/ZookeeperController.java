package com.javadevmap.zookeeper.controllers;

import com.javadevmap.zookeeper.result.Result;
import com.javadevmap.zookeeper.result.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Zookeeper
 */
@RestController
@RequestMapping(value = "/zookeeper")
public class ZookeeperController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Result<String> hello() {
        Result<String> result = new Result<>(ResultCode.OK, "Hello Zookeeper!");
        return result;
    }
}
