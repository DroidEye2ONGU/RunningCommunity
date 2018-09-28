package droideye.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;

import java.sql.Timestamp;
import java.util.List;

import droideye.pojo.Memberinfo;

public interface MemberService {

    // 添加用户
    public boolean addMember(Memberinfo memberinfo);

    // 查询所有用户
    public List<Memberinfo> getAllMembers();

    //根据昵称来查询单个用户
    public Memberinfo querySingleMemberByNickName(String nickName);

    //根据ID来查询单个用户
    public Memberinfo querySingleMemberById(Integer id);

    //更改上下或下线状态
    public boolean updateIsOnlineByNickName(Integer isOnline, String nickName);

    //更改用户状态
    public boolean updateStatusByNickName(Integer status, String nickName);

    //增加积分
    public boolean addPointByNickName(Integer point, String nickName);

    //减少积分
    public boolean subPointByNickName(Integer point, String nickName);

    //更改上一次在线时间
    public boolean changeLatestOnlineTimeByNickName(@Param("selfName") String nickName,
                                                    @Param("time") Timestamp latestTime);

    //根据用户名查找密码提示问题
    public String findPasswordQuestionByNickname(String nickName);

    //根据用户名查找密码提示问题答案
    public String findPasswordQuestionAnswerByNickName(String nickName);

    //更改密码
    public String rePassword(String nickName, String passwordQuestionAnswer);

    //根据用户名更改密码
    public boolean changePasswordByNickName(@Param("selfName") String nickName,
                                            @Param("newPass") String password);

    //根据用户名查询用户密码
    public boolean checkPasswordByNickName(String nickName,String password);

    //更改用户信息
    public Memberinfo modifyMemberInfo(Memberinfo memberinfo);

    //获取在线人数
    public Integer getOnlineCount();

    //过滤集合中的用户
    public List<Memberinfo> getFilteredList(List<Memberinfo> memberinfoList, String age,
                                            String gender, String provinceCity, ModelMap modelMap);

    //移除集合中不符合条件的用户
    public List<Memberinfo> getSupportedList(List<Memberinfo> memberinfoList, String selfName);
}
