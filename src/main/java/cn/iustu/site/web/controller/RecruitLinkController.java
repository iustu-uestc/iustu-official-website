package cn.iustu.site.web.controller;

import cn.iustu.site.common.entity.RecruitLink;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.RecruitLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruitLink")
public class RecruitLinkController {

    @Autowired
    private RecruitLinkService recruitLinkService;

    @GetMapping("/get")
    public Result get(){
        RecruitLink recruitLink = recruitLinkService.get();
        if(recruitLink == null){
            return Result.fail().setMsg("记录不存在");
        }else{
            return Result.success().add("url", recruitLink.getUrl());
        }
    }
}
