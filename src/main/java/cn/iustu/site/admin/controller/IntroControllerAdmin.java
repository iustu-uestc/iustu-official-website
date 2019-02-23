package cn.iustu.site.admin.controller;

import cn.iustu.site.common.entity.Intro;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/intro")
public class IntroControllerAdmin {

    @Autowired
    private IntroService introService;

    @GetMapping("/get")
    public Result get(){
        Intro intro = introService.get();
        return Result.success().add("content", intro.getContent());
    }

    @PostMapping("/update")
    public Result update(@RequestParam String content){
        Intro intro = introService.get();
        if(intro == null){
            intro = new Intro();
            intro.setId(1);
            intro.setContent(content);
        }else{
            if(!StringUtils.isEmpty(content)) intro.setContent(content);
        }
        introService.save(intro);

        return Result.success().setMsg("修改成功");
    }
}
