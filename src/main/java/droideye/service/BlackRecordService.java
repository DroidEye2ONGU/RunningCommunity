package droideye.service;

import java.util.List;

import droideye.pojo.Blackrecord;

public interface BlackRecordService {
    //添加一个黑名单记录
    public boolean addOneBlackRecord(Blackrecord blackrecord);

    //把名单中的所有人添加到黑名单
    public void addManyToBlackRecord(String selfName, String[] blackNames);

    //查找某会员的所有黑名单
    public List<Blackrecord> queryAllBlackRecordsBySelfName(String selfName);

    //从黑名单中移出
    public boolean deleteFromBlackRecord(String selfName, String blackName);

    //根据传来的黑名单把名单的人全部删除
    public void deleteBlackRecordList(String selfName, String[] blackNames);
}
