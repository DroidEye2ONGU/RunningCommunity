package droideye.mapper;

import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

import droideye.pojo.Memberinfo;

public interface MemberInfoMapper {

    /*
     * 获得所有用户
     * */
    public List<Memberinfo> getAllMemberInfo();

    /*
     * 注册用户
     * */
    public boolean addMember(Memberinfo memberinfo);

    //根据昵称来查询单个用户
    public Memberinfo querySingleMemberByNickName(String nickName);

    //根据ID来查询单个用户
    public Memberinfo querySingleMemberById(Integer id);

    //更改上下或下线状态
    public boolean updateIsOnlineByNickName(@Param("isOnline") Integer isOnline,
                                            @Param("nickName") String nickName);

    //更改用户状态
    public boolean updateStatusByNickName(@Param("status") Integer status,
                                          @Param("nickName") String nickName);

    //增加积分
    public boolean addPointByNickName(@Param("point") Integer point,
                                      @Param("nickName") String nickName);

    //减少积分
    public boolean subPointByNickName(@Param("point") Integer point,
                                      @Param("nickName") String nickName);

    //更改上一次在线时间
    public boolean changeLatestOnlineTimeByNickName(@Param("nickName") String nickName,
                                                    @Param("time") Timestamp latestTime);

    //根据用户名查找密码提示问题
    public String findPasswordQuestionByNickname(String nickName);

    //根据用户名查找密码提示问题答案
    public String findPasswordQuestionAnswerByNickName(String nickName);

    //根据用户名更改密码
    public boolean changePasswordByNickName(@Param("nickName") String nickName,
                                            @Param("newPass") String password);

    //根据用户名查询用户密码
    public String queryPasswordByNickName(String nickName);

    //根据用户名更新用户信息
    public boolean modifyMemberInfoByNickName(Memberinfo memberinfo);

    //获取在线人数
    public Integer getOnlineCount();
}
