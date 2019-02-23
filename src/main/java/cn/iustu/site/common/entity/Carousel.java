package cn.iustu.site.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_carousel")
public class Carousel {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
