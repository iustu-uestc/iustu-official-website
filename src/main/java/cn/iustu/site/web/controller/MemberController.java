package cn.iustu.site.web.controller;

import cn.iustu.site.common.entity.Member;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.common.util.PageUtil;
import cn.iustu.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/get/{id}")
    public Result get(@PathVariable Integer id){
        Member member = memberService.get(id);
        if(member == null) return Result.fail().setMsg("不存在指定id的工作室成员");
        return Result.success()
                .add("id", member.getId())
                .add("name", member.getName())
                .add("grade", member.getGrade())
                .add("avatar", member.getAvatar())
                .add("tech", member.getTech())
                .add("institute", member.getInstitute())
                .add("bio", member.getBio())
                .add("intro", member.getIntro())
                .add("email", member.getEmail());

    }

    @GetMapping("/list")
    public Result list(Page page){
        PageUtil.pageValidate(page);
        Long count = memberService.count();
        List<Member> members = memberService.list(page);
        return Result.success()
                .add("total", count)
                .add("members", members);
    }

    @GetMapping("/list/grade/{grade}")
    public Result list(@PathVariable Integer grade, Page page){
        PageUtil.pageValidate(page);
        Long count = memberService.countByGrade(grade);
        List<Member> members = memberService.listByGrade(grade, page);
        return Result.success()
                .add("total", count)
                .add("members", members);

    }
}
