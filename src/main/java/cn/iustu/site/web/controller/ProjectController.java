package cn.iustu.site.web.controller;

import cn.iustu.site.common.entity.Project;
import cn.iustu.site.common.entity.ProjectMember;
import cn.iustu.site.common.entity.vo.ProjectMemberVO;
import cn.iustu.site.common.entity.vo.ProjectVO;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.common.util.PageUtil;
import cn.iustu.site.service.MemberService;
import cn.iustu.site.service.ProjectMemberService;
import cn.iustu.site.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/get/{id}")
    public Result get(@PathVariable Integer id) {
        Project project = projectService.get(id);
        if (project == null) return Result.fail().setMsg("不存在指定id的项目");

        List<ProjectMember> list = projectMemberService.listByProjectId(id);
        List<ProjectMemberVO> projectMembers = new ArrayList<>();
        for (ProjectMember projectMember : list) {
            ProjectMemberVO projectMemberVO = new ProjectMemberVO();
            projectMemberVO.setId(projectMember.getId());
            projectMemberVO.setRole(projectMember.getRole());
            projectMemberVO.setAvatar(memberService.get(projectMember.getMemberId()).getAvatar());
            projectMemberVO.setName(memberService.get(projectMember.getMemberId()).getName());

            projectMembers.add(projectMemberVO);
        }

        return Result.success()
                .add("id", project.getId())
                .add("image", project.getImage())
                .add("name", project.getName())
                .add("projectBackground", project.getProjectBackground())
                .add("solution", project.getSolution())
                .add("projectFunction", project.getProjectFunction())
                .add("releaseDate", project.getReleaseDate())
                .add("projectMembers", projectMembers);
    }

    @GetMapping("/list")
    public Result list(Page page) {
        PageUtil.pageValidate(page);

        List<Project> list = projectService.list(page);
        List<ProjectVO> projects = new ArrayList<>();

        for (Project project : list) {
            ProjectVO projectVO = new ProjectVO();
            List<ProjectMemberVO> projectMemberVOS = new ArrayList<>();
            BeanUtils.copyProperties(project, projectVO);
            List<ProjectMember> projectMembers = projectMemberService.listByProjectId(project.getId());
            for (ProjectMember projectMember : projectMembers) {
                ProjectMemberVO projectMemberVO = new ProjectMemberVO();
                projectMemberVO.setId(projectMember.getId());
                projectMemberVO.setRole(projectMember.getRole());
                projectMemberVO.setAvatar(memberService.get(projectMember.getMemberId()).getAvatar());
                projectMemberVO.setName(memberService.get(projectMember.getMemberId()).getName());
                projectMemberVOS.add(projectMemberVO);
            }
            projectVO.setProjectMembers(projectMemberVOS);
            projects.add(projectVO);
        }

        return Result.success()
                .add("total", projectService.count())
                .add("projects", projects);
    }
}
