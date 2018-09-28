package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import droideye.common.util.JavaMail.JavaMailUtil;
import droideye.common.util.MD5;
import droideye.common.util.RandomChar;
import droideye.mapper.MemberInfoMapper;
import droideye.pojo.Blackrecord;
import droideye.pojo.Friendrecord;
import droideye.pojo.Memberinfo;
import droideye.pojo.Pointaction;
import droideye.pojo.Pointrecord;
import droideye.service.BlackRecordService;
import droideye.service.FriendRecordService;
import droideye.service.GradeRecordService;
import droideye.service.MemberService;
import droideye.service.PointActionService;
import droideye.service.PointRecordService;

import static droideye.common.util.RandomChar.RANDOM_CHAR_LOWERCASE;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    //DAO
    @Autowired
    MemberInfoMapper memberInfoMapper;

    //MD5工具类
    @Autowired
    MD5 md5;

    //JavaMail工具类
    @Autowired
    JavaMailUtil javaMailUtil;

    //积分Service
    @Autowired
    PointActionService pointActionService;
    //积分Action Service
    @Autowired
    PointRecordService pointRecordService;
    //积分等级 Service
    @Autowired
    GradeRecordService gradeRecordService;

    @Autowired
    FriendRecordService friendRecordService;

    @Autowired
    BlackRecordService blackRecordService;

    @Override
    public boolean addMember(Memberinfo memberinfo) {
        //将密码转为MD5
        String password = memberinfo.getPassword();
        System.err.println("获取到密码:" + password);

        password = md5.getMD5ofStr(password);
        System.err.println("加密后的密码:" + password);

        //将MD5密码设置到对象中
        memberinfo.setPassword(password);

        //为新用户增加20积分
        //增加获得积分记录并获取要添加的积分
        Integer point = pointRecordService.changePointRecord(PointActionService.REGISTER,
                memberinfo.getNickName());
        memberinfo.setPoint(point);

        //为新用户设置状态和注册日期
        memberinfo.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        memberinfo.setStatus(0);
        memberinfo.setIsOnline(0);
        memberinfo.setLatestDate(new Timestamp(0));

        //设置积分等级
        memberinfo.setGradeId(gradeRecordService.queryGradeByPointReturnId(memberinfo.getPoint()));

        //查看是否有推荐人,如果有,判断推荐人是否存在
        if (memberinfo.getRecommender() != null && !memberinfo.getRecommender().trim().equals("")) {
            String recommender = memberinfo.getRecommender();
            Memberinfo recommenderInfo = querySingleMemberByNickName(recommender);
            if (recommenderInfo != null) {
                System.out.println("MemberServiceImpl:register:获得推荐人:" + recommenderInfo.getNickName());
                //推荐人存在,为推荐人增加20积分RECOMMEND
                Integer recommendPoint = pointRecordService.changePointRecord(
                        PointActionService.RECOMMEND, recommender);
                //更新推荐人分数
                addPointByNickName(recommendPoint, recommenderInfo.getNickName());
            } else {
                memberinfo.setRecommender(memberinfo.getRecommender() + "注册时未找到该用户");
            }
        }

        System.err.println("MemberServiceImpl:register:将要存储用户:" + memberinfo);

        boolean result = memberInfoMapper.addMember(memberinfo);

        return result;
    }

    @Override
    public List<Memberinfo> getAllMembers() {
        List<Memberinfo> members = memberInfoMapper.getAllMemberInfo();
        return members;
    }

    @Override
    public Memberinfo querySingleMemberByNickName(String nickName) {
        Memberinfo memberinfo = memberInfoMapper.querySingleMemberByNickName(nickName);
        return memberinfo;
    }

    @Override
    public Memberinfo querySingleMemberById(Integer id) {
        Memberinfo memberinfo = memberInfoMapper.querySingleMemberById(id);

        return memberinfo;
    }

    @Override
    public boolean updateIsOnlineByNickName(Integer isOnline, String nickName) {

        if (isOnline != 1) {
            if (isOnline != 0) {
                return false;
            }
        }

        boolean result = memberInfoMapper.updateIsOnlineByNickName(isOnline, nickName);

        return result;
    }

    @Override
    public boolean updateStatusByNickName(Integer status, String nickName) {

        if (status != 1) {
            if (status != 0) {
                return false;
            }
        }

        boolean result = memberInfoMapper.updateStatusByNickName(status, nickName);

        return result;
    }

    @Override
    public boolean addPointByNickName(Integer point, String nickName) {

        boolean result = memberInfoMapper.addPointByNickName(point, nickName);

        return result;
    }

    @Override
    public boolean subPointByNickName(Integer point, String nickName) {

        boolean result = memberInfoMapper.subPointByNickName(point, nickName);

        return result;
    }

    @Override
    public boolean changeLatestOnlineTimeByNickName(String nickName, Timestamp latestTime) {
        boolean result = memberInfoMapper.changeLatestOnlineTimeByNickName(nickName, latestTime);

        return result;
    }

    /**
     * 抽象出来的改变积分的方法,可以自动记录积分变化过程,并将新的积分赋给传进来的Memberinfo对象并返回
     * 后期应该考虑增加事务支持
     *
     * @param pointAction 要查询的积分名称
     * @param nickName    要增加记录的会员昵称
     * @return 要改变的积分
     */
    @Deprecated
    private Integer changePointRecord(String pointAction, String nickName) {
        //获得相应的积分Action
        Pointaction pointaction = pointActionService.queryPointActionByActionName(pointAction);

        //获得积分Action对应的ID
        Integer pointActionId = pointaction.getId();

        //添加相应的记录
        pointRecordService.addPointRecord(new Pointrecord(nickName,
                new Timestamp(System.currentTimeMillis()), pointActionId));

        //改变相应积分
        /*Integer point = memberinfo.getPoint();
        if (point == null) {
            memberinfo.setPoint(pointaction.getPoint());
        } else {
            memberinfo.setPoint(point + pointaction.getPoint());
        }*/

        return pointaction.getPoint();
    }

    @Override
    public String findPasswordQuestionByNickname(String nickName) {
        String passwordQuestion = memberInfoMapper.findPasswordQuestionByNickname(nickName);

        return passwordQuestion;
    }

    @Override
    public String findPasswordQuestionAnswerByNickName(String nickName) {

        String passwordQuestionAnswer = memberInfoMapper.findPasswordQuestionAnswerByNickName(nickName);

        return passwordQuestionAnswer;
    }

    @Override
    public boolean changePasswordByNickName(String nickName, String password) {
        boolean result = memberInfoMapper.changePasswordByNickName(nickName, password);

        return result;
    }

    @Override
    public String rePassword(String nickName, String passwordQuestionAnswer) {
        //根据用户名查找到此人的密码提示问题
        Memberinfo memberinfo = memberInfoMapper.querySingleMemberByNickName(nickName);
        String realAnswer = memberinfo.getPasswordAnswer();


        if (realAnswer.equals(passwordQuestionAnswer.trim())) {
            //生成新的随机密码
            String newPassword = RandomChar.getChars(RANDOM_CHAR_LOWERCASE, 8);

            //生成随机密码的MD5值
            String newMD5Password = MD5.getMD5Str(newPassword);

            //将随机MD5密码更新到用户数据库
            changePasswordByNickName(nickName, newMD5Password);

            //向用户邮箱发送邮件
            String emailDes = memberinfo.getEmail();

            try {
                javaMailUtil.sendEmail(emailDes, "RunningCommunity:您的密码已重置",
                        "您的新密码为: " + newPassword + "\n\t\t请牢记您的新密码,并用该密码登陆");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return newPassword;

        } else {
            return "WrongAnswer";
        }
    }

    @Override
    public boolean checkPasswordByNickName(String nickName, String password) {

        String realMD5Pass = memberInfoMapper.queryPasswordByNickName(nickName);
        String thisMD5Pass = md5.getMD5ofStr(password);

        if (realMD5Pass.equals(thisMD5Pass)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Memberinfo modifyMemberInfo(Memberinfo memberinfo) {
        Memberinfo completedMemberInfo = memberInfoMapper.
                querySingleMemberByNickName(memberinfo.getNickName());
        String newMD5Pass = md5.getMD5ofStr(memberinfo.getPassword());

        //更新用户信息
        completedMemberInfo.setPassword(newMD5Pass);
        completedMemberInfo.setGender(memberinfo.getGender());
        completedMemberInfo.setEmail(memberinfo.getEmail());
        completedMemberInfo.setPasswordQuestion(memberinfo.getPasswordQuestion());
        completedMemberInfo.setPasswordAnswer(memberinfo.getPasswordAnswer());
        completedMemberInfo.setProvinceCity(memberinfo.getProvinceCity());
        completedMemberInfo.setAddress(memberinfo.getAddress());
        completedMemberInfo.setPhone(memberinfo.getPhone());

        boolean result = memberInfoMapper.modifyMemberInfoByNickName(completedMemberInfo);

        return completedMemberInfo;
    }

    @Override
    public Integer getOnlineCount() {
        return memberInfoMapper.getOnlineCount();
    }

    @Override
    public List<Memberinfo> getFilteredList(List<Memberinfo> allMembers, String age, String gender, String provinceCity, ModelMap modelMap) {
        //开始3次筛选
        if (!age.equals("0")) {
            modelMap.addAttribute("age", age);
            Integer integeredAge = Integer.parseInt(age);
            Iterator<Memberinfo> iterator = allMembers.iterator();
            while (iterator.hasNext()) {
                Memberinfo next = iterator.next();
                if (!(next.getAge() - (integeredAge * 10) >= 0 &&
                        next.getAge() - (integeredAge * 10) < 10)) {
                    iterator.remove();
                }
            }
        }
        if (!gender.equals("0")) {
            modelMap.addAttribute("gender", gender);
            Iterator<Memberinfo> iterator = allMembers.iterator();
            while (iterator.hasNext()) {
                if (!(iterator.next().getGender().equals(gender))) {
                    iterator.remove();
                }
            }
        }

        if (!provinceCity.equals("0")) {
            modelMap.addAttribute("provinceCity", provinceCity);
            Iterator<Memberinfo> iterator = allMembers.iterator();
            while (iterator.hasNext()) {
                if (!(iterator.next().getProvinceCity().equals(provinceCity))) {
                    iterator.remove();
                }
            }
        }

        return allMembers;
    }

    @Override//不可以用for循环,索引会出现错误
    public List<Memberinfo> getSupportedList(List<Memberinfo> allMembers, String nickName) {
        //移除自己
        for (int i = 0; i < allMembers.size(); i++) {
            if (allMembers.get(i).getNickName().equals(nickName)) {
                allMembers.remove(i);
                break;
            }
        }

        //获取当前用户的好友名字集合
        List<Friendrecord> friendRecords = friendRecordService.queryAllFriendsByNickName(nickName);
        List<String> friendNames = new ArrayList<>();
        for (Friendrecord friendrecord :
                friendRecords) {
            friendNames.add(friendrecord.getFriendName());
        }

        //获取当前用户的黑名单名字集合
        List<Blackrecord> blackrecords = blackRecordService.queryAllBlackRecordsBySelfName(nickName);
        List<String> blackNames = new ArrayList<>();
        for (Blackrecord blackrecord :
                blackrecords) {
            blackNames.add(blackrecord.getBlackName());
        }

       //删除总集合中当前用户的好友和好友
        Iterator<Memberinfo> iterator = allMembers.iterator();
        while (iterator.hasNext()) {
            Memberinfo next = iterator.next();
            if (blackNames.contains(next.getNickName()) || friendNames.contains(next.getNickName())) {
                iterator.remove();
            }
        }
        return allMembers;
    }
}
