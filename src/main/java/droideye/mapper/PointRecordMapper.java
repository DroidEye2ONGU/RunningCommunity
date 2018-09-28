package droideye.mapper;

import java.util.List;

import droideye.pojo.Pointrecord;

public interface PointRecordMapper {

    //添加积分操作
    public boolean addPointRecord(Pointrecord pointrecord);

    //通过会员昵称查找积分记录
    public List<Pointrecord> queryPointRecordsByNickName(String nickName);
}
