package cn.iustu.site.admin.controller;

import cn.iustu.site.config.constant.IUSTUConstant;
import cn.iustu.site.common.entity.Project;
import cn.iustu.site.common.entity.ProjectMember;
import cn.iustu.site.common.entity.dto.ProjectDTO;
import cn.iustu.site.common.entity.vo.ProjectMemberVO;
import cn.iustu.site.common.entity.vo.ProjectVO;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.common.util.PageUtil;
import cn.iustu.site.common.util.UploadUtil;
import cn.iustu.site.service.MemberService;
import cn.iustu.site.service.ProjectMemberService;
import cn.iustu.site.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin/project")
public class ProjectControllerAdmin {

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

    @PostMapping("/add")
    public Result add(ProjectDTO projectDTO, HttpServletRequest request) {
        //校验
        Result result = validate(projectDTO);
        if (result != null) return result;

        //设置项目属性
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setProjectBackground(projectDTO.getProjectBackground());
        project.setProjectFunction(projectDTO.getProjectFunction());
        project.setReleaseDate(projectDTO.getReleaseDate());
        project.setSolution(projectDTO.getSolution());

        //上传项目图片
        try {
            String path = UploadUtil.uploadFile(IUSTUConstant.DOMAIN, IUSTUConstant.UPLOAD_PATH, projectDTO.getImage(), request);
            project.setImage(path);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().setMsg("项目图片上传失败");
        }

        Project project1 = projectService.save(project);

        //保存项目开发者
        for (ProjectMember projectMember : projectDTO.getProjectMembers()) {
            projectMember.setId(null);
            projectMember.setProjectId(project1.getId());
            projectMemberService.add(projectMember);
        }

        return Result.success().setMsg("添加成功")
                .add("projectId", project1.getId());
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
        if (projectService.get(id) == null) return Result.fail().setMsg("不存在指定的项目");

        //删除项目下的开发者
        List<ProjectMember> projectMembers = projectMemberService.listByProjectId(id);
        for (ProjectMember projectMember : projectMembers) {
            projectMemberService.delete(projectMember.getId());
        }
        //删除项目
        projectService.delete(id);
        return Result.success().setMsg("删除成功");
    }

    @PostMapping("/update")
    public Result update(ProjectDTO projectDTO, HttpServletRequest request) {

        //校验
        if (projectDTO.getId() == null) return Result.fail().setMsg("项目id能为空");
        Project project = projectService.get(projectDTO.getId());
        if (project == null) return Result.fail().setMsg("不存在指定的项目");

        if (projectDTO.getReleaseDate() != null) project.setReleaseDate(projectDTO.getReleaseDate());
        if (!StringUtils.isEmpty(projectDTO.getName())) project.setName(projectDTO.getName());
        if (!StringUtils.isEmpty(projectDTO.getSolution())) project.setSolution(projectDTO.getSolution());
        if (!StringUtils.isEmpty(projectDTO.getProjectFunction()))
            project.setProjectFunction(projectDTO.getProjectFunction());
        if (!StringUtils.isEmpty(projectDTO.getProjectBackground()))
            project.setProjectBackground(projectDTO.getProjectBackground());

        //上传项目图片并更新
        if (projectDTO.getImage() != null) {
            try {
                String path = UploadUtil.uploadFile(IUSTUConstant.DOMAIN, IUSTUConstant.UPLOAD_PATH, projectDTO.getImage(), request);
                project.setImage(path);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail().setMsg("图片上传失败");
            }
        }

        //添加或修改项目开发者
        if (!(projectDTO.getProjectMembers() == null || projectDTO.getProjectMembers().size() == 0)) {
            for (ProjectMember projectMember : projectDTO.getProjectMembers()) {

                //如果是对已有的项目开发者进行修改
                if (projectMember.getId() != null && projectMemberService.get(projectMember.getId()) != null) {
                    ProjectMember projectMember1 = projectMemberService.get(projectMember.getId());
                    if (projectMember.getMemberId() != null) {
                        if (memberService.get(projectMember.getMemberId()) != null) {
                            projectMember1.setMemberId(projectMember.getMemberId());
                        } else {
                            return Result.fail().setMsg("不存在指定id的工作室成员");
                        }
                    }
                    if (!StringUtils.isEmpty(projectMember.getRole())) projectMember1.setRole(projectMember.getRole());
                    projectMemberService.update(projectMember1);
                } else {
                    projectMember.setId(null);
                    if (projectMember.getMemberId() == null)
                        return Result.fail().setMsg("新添加项目开发者对应的工作室成员id（memberId）不能为空");
                    if (projectMember.getRole() == null) return Result.fail().setMsg("新添加项目开发者对应的角色不能为空");
                    projectMember.setProjectId(projectDTO.getId());
                    projectMemberService.add(projectMember);
                }
            }
        }

        projectService.save(project);

        return Result.success().setMsg("修改成功");
    }

    private Result validate(ProjectDTO projectDTO) {
        if (projectDTO.getImage() == null) return Result.fail().setMsg("项目图片不能为空");
        if (StringUtils.isEmpty(projectDTO.getName())) return Result.fail().setMsg("项目名称不能为空");
        if (StringUtils.isEmpty(projectDTO.getProjectBackground())) return Result.fail().setMsg("项目背景不能为空");
        if (StringUtils.isEmpty(projectDTO.getProjectFunction())) return Result.fail().setMsg("项目用途不能为空");
        if (StringUtils.isEmpty(projectDTO.getSolution())) return Result.fail().setMsg("解决方案不能为空");
        if (projectDTO.getReleaseDate() == null) return Result.fail().setMsg("上线日期不能为空");
        if (projectDTO.getProjectMembers() == null || projectDTO.getProjectMembers().size() == 0)
            return Result.fail().setMsg("项目开发人员不能为空");

        for (ProjectMember projectMember : projectDTO.getProjectMembers()) {
            if (StringUtils.isEmpty(projectMember.getRole())) return Result.fail().setMsg("项目开发者角色不能为空");
            if (projectMember.getMemberId() == null) return Result.fail().setMsg("项目开发者对应的工作室成员的id(memberId)不能为空");
            if (memberService.get(projectMember.getMemberId()) == null) return Result.fail().setMsg("不存在指定id的工作室成员");
        }

        return null;
    }


}
