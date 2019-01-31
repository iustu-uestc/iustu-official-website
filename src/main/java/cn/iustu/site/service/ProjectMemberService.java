package cn.iustu.site.service;

import cn.iustu.site.common.entity.ProjectMember;
import cn.iustu.site.common.model.Page;

import java.util.List;

public interface ProjectMemberService {
    ProjectMember get(Integer id);
    List<ProjectMember> list(Page page);
    List<ProjectMember> listByProjectId(Integer projectId);
    List<ProjectMember> listByMemberId(Integer memberId);
    Long count();
    Long countByProjectId(Integer projectId);
    void update(ProjectMember projectMember);
    void add(ProjectMember projectMember);
    void delete(Integer id);
}
