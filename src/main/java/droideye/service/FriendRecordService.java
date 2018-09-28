package droideye.service;

import java.util.List;

import droideye.pojo.Friendrecord;

public interface FriendRecordService {

    //根据会员名查找这个会员的所有好友
    public List<Friendrecord> queryAllFriendsByNickName(String selfName);

    // 添加一个好友
    public boolean addOneFriend(Friendrecord friendrecord);

    // 删除一个好友
    public boolean deleteOneFriend(String selfName, String friendName);

    // 将名单中的好友全部删除
    public void deleteManyFriends(String selfName, String[] friends);
}
