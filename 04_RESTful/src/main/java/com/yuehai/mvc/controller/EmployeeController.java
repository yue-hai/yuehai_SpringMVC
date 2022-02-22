package com.yuehai.mvc.controller;

import com.yuehai.mvc.bean.Employee;
import com.yuehai.mvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * @author 月海
 * @create 2022/1/25 14:43
 */

// @Controller注解，标识该类为控制器
@Controller
public class EmployeeController {

    // 注入属性注解，按类型注入
    @Autowired
    private EmployeeDao employeeDao;

    // 查询全部数据|/employee|GET|
    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    // 使用 Model 向 request 域对象共享数据
    public String getEmployee(Model model){
        // 调用employeeDao的getAll()方法，获取数据
        Collection<Employee> employeeList = employeeDao.getAll();
        // 向请求域共享数据，参数1为名称，参数2为值
        model.addAttribute("employeeList",employeeList);

        return "employee_list";
    }

    // 删除|/employee/2|DELETE|
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    // 获取占位符请求参数中的id的值，并赋值给Integer类型的形参id
    public String deleteEmployee(@PathVariable("id") Integer id){
        // 调用employeeDao的delete()方法，删除数据
        employeeDao.delete(id);

        // 重定向视图到employee（查询全部数据）视图请求
        return "redirect:/employee";
    }

    // 跳转到添加数据页面|/toAdd|GET|配置文件中已配置

    // 执行保存|/employee|POST|
    @RequestMapping(value = "/employee",method = RequestMethod.POST)
    // 通过POJO获取请求参数
    // 若浏览器传输的请求参数的参数名和实体类中的属性名一致，那么请求参数就会为此属性赋值
    public String addEmployee(Employee employee){
        // 调用employeeDao的save()方法，添加保存数据
        employeeDao.save(employee);

        // 重定向视图到employee（查询全部数据）视图请求
        return "redirect:/employee";
    }

    // 跳转到更新数据页面（根据id查询）|/employee/1|GET|
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    // 获取占位符请求参数中的id的值，并赋值给Integer类型的形参id
    // 使用 Model 向 request 域对象共享数据
    public String getEmployeeById(@PathVariable("id") Integer id,Model model){
        // 根据id查询数据
        Employee employee = employeeDao.get(id);
        // 向请求域共享数据，参数1为名称，参数2为值
        model.addAttribute("employee",employee);

        return "employee_update";
    }

    // 执行更新（修改）|/employee|PUT|
    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    // 通过POJO获取请求参数
    // 若浏览器传输的请求参数的参数名和实体类中的属性名一致，那么请求参数就会为此属性赋值
    public String updateEmployee(Employee employee){
        // 调用employeeDao的save()方法，更新（修改）数据
        employeeDao.save(employee);

        // 重定向视图到employee（查询全部数据）视图请求
        return "redirect:/employee";
    }

}




















