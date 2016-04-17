package com.kxw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kingson.wu on 2015/9/25.
 */

@RestController
@RequestMapping("/")
public class IndexController {

    public String index(){

        return "hello";
    }
}
