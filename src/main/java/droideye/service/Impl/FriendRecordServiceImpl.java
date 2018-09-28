package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import droideye.mapper.FriendRecordMapper;
import droideye.pojo.Friendrecord;
import droideye.service.FriendRecordService;

@Service("friendRecordService")
public class FriendRecordServiceImpl implements FriendRecordService {

    @Autowired
    FriendRecordMapper friendRecordMapper;


    @Override
    public List<Friendrecord> queryAllFriendsByNickName(String selfName) {
        List<Friendrecord> friendrecords = friendRecordMapper.queryAllFriendsByNickName(selfName);

        return friendrecords;
    }

    @Override
    public boolean addOneFriend(Friendrecord friendrecord) {
        boolean result = friendRecordMapper.addOneFriend(friendrecord);

        return result;
    }

    @Override
    public boolean deleteOneFriend(String selfName, String friendName) {
        boolean result = friendRecordMapper.deleteOneFriend(selfName, friendName);

        return result;
    }

    @Override
    public void deleteManyFriends(String selfName, String[] friends) {
        for (String friendName :
                friends) {
            //从库中删除
            deleteOneFriend(selfName, friendName);
        }
    }
}
