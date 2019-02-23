package cn.iustu.site.admin.controller;

import cn.iustu.site.common.entity.Admin;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminControllerAdmin {

    @Value("${iustu.login-view-url}")
    private String loginViewUrl;

    @Autowired
    private AdminService adminService;

    @GetMapping("/loginView")
    public String login(){
        return "redirect:" + loginViewUrl;
    }

    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("admin", null);
        return Result.success().setMsg("注销成功");
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(Admin admin, HttpServletRequest request){
        Admin admin1 = adminService.get();

        //如果用户名和密码输入不全，则登录失败
        if(StringUtils.isEmpty(admin.getUsername())) return Result.fail().setMsg("用户名不能为空");
        if(StringUtils.isEmpty(admin.getPassword())) return Result.fail().setMsg("密码不能为空");


        if(admin.getUsername().equals(admin1.getUsername()) && admin.getPassword().equals(admin1.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin.getUsername());
            return Result.success().setMsg("登录成功");
        }else{
            return Result.fail().setMsg("用户名或密码错误");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(Admin admin){
        Admin admin1 = adminService.get();
        if(admin1 == null){
            admin.setId(1);
            adminService.update(admin);
        }else{
            //如果参数为空，则不做更改
            if(!StringUtils.isEmpty(admin.getUsername())) admin1.setUsername(admin.getUsername());
            if(!StringUtils.isEmpty(admin.getPassword())) admin1.setPassword(admin.getPassword());
            adminService.update(admin1);
        }

        return Result.success().setMsg("修改成功");
    }
}
