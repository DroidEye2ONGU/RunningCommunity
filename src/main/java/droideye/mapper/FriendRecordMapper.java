package droideye.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import droideye.pojo.Friendrecord;

public interface FriendRecordMapper {

    //根据会员名查找这个会员的所有好友
    public List<Friendrecord> queryAllFriendsByNickName(String selfName);

    // 添加一个好友
    public boolean addOneFriend(Friendrecord friendrecord);

    // 删除一个好友
    public boolean deleteOneFriend(@Param("selfName") String selfName,
                                   @Param("friendName") String friendName);
}
