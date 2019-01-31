package cn.iustu.site.service.impl;

import cn.iustu.site.common.entity.Project;
import cn.iustu.site.common.model.Page;
import cn.iustu.site.dao.ProjectRepository;
import cn.iustu.site.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> list(Page page) {
        Pageable pageable = PageRequest.of(page.getPage() - 1, page.getRows());
        org.springframework.data.domain.Page<Project> projects = projectRepository.findAll(pageable);
        return projects.getContent();
    }

    @Override
    public Project get(Integer id) {
        try{
            return projectRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public Long count() {
        return projectRepository.count();
    }
}
