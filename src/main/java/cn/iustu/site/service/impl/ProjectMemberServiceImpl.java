package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.ProjectMember;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.dao.ProjectMemberRepository;
import cn.iustu.site.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Override
    public ProjectMember get(Integer id) {
        try{
            return projectMemberRepository.findById(id).get();
        }catch(NoSuchElementException e){
            return null;
        }
    }

    @Override
    public List<ProjectMember> list(Page page) {
        Pageable pageable = PageRequest.of(page.getPage() - 1, page.getRows());
        org.springframework.data.domain.Page<ProjectMember> projectMembers = projectMemberRepository.findAll(pageable);
        return projectMembers.getContent();
    }

    @Override
    public List<ProjectMember> listByProjectId(Integer projectId) {
        return projectMemberRepository.findByProjectId(projectId);
    }

    @Override
    public List<ProjectMember> listByMemberId(Integer memberId) {
        return projectMemberRepository.findByMemberId(memberId);
    }

    @Override
    public Long count() {
        return projectMemberRepository.count();
    }

    @Override
    public Long countByProjectId(Integer projectId) {
        return projectMemberRepository.countByProjectId(projectId);
    }

    @Override
    @Transactional
    public void update(ProjectMember projectMember) {
        projectMemberRepository.save(projectMember);
    }

    @Override
    @Transactional
    public void add(ProjectMember projectMember) {
        projectMemberRepository.save(projectMember);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        projectMemberRepository.deleteById(id);
    }
}
