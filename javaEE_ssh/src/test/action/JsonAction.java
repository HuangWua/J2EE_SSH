package test.action;

import com.duan.ssh.entity.User;
import com.duan.ssh.service.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @ProjectName: javaEE_ssh
 * @Package: com.duan.ssh.action
 * @ClassName: JsonAction
 * @Author: duan
 * @Description: Json数据测试
 * @Date: 2019-05-30 22:35
 * @Version: 1.0
 */
@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
public class JsonAction extends ActionSupport {

    @Autowired
    private UserService userService;
    private String name;
    private User user;
    private Gson gson;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Action(value = "getJson",
            results = {
                    @Result(name = "success", type = "json")
            })
    public String getJson() throws Exception {

        user = new User();
        user.setDefaultPhone("981236782");
        user.setUsername("aaaasdas");
        user.setId(82);
        user.setDefaultAddress("a熬时间倒计时的哦啊接收到");
        user.setGender("男");
        user.setMoney(33333);
        user.setRole(3);
//        user = userService.getUserById(id);
        gson = new Gson();
        name = gson.toJson(user);
        return SUCCESS;
    }

}
