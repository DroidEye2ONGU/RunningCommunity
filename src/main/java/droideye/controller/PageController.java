package droideye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;

import droideye.common.util.AgeMapUtil;
import droideye.common.util.ProvinceUtil;
import droideye.common.util.ip.IPSeeker;
import droideye.pojo.Blackrecord;
import droideye.pojo.Friendrecord;
import droideye.pojo.Graderecord;
import droideye.pojo.Memberinfo;
import droideye.pojo.Memberspace;
import droideye.pojo.Messagerecord;
import droideye.service.BlackRecordService;
import droideye.service.FriendRecordService;
import droideye.service.GradeRecordService;
import droideye.service.MemberService;
import droideye.service.MemberSpaceService;
import droideye.service.MessageRecordService;


@Controller
public class PageController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberSpaceService memberSpaceService;

    @Autowired
    GradeRecordService gradeRecordService;

    @Autowired
    FriendRecordService friendRecordService;

    @Autowired
    BlackRecordService blackRecordService;

    @Autowired
    MessageRecordService messageRecordService;

    @RequestMapping("/toLoginPage")
    public String toLoginPage() {
        return "login";
    }

    /**
     * index跳转到home,判断cookie是否有用户名和密码,如果有,转到登陆进行登陆
     * 若没有,转到登录页面
     */
    @RequestMapping(value = "/checkAutoLogin", method = RequestMethod.GET)
    public String checkAutoLogin(@CookieValue(value = "username", required = false) String username,
                                 @CookieValue(value = "password", required = false) String password,
                                 Map<String, Object> map) {
        System.out.println("PageController-checkAutoLogin:username:" + username);
        System.out.println("PageController-checkAutoLogin:password:" + password);

        //如果找到cookie中的信息,取出存入map并转为到login方法进行处理自动登陆
        if (username != null && password != null) {

            map.put("username", username);
            map.put("password", password);

            //设置是从PageController转发过去的
            map.put("isFromPageController", new Boolean(true));
            //return "forward:/member/login";
            return "loginHelper";
        }

        //若没有找到完成信息则跳转到登录页面让用户登陆
        return "login";
    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping("/toRegisterPage")
    public String toRegisterPage(Map<String, Object> map) {
        map.put("memberinfo", new Memberinfo());
        map.put("provinces", ProvinceUtil.getProvinces());
        return "register";
    }

    /**
     * 跳转到找回密码页面
     */
    @RequestMapping("/toFindPasswordPage")
    public String toFindPasswordPage() {
        return "passwordMissing";
    }

    @RequestMapping("/member/toModifyPage")
    public String toModifyPage(@SessionAttribute("user") Memberinfo memberinfo,
                               Map<String, Object> map) {


        //查询当前用户并放入map
        map.put("memberinfo", memberinfo);
        //map.put("provinces",ProvinceUtil.getProvinces());

        return "/member/modify";
    }

    /**
     * 跳转到个人空间页面
     */
    @RequestMapping("/member/toSpacePage")
    public String toSpacePage(@SessionAttribute("user") Memberinfo memberinfo, ModelMap modelMap) {
        System.out.println("PageController:toSpacePage:memberinfo:" + memberinfo);
        /*
         * 判断用户是否有个性空间,如果有,初始化参数并转到个性空间,否则跳转到没有个性空间页面进行提示
         * */
        Integer memberId = memberinfo.getId();
        Memberspace memberspace = memberSpaceService.queryMemberSpaceByMemberId(memberId);

        System.out.println("PageController:toSpacePage:memberspace:" + memberspace);

        if (memberspace == null) {
            return "/member/noSpace";
        } else {
            //将memberspace信息放入request
            modelMap.addAttribute("memberspace", memberspace);
            //查询积分等级
            Graderecord graderecord = gradeRecordService.queryGradeByPoint(memberinfo.getPoint());
            modelMap.addAttribute("graderecord", graderecord);
            return "/member/space";
        }
    }

    @RequestMapping("/member/toCreateSpacePage")
    public String toCreateSpacePage(@SessionAttribute("user") Memberinfo memberinfo,
                                    ModelMap modelMap) {
        //获取用户ID
        Integer memberId = memberinfo.getId();

        //查找此人的memberspace
        Memberspace memberspace = memberSpaceService.queryMemberSpaceByMemberId(memberId);

        //将emberspace放入request
        modelMap.addAttribute("memberspace", memberspace);
        return "/member/createSpace";
    }

    @RequestMapping("/memebr/toMemberActivityPage")
    public String toMemberActivityPage(@SessionAttribute("user") Memberinfo memberinfo,
                                       HttpServletRequest request,
                                       Map<String, Object> map) {
        //获取当前用户的积分等级
        String gradename = gradeRecordService.queryGradeByPoint(memberinfo.getPoint()).getGradename();

        //获取用户的客户端地址
        String ip = request.getRemoteAddr();
        IPSeeker ipSeeker = IPSeeker.getInstance();
        String area = ipSeeker.getArea(ip);
        String country = ipSeeker.getCountry(ip);
        String address = country + " " + area;

        System.out.println("ip:" + ip + " address:" + address);

        //获取应用访问人数
        ServletContext application = request.getServletContext();
        Integer sumPeople = (Integer) application.getAttribute("sumPeople");
        if (sumPeople == null) {
            sumPeople = 1;
        } else {
            sumPeople++;
        }
        application.setAttribute("sumPeople", sumPeople);

        //获取所有在线人数
        Integer onlineCount = memberService.getOnlineCount();

        map.put("onlineCount", onlineCount);
        map.put("gradename", gradename);
        map.put("address", address);

        return "member/activity";
    }

    @RequestMapping("/member/toBuddyListPage")
    public String toBuddyListPage(@SessionAttribute("username") String username,
                                  ModelMap modelMap) {

        //查找出此人的所有好友
        List<Friendrecord> friendRecords = friendRecordService.queryAllFriendsByNickName(username);

        //遍历并获取好友的Memberinfo对象集合
        List<Memberinfo> friends = new ArrayList<>();

        for (Friendrecord friend :
                friendRecords) {
            Memberinfo memberinfo = memberService.querySingleMemberByNickName(friend.getFriendName());
            friends.add(memberinfo);
        }


        //将朋友集合加入request
        modelMap.addAttribute("friends", friends);

        return "messenger/buddyList";
    }

    @RequestMapping("/member/toBlackListPage")
    public String toBlackListPage(@SessionAttribute("username") String nickName,
                                  ModelMap modelMap) {
        //查出此人的所有黑名单
        List<Blackrecord> blackRecords = blackRecordService.queryAllBlackRecordsBySelfName(nickName);

        //遍历并获取黑名单的Memberinfo集合
        List<Memberinfo> blacks = new ArrayList<>();
        for (Blackrecord blackrecord :
                blackRecords) {
            Memberinfo memberinfo = memberService.querySingleMemberByNickName(blackrecord.getBlackName());
            blacks.add(memberinfo);
        }

        modelMap.addAttribute("blackRecords", blacks);

        return "messenger/blackList";
    }

    @RequestMapping("/member/toMatchFriendPage")
    public String toMatchFriendPage(@SessionAttribute("username") String nickName,
                                    ModelMap modelMap) {

        List<Memberinfo> allMembers = memberService.getSupportedList(
                memberService.getAllMembers(), nickName);

        modelMap.addAttribute("allMembers", allMembers);
        modelMap.addAttribute("provinces", ProvinceUtil.getProvinces());

        return "/messenger/matchFriend";
    }

    @RequestMapping("/member/toMatchFriendPageByExactSearch")
    public String toMatchFriendPageByExactSearch(@SessionAttribute("username") String nickName,
                                                 @RequestParam("age") String age,
                                                 @RequestParam("gender") String gender,
                                                 @RequestParam("provinceCity") String provinceCity,
                                                 ModelMap modelMap) {

        List<Memberinfo> allMembers = memberService.getFilteredList(memberService.getAllMembers(),
                age, gender, provinceCity, modelMap);

        allMembers = memberService.getSupportedList(allMembers, nickName);


        modelMap.addAttribute("allMembers", allMembers);
        modelMap.addAttribute("provinces", ProvinceUtil.getProvinces());
        modelMap.addAttribute("ageMap", AgeMapUtil.getAgeMap());

        return "/messenger/matchFriend";
    }

    @RequestMapping("/member/toMessagePage")
    public String toMessagePage(@SessionAttribute("username") String receiver,
                                ModelMap modelMap) {

        List<Messagerecord> messagerecordList = messageRecordService.queryAllReceiversMessages(receiver);
        System.out.println("PageController:toMessagePage:messages:");
        for (Messagerecord record :
                messagerecordList) {
            System.out.println(record);
        }
        modelMap.addAttribute("messages", messagerecordList);


        return "/messenger/inbox";
    }

    @RequestMapping("/member/toSendMessagePage")
    public String toSendMessagePage(@SessionAttribute("username") String sender,
                                    ModelMap modelMap) {
        List<Messagerecord> messagerecordList = messageRecordService.queryAllSendersMessages(sender);
        modelMap.addAttribute("sendedMessages", messagerecordList);

        return "messenger/outbox";
    }

    @RequestMapping("/member/toWriteMessagePage")
    public String toWriteMessagePage(@RequestParam(value = "receiver", required = false) String receiver,
                                     ModelMap modelMap) {

        if (receiver != null) {
            modelMap.addAttribute("receiver", receiver);
        }

        return "messenger/sendInfo";
    }

}
