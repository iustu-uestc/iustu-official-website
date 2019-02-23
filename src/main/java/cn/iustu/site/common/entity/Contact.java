package cn.iustu.site.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_contact")
public class Contact {

    @Id
    private Integer id;

    @Column
    private String qq;

    @Column
    private String email;

    @Column
    private String tel;

    @Column
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
