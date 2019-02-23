package cn.iustu.site.common.entity.vo;

import cn.iustu.site.common.entity.Project;

import java.util.List;

public class ProjectVO extends Project {

    List<ProjectMemberVO> projectMembers;

    public List<ProjectMemberVO> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<ProjectMemberVO> projectMembers) {
        this.projectMembers = projectMembers;
    }
}
