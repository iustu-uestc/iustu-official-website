package cn.iustu.site.common.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_project")
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String projectBackground;

    @Column(nullable = false)
    private String solution;

    @Column(nullable = false)
    private String projectFunction;

    @Column(nullable = false)
    private Date releaseDate;

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
}
