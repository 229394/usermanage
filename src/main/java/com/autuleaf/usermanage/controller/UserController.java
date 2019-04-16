package com.autuleaf.usermanage.controller;

import com.autuleaf.usermanage.entity.UserInfo;
import com.autuleaf.usermanage.param.UserParam;
import com.autuleaf.usermanage.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    //跳转到用户列表界面
    //start代表开始页,limit代表每页的页数
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "start",defaultValue = "0") Integer start,
                       @RequestParam(value = "limit",defaultValue = "4") Integer limit){
        start=start<0?0:start;
        //按照id倒序排序
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,limit,sort);
        Page<UserInfo> userList = userRepository.findList(pageable);
        //传入值
        model.addAttribute("users",userList);
        return "user/list";
    }

//    @GetMapping("/searchUser")
//    public String findByUserNameLike(String userName,Model model,
//                                     @RequestParam(value = "start",defaultValue = "0") Integer start,
//                                     @RequestParam(value = "limit",defaultValue = "4") Integer limit){
//        start=start<0?0:start;
//        //按照id倒序排序
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = new PageRequest(start,limit,sort);
//        Page<UserInfo> userList = userRepository.findByUserNameLike(userName,pageable);
//        if(userList == null) {
//            userList = userRepository.findList(pageable);
//        }
//        model.addAttribute("users",userList);
//        return "user/list";
//    }

    //跳转到添加界面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }

    //处理添加事件
    @RequestMapping("/add")
    public String add(UserParam userParam, BindingResult result, ModelMap modelMap){
        String errorMsg = "";
        //处理异常信息
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError e : list){
                errorMsg = errorMsg+e.getCode()+":"+e.getDefaultMessage();
            }
            modelMap.addAttribute("errorMsg",errorMsg);
            return "user/userAdd";
        }
        //处理用户名相同的情况
        UserInfo userInfo = userRepository.findByUserName(userParam.getUserName());
        if (userInfo!=null){
            modelMap.addAttribute("errorMsg","用户名已存在!");
            return "user/userAdd";
        }
        UserInfo user = new UserInfo();
        //把userParam复制给user
        BeanUtils.copyProperties(userParam,user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    //跳转到更新用户信息界面
    @RequestMapping("/toEdit")
    public String toEdit(long id,Model model){
        UserInfo userInfo = userRepository.findById(id).get();
        model.addAttribute("user",userInfo);
        return "user/userUpdate";
    }

    //跳转到用户列表界面
    @Transactional
    @RequestMapping("/edit")
    public String edit(@RequestParam("userName")String userName,
                       @RequestParam("pwd")String pwd,
                       @RequestParam("age")int age,@RequestParam("id")long id){
        userRepository.update(userName,pwd,age,new Date(),id);
        return "redirect:/list";
    }

    //删除每一个用户
    @Transactional
    @RequestMapping("/delete")
    public String delete(@RequestParam("id")long id){
        userRepository.deleteById(id);
        return "redirect:/list";
    }

}
