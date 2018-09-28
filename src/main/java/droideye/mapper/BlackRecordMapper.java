package droideye.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import droideye.pojo.Blackrecord;

public interface BlackRecordMapper {


    //添加一个黑名单记录
    public boolean addOneBlackRecord(Blackrecord blackrecord);

    //查找某会员的所有黑名单
    public List<Blackrecord> queryAllBlackRecordsBySelfName(String selfName);

    //从黑名单中删除
    public boolean deleteFromBlackRecord(@Param("selfName") String selfName,
                                         @Param("blackName") String blackName);

}
