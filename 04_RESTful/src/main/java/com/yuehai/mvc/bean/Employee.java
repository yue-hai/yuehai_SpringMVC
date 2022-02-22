package com.yuehai.mvc.bean;

/**
 * @author 月海
 * @create 2022/1/25 14:45
 */

// 实体类
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    //1 男, 0 女
    private Integer gender;

    public Employee() { }
    public Employee(Integer id, String lastName, String email, Integer gender) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getGender() { return gender; }
    public void setGender(Integer gender) { this.gender = gender; }

}
