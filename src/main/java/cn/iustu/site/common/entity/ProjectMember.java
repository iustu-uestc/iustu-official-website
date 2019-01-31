package cn.iustu.site.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_project_member")
public class ProjectMember {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer projectId;

    @Column
    private Integer memberId;

    @Column
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
