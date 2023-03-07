package org.cc.pojo;

/**
 * @Author cc
 * @Date 2022/10/13 19:23
 * @PackageName:org.cc.pojo
 * @ClassName: User
 * @Description: TODO
 * @Version 1.0
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(Integer id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
