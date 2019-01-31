package cn.iustu.site.admin.controller;

import cn.iustu.site.common.entity.Member;
import cn.iustu.site.common.entity.ProjectMember;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.common.util.PageUtil;
import cn.iustu.site.common.util.UploadUtil;
import cn.iustu.site.service.MemberService;
import cn.iustu.site.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
public class MemberControllerAdmin {

    @Value("${iustu.domain}")
    private String domain;

    @Value("${iustu.upload-path}")
    private String uploadPath;

    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectMemberService projectMemberService;

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

    @PostMapping("/add")
    public Result add(String name, Integer grade, MultipartFile avatar, String tech, String institute, String bio, String intro, String email, HttpServletRequest request){

        Result result = validate(name, grade, avatar, tech, institute, bio, intro, email);
        if(result != null) return result;

        Member member = new Member();

        try {
            String path = UploadUtil.uploadFile(domain, uploadPath, avatar, request);
            member.setAvatar(path);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().setMsg("头像上传失败");
        }

        member.setBio(bio);
        member.setEmail(email);
        member.setGrade(grade);
        member.setInstitute(institute);
        member.setIntro(intro);
        member.setName(name);
        member.setTech(tech);

        Member save = memberService.save(member);

        return Result.success()
                .setMsg("添加成功")
                .add("memberId", save.getId());
    }

    @PostMapping("/update")
    public Result update(Integer id, String name, Integer grade, MultipartFile avatar, String tech, String institute, String bio, String intro, String email, HttpServletRequest request){

        if(id == null) return Result.fail().setMsg("id不能为空");

        Member member = memberService.get(id);
        if(member == null) return Result.fail().setMsg("不存在指定id的工作室成员");

        if(avatar != null){
            try {
                String path = UploadUtil.uploadFile(domain, uploadPath, avatar, request);
                member.setAvatar(path);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail().setMsg("头像上传失败");
            }
        }

        if(!StringUtils.isEmpty(name)) member.setName(name);
        if(grade != null) member.setGrade(grade);
        if(!StringUtils.isEmpty(tech)) member.setTech(tech);
        if(!StringUtils.isEmpty(institute)) member.setInstitute(institute);
        if(!StringUtils.isEmpty(bio)) member.setBio(bio);
        if(!StringUtils.isEmpty(intro)) member.setIntro(intro);
        if(!StringUtils.isEmpty(email)) member.setEmail(email);

        memberService.save(member);

        return Result.success().setMsg("修改成功");

    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        if(id == null) return Result.fail().setMsg("id不能为空");
        if(memberService.get(id) == null) return Result.fail().setMsg("不存在指定id的工作室成员");

        //删除对应的项目成员
        List<ProjectMember> projectMembers = projectMemberService.listByMemberId(id);
        for(ProjectMember projectMember : projectMembers){
            projectMemberService.delete(projectMember.getId());
        }

        //删除成员
        memberService.delete(id);

        return Result.success().setMsg("删除成功");
    }



    private Result validate(String name, Integer grade, MultipartFile avatar, String tech, String institute, String bio, String intro, String email){

        if(StringUtils.isEmpty(name)) return Result.fail().setMsg("姓名不能为空");
        if(grade == null) return Result.fail().setMsg("年级不能为空");
        if(avatar == null) return Result.fail().setMsg("头像不能为空");
        if(StringUtils.isEmpty(tech)) return Result.fail().setMsg("技术方向不能为空");
        if(StringUtils.isEmpty(institute)) return Result.fail().setMsg("技术方向不能为空");
        if(StringUtils.isEmpty(bio)) return Result.fail().setMsg("履历不能为空");
        if(StringUtils.isEmpty(intro)) return Result.fail().setMsg("简介不能为空");
        if(StringUtils.isEmpty(email)) return Result.fail().setMsg("邮箱不能为空");

        return null;
    }
}
