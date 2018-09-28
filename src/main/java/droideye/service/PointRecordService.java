package droideye.service;

import java.util.List;

import droideye.pojo.Pointrecord;

public interface PointRecordService {

    //添加积分操作
    public boolean addPointRecord(Pointrecord pointrecord);

    //通过会员昵称查找积分记录
    public List<Pointrecord> queryPointRecordsByNickName(String nickName);

    /**
     * 抽象出来的改变积分的方法,可以自动记录积分变化过程,并将新的积分赋给传进来的Memberinfo对象并返回
     * 后期应该考虑增加事务支持
     *
     * @param pointAction 要查询的积分名称
     * @param nickName 要增加记录的会员昵称
     *
     * @return 要改变的积分
     * */
    Integer changePointRecord(String pointAction,String nickName);
}
