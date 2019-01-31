package cn.iustu.site.admin.controller;

import cn.iustu.site.common.entity.RecruitLink;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.RecruitLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/recruitLink")
public class RecruitLinkControllerAdmin {

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

    @PostMapping("/update")
    public Result update(@RequestParam String url){
        RecruitLink recruitLink = recruitLinkService.get();
        if(recruitLink == null){
            recruitLink = new RecruitLink();
            recruitLink.setId(1);
            recruitLink.setUrl(url);
        }else{
            if(!StringUtils.isEmpty(url)) recruitLink.setUrl(url);
        }
        recruitLinkService.update(recruitLink);

        return Result.success().setMsg("修改成功");
    }
}
