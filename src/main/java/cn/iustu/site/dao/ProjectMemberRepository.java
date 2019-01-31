package cn.iustu.site.dao;

import cn.iustu.site.common.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
    List<ProjectMember> findByProjectId(Integer projectId);
    List<ProjectMember> findByMemberId(Integer memberId);
    Long countByProjectId(Integer projectId);
}
