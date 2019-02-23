package cn.iustu.site.common.entity.dto;


import cn.iustu.site.common.entity.ProjectMember;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public class ProjectDTO {

    private Integer id;
    private MultipartFile image;
    private String name;
    private String projectBackground;
    private String solution;
    private String projectFunction;
    private Date releaseDate;
    private List<ProjectMember> projectMembers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectBackground() {
        return projectBackground;
    }

    public void setProjectBackground(String projectBackground) {
        this.projectBackground = projectBackground;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getProjectFunction() {
        return projectFunction;
    }

    public void setProjectFunction(String projectFunction) {
        this.projectFunction = projectFunction;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<ProjectMember> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<ProjectMember> projectMembers) {
        this.projectMembers = projectMembers;
    }
}
