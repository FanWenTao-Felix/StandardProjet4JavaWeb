package com.kxw.controller;

import com.kxw.json.JacksonUtils;
import com.kxw.model.ParamModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kxw on 2015/9/29.
 */

@RestController
@RequestMapping(value = "/param" ,method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
public class ParamterController {

    /**
     *  {<a href='http://localhost:8084/portal/param/list?id=1&name=kingson&company=abcs'>@link</a>}
     */
    @RequestMapping("/list")
    public String list(ParamModel param) throws Exception {

        return JacksonUtils.obj2json(param);
    }
}
