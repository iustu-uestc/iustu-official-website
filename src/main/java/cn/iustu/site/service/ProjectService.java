package cn.iustu.site.service;

import cn.iustu.site.common.entity.Project;
import cn.iustu.site.common.model.Page;

import java.util.List;

public interface ProjectService {
    Project save(Project project);
    void delete(Integer id);
    List<Project> list(Page page);
    Project get(Integer id);
    Long count();
}
