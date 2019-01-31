package cn.iustu.site.common.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_intro")
public class Intro {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
