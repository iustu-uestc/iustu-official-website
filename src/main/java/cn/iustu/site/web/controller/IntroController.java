package cn.iustu.site.web.controller;

import cn.iustu.site.common.entity.Intro;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intro")
public class IntroController {

    @Autowired
    private IntroService introService;

    @GetMapping("/get")
    public Result get(){
        Intro intro = introService.get();
        return Result.success().add("content", intro.getContent());
    }
}
