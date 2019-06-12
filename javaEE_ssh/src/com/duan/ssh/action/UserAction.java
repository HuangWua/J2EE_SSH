package com.duan.ssh.action;

import cn.hutool.captcha.LineCaptcha;
import com.duan.ssh.entity.Order;
import com.duan.ssh.entity.Shoppingcart;
import com.duan.ssh.entity.User;
import com.duan.ssh.model.PageBean;
import com.duan.ssh.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.action
 * @ClassName: UserAction
 * @Author: duan
 * @Description: 用户操作
 * @Date: 2019-05-29 13:40
 * @Version: 1.0
 */
@Controller
@Scope("prototype")
@ParentPackage("custom_default")
@Namespace("/user")
@Results({
        @Result(name = "success", type = "json"),
        @Result(name = "error", location = "/error.jsp")
})
public class UserAction extends ActionSupport {
    @Autowired
    private UserService userService;
    //用户信息
    private User user;
    //验证码信息
    private String verifyCode;

    private List<User> userList;
    //数据操作结果
    private int result;
    //用户购物车
    private Set<Shoppingcart> setSc;
    //用户订单
    private Set<Order> setO;
    //注册名称
    private String registerName;

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public Set<Order> getSetO() {
        return setO;
    }

    public void setSetO(Set<Order> setO) {
        this.setO = setO;
    }

    public Set<Shoppingcart> getSetSc() {
        return setSc;
    }

    public void setSetSc(Set<Shoppingcart> setSc) {
        this.setSc = setSc;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }


    //
    @Action(value = "register", results = {
            @Result(name = "success", type = "json", params = {"root", "result"}),
            @Result(name = "error", location = "/jsp/user/login.jsp", type = "redirect")
    })
    public String register() throws Exception {

        user.setRole(0);
        user.setMoney(0);

        result = userService.registerUser(user);

        return "success";

    }


    //
    @Action(value = "loginVerifyCode")
    public String loginVerifyCode() throws IOException {

        ActionContext actionContext = ActionContext.getContext();
        Map session = actionContext.getSession();

        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            LineCaptcha shearCaptcha = new LineCaptcha(200, 100);
            session.put("verifyCode", shearCaptcha.getCode());
            verifyCode = shearCaptcha.getCode();
            shearCaptcha.write(response.getOutputStream());
        } finally {
            response.getOutputStream().close();
        }

        return NONE;
    }


    //
    @Action(value = "selectUserById", results = {
            @Result(name = "success", type = "json", params = {"root", "user"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String selectUserById() throws Exception {
        user = userService.getUserById(user.getId());
        return "success";
    }


    //
    @Action(value = "selectUserByName", results = {
            @Result(name = "success", type = "json", params = {"root", "user"})
    })
    public String selectUserByName() throws Exception {
        user = userService.selectUserByName(registerName);
        return "success";
    }


    //
    @Action(value = "login", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    })
    public String login() throws Exception {

        String code = (String) ServletActionContext.getRequest().getSession().getAttribute("verifyCode");

        if (verifyCode.equals(code)) {

            User user1 = userService.selectUserByName(user.getUsername());
            if (user1 != null) {
                if (user1.getPassword().equals(user.getPassword())) {
                    ServletActionContext.getRequest().getSession().setAttribute("user", user1);
                    result = 1;
                    return "success";
                } else
                    result = 0;
            } else {
                result = 0;
            }
        } else {
            result = 2;
        }

        return "success";
    }


    //
    @Action(value = "selectSc", results = {
            @Result(name = "success", type = "json", params = {"root", "setSc"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String selectSc() throws Exception {

        setSc = userService.selectSc(user.getId());

        return "success";
    }


    //
    @Action(value = "updateUser", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String updateUser() throws Exception {

        result = userService.updateUser(user);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "selectOrders", results = {
            @Result(name = "success", type = "json", params = {"root", "setO"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String selectOrders() throws Exception {

        setO = userService.selectOrders(user.getId());

        return "success";
    }


    //
    @Action(value = "selectAllUser", results = {
            @Result(name = "success", type = "json", params = {"root", "userList"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String selectAllUser() throws Exception {

        userList = userService.selectAllUser();

        return "success";
    }


    //
    @Action(value = "deleteUser", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String deleteUser() throws Exception {

        result = userService.deleteUser(user);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "insertUser", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String insertUser() throws Exception {

        result = userService.registerUser(user);

        return result == 0 ? "error" : "success";
    }


    //
    @Action(value = "selectUserLikeName", results = {
            @Result(name = "success", type = "json", params = {"root", "userList"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String selectUserLikeName() throws Exception {

        userList = userService.selectUserLikeName(registerName);

        return "success";

    }


    //
    @Action(value = "selectUserByRole", results = {
            @Result(name = "success", type = "json", params = {"root", "userList"})
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String selectUserByRole() throws Exception {

        userList = userService.selectUserByRole(user.getRole());

        return "success";

    }


    //
    @Action(value = "userPayment", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String userPayment() throws Exception {

        result = userService.userPayment(user);

        return "success";
    }


//    //
//    @Action(value = "checkMoney")
//    public String checkMoney() {
//
//        result=userService.checkmoney
//
//        return "success";
//    }


    //
    @Action(value = "toManager", results = {
            @Result(name = "success", location = "/WEB-INF/manager.jsp")
    }, interceptorRefs = {
            @InterceptorRef("managerStack")
    })
    public String toManager() {

        return "success";

    }


    //
    @Action(value = "logOut", results = {
            @Result(name = "success", type = "json", params = {"root", "result"})
    }, interceptorRefs = {
            @InterceptorRef("loginStack")
    })
    public String logOut() {

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("user");

        user = (User) session.getAttribute("user");

        return "success";
    }


    private File upload;
    private String uploadFileName;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }


//    //
//    @Action(value = "uploadFile", results = {
//            @Result(name = "success", type = "json", params = {"root", "url"})
//    })
//    public String uploadFile() {
//
//        String pic_path = "F:\\idea\\IdeaProjects\\uploadFile";
//
//        File file = new File(pic_path);
//
//        String newFileName = UUID.randomUUID() + uploadFileName.substring(uploadFileName.lastIndexOf("."));
//
//        FileUtil.copyFile(upload, new File(file, newFileName));
//
//
//
//        return "success";
//
//    }


    //
    private PageBean<User> pageBean = new PageBean<>();

    public PageBean<User> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<User> pageBean) {
        this.pageBean = pageBean;
    }


    //
    @Action(value = "selectuserByPage", results = {
            @Result(name = "success", type = "json", params = {"root", "pageBean"})
    })
    public String selectUserByPage() throws Exception {

        pageBean = userService.getUserByPage(pageBean.getPageNum(), pageBean.getPageSize());

        return "success";
    }


}
