package droideye.controller.Member;

import com.alibaba.druid.sql.visitor.functions.If;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import droideye.common.util.MD5;
import droideye.common.util.ProvinceUtil;
import droideye.pojo.Memberinfo;
import droideye.pojo.Memberspace;
import droideye.service.GradeRecordService;
import droideye.service.MemberService;
import droideye.service.MemberSpaceService;

@SessionAttributes(value = {"username", "user"})
@RequestMapping("/member")
@Controller
public class MemberInfoController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberSpaceService memberSpaceService;

    @Autowired
    GradeRecordService gradeRecordService;

    @Autowired
    MD5 md5;

    //@ModelAttribute("username")
    //public String getUsername() {
    //    return "";
    //}
    //
    //@ModelAttribute("password")
    //public String getPassword() {
    //    return "";
    //}

    /**
     * 用于用户登录
     *
     * @param username             从前端或cookie获取到的用户名
     * @param password             从前端或cookie获取到的密码
     * @param isFromPageController 是否是从pageController转发过来的,如果是值为true,否则为null
     *                             该参数主要影响当登录失败后的提示信息
     * @return 视图路径
     */
    @RequestMapping("/login")
    public String memberLogin(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "isFromPageController", required = false) Boolean isFromPageController,
                              @RequestParam(value = "autoLogin", required = false) String checkboxValue,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        System.err.println("MemberInfoController-memberLogin:username:" + username);
        System.err.println("MemberInfoController-memberLogin:password:" + password);
        System.err.println("MemberInfoController-memberLogin:isFromPageController:" + isFromPageController);
        System.err.println("MemberInfoController-memberLogin:checkboxValue:" + checkboxValue);

        //将收到的密码转换为MD5
        String md5Pass = md5.getMD5ofStr(password.trim());

        System.out.println("MemberInfoController-memberLogin:md5Pass:" + md5Pass);

        //创建一个错误提示容器
        List<String> errorList = new ArrayList<>();

        Memberinfo memberinfo = memberService.querySingleMemberByNickName(username);

        //如果未找到,则加入提示信息并返回登录页面
        if (memberinfo == null) {
            errorList.add("未找到该用户,请检查您的用户名");
            map.put("errorMessages", errorList);
            return "login";
        }

        if (memberinfo.getNickName().equals(username.trim()) &&
                memberinfo.getPassword().equals(md5Pass.trim())) {
            //更新数据库用户登陆状态信息
            memberService.updateIsOnlineByNickName(1, memberinfo.getNickName());
            //更新数据库用户中的上一次登录时间
            memberService.changeLatestOnlineTimeByNickName(username, new Timestamp(System.currentTimeMillis()));


            //登录成功,将用户名和用户信息存入session
            map.put("username", username);
            map.put("user", memberinfo);

            //查询积分等级并装入request 由PageController处理
            //String gradename = gradeRecordService.queryGradeByPoint(memberinfo.getPoint()).getGradename();
            //map.put("gradename", gradename);

            //判断是否点选自动登录,如果点选将信息存入cookie
            if (checkboxValue != null && checkboxValue.equals("0")) {
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);

                usernameCookie.setMaxAge(60 * 60 * 24 * 14);
                passwordCookie.setMaxAge(60 * 60 * 24 * 14);

                //由于当前在/member路径下,因此如果不主动设置cookie路径为/,这两个cookie的路径也会为/member
                //这样由于获取cookie的PageController在/下,获取到的cookie就不会包括/member下的cookie
                usernameCookie.setPath("/");
                passwordCookie.setPath("/");

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            //跳转到首页
            //return "member/activity";
            return "redirect:/memebr/toMemberActivityPage";
        } else {
            if (isFromPageController != null && isFromPageController) {
                //判断cookie中的用户名或密码失败
                errorList.add("您的本地登录信息错误,请重新登陆");
                map.put("errorMessages", errorList);
                return "login";
            } else {
                //判断用户输入的用户名或密码错误
                errorList.add("您输入的用户名或密码错误,请检查");
                map.put("errorMessages", errorList);
                return "login";
            }
        }
    }

    @RequestMapping("/logout")
    public String memberLogout(@SessionAttribute("username") String username,
                               @SessionAttribute("user") Memberinfo user,
                               HttpSession session) {
        System.out.println("MemberInfoController:memberLogout:进入方法");
        System.out.println("MemberInfoController:memberLogout:user:" + user);
        System.out.println("MemberInfoController:memberLogout:username:" + username);


        if (user != null && username != null) {
            //直接销毁该用户session
            session.invalidate();
        }

        // 更改用户在数据库中的登录状态
        memberService.updateIsOnlineByNickName(0, username);
        // 更改用户的上一次在线状态
        memberService.changeLatestOnlineTimeByNickName(username, new Timestamp(System.currentTimeMillis()));

        if (session != null) {
            System.out.println("MemberInfoController:memberLogout:session:" + session);
        }

        //返回未登录页面
        return "login";
    }

    @RequestMapping("/register")
    public String memberRegister(@Valid Memberinfo memberinfo,
                                 BindingResult bindingResult,
                                 @RequestParam(value = "repassword", required = false) String repassword,
                                 Map<String, Object> map) {

        System.out.println("MemberInfoController:memberRegister:进入方法");

        System.out.println("MemberInfoController:memberRegister:memberinfo对象:" + memberinfo);
        System.out.println("MemberInfoController:memberRegister:repassword:" + repassword);

        int errorCount = bindingResult.getErrorCount();
        System.out.println("MemberInfoController:memberRegister:有" + errorCount + "个错误");

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        /*for (FieldError error :
                fieldErrors) {
            String errorName = error.getField();//获取错误的属性名
            String errorMessage = error.getDefaultMessage();//获取错误信息
            System.out.println("MemberInfoController:memberRegister:" +
                    "发现错误:" + errorName + ",错误信息:" + errorMessage);
        }*/

        if (errorCount > 0) {
            //如果存在错误,直接返回注册页面
            map.put("provinces", ProvinceUtil.getProvinces());
            return "register";
        }

        if (memberinfo.getPassword().equals(repassword.trim())) {
            //保存MD5转换前的原始密码
            String password = memberinfo.getPassword();
            //如果两次密码输入相同,继续注册
            boolean result = memberService.addMember(memberinfo);
            System.out.println("MemberInfoController:memberRegister:开始添加会员,结果为:" + result);
            //通过pageHelper来进行登陆
            map.put("username", memberinfo.getNickName());
            map.put("password", password);

            return "loginHelper";
        } else {
            //bindingResult.addError(new ObjectError("repasswordwrong", "两次密码输入不同"));
            bindingResult.addError(new FieldError("registererror",
                    "repasswordwrong", "两次密码输入不同"));
            return "register";
        }

    }

    @ResponseBody
    @RequestMapping("/sameNickNameCheck")
    public String sameNickNameCheck(String username) {
        System.out.println("MemberInfoController:sameNickNameCheck:username:" + username);

        Memberinfo memberinfo = memberService.querySingleMemberByNickName(username);

        if (memberinfo == null) {
            return "1";
        } else {
            return "0";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/findPasswordQuestion", produces = "application/json; charset=utf-8")
    public String findPasswordQuestion(String nickName) {

        System.out.println(nickName);
        //先查询用户是否存在
        Memberinfo memberinfo = memberService.querySingleMemberByNickName(nickName);

        System.out.println(memberinfo);

        if (memberinfo == null) {
            return "0";
        } else {

            return memberinfo.getPasswordQuestion();
        }


    }

    @RequestMapping("/rePassword")
    public String rePassword(@RequestParam("userName") String username,
                             @RequestParam("passwdQuestion") String passwdQuestion,
                             @RequestParam("passwdAnswer") String passwdAnswer,
                             HttpServletResponse response, Map<String, Object> map,
                             RedirectAttributes redirectAttributes
    ) {
        System.out.println("MemberInfoController:rePassword");
        System.out.println("MemberInfoController:rePassword:用户名" + username);
        System.out.println("MemberInfoController:rePassword:密码问题" + passwdQuestion);
        System.out.println("MemberInfoController:rePassword:密码问题答案" + passwdAnswer);


        //调用service来处理重置密码
        String result = memberService.rePassword(username, passwdAnswer);

        if (result.equals("WrongAnswer")) {
            //如果密码提示问题错误,加入map,返回找回密码页面
            map.put("answer", "密码问题验证错误,请检查您的答案");
            return "passwordMissing";
        } else {
            //可以直降将参数带到最后的视图页面\

            redirectAttributes.addFlashAttribute("repasswordflag", "233");
            return "redirect:/toLoginPage";
        }

    }

    @ResponseBody
    @RequestMapping("/checkPassword")
    public String checkPassword(@RequestParam("password") String password,
                                @SessionAttribute("username") String username) {

        boolean result = memberService.checkPasswordByNickName(username, password);

        if (result) {
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping("/updateMemberinfo")
    public String modifyMemberInfo(@Valid Memberinfo memberinfo,
                                   BindingResult bindingResult,
                                   ModelMap modelMap,
                                   RedirectAttributes redirectAttributes) {

        System.out.println("MemberInfoController:modifyMemberInfo:memberinfo:" + memberinfo);

        for (FieldError error :
                bindingResult.getFieldErrors()) {
            System.out.println(error.getField() + " : " + error.getDefaultMessage());
        }

        if (bindingResult.getErrorCount() > 0) {
            if (!(bindingResult.getErrorCount() == 1 && bindingResult.getFieldError("age") != null)) {
                return "member/modify";
            }
        }

        //开始更新用户信息
        Memberinfo completeMemberinfo = memberService.modifyMemberInfo(memberinfo);

        //更新session
        modelMap.addAttribute("user", completeMemberinfo);
        redirectAttributes.addFlashAttribute("modifyOKFlag", true);

        return "redirect:/member/toModifyPage";
    }

    @RequestMapping("/createOrUpdateMemberSpace")
    public String createOrUpdateMemberSpace(@RequestParam("opinion") String opinion,
                                    @RequestParam("runTime") String runTime,
                                    @RequestParam("runHabit") String runHabit,
                                    @RequestParam("runStar") String runStar,
                                    @RequestParam("cellPhone") String cellPhone,
                                    @RequestParam("runPlace") String runPlace,
                                    @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
                                    @SessionAttribute("user") Memberinfo memberinfo,
                                    Map<String, Object> map) throws IOException {
        System.out.println("MemberInfoController:createMemberSpace");
        System.out.println(opinion + " " + runTime + " " + runHabit + " " + runStar + " " +
                cellPhone + " " + runPlace + " " + memberinfo.getId());
        System.out.println(iconFile.getOriginalFilename());
        //生成一个Memberspace对象并为其赋值,传给service进行处理
        Memberspace memberspace = new Memberspace(runPlace, runTime, runHabit, runStar,
                cellPhone, opinion, memberinfo.getId());

        if (iconFile.getOriginalFilename() != "") {
            System.out.println("iconFile:" + iconFile.getOriginalFilename());
            String fileName = iconFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

            //生成UUID
            String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "");
            String completeUUIDFileName = uuidFileName + "." + fileType;

            System.out.println("fileName:" + fileName);
            System.out.println("fileType:" + fileType);
            System.out.println("completeUUIDFileName:" + completeUUIDFileName);

            memberspace.setIcon(completeUUIDFileName);

            //将文件写到目录
            iconFile.transferTo(
                    new File("C:\\Users\\DroidEye\\Desktop\\Programme\\IdeaProjects\\RunningCommunity" +
                            "\\src\\main\\webapp\\WEB-INF\\upload\\" + completeUUIDFileName));
        }

        Memberspace memberspaceInDB = memberSpaceService.queryMemberSpaceByMemberId(memberinfo.getId());

        if (memberspaceInDB == null) {
            //库中没有该用户的memberspace,创建一个
            //调用service存储
            memberSpaceService.addMemberSpace(memberspace);
            //由于更新了积分,将用户重新写入session
            Memberinfo newMemberinfo = memberService.querySingleMemberByNickName(memberinfo.getNickName());
            System.out.println("newMemberInfo:" + newMemberinfo);
            map.put("user", newMemberinfo);
        } else {
            //库中已有memberspace,更新
            //如果上传了新的照片,则直接更新,若没有上传则用旧的数据
            if (iconFile.getOriginalFilename() == "") {
                memberspace.setIcon(memberspaceInDB.getIcon());
            }
            memberSpaceService.modifyMemberSpace(memberspace);
        }
        return "redirect:/member/toSpacePage";
    }

}
