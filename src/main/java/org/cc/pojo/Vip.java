package org.cc.pojo;

/**
 * @Author cc
 * @Date 2022/10/21 19:13
 * @PackageName:org.cc.pojo
 * @ClassName: Vip
 * @Description: TODO
 * @Version 1.0
 */
public class Vip {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String qq;

    @Override
    public String toString() {
        return "Vip{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }

    public Vip() {
    }

    public Vip(Integer id, String username, String email, String password, String qq) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.qq = qq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
