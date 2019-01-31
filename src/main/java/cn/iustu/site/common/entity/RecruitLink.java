package cn.iustu.site.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_recruit_link")
public class RecruitLink {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
