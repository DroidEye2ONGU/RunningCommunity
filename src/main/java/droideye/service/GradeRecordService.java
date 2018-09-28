package droideye.service;

import droideye.pojo.Graderecord;

public interface GradeRecordService {

    //根据积分查询级别
    public Graderecord queryGradeByPoint(Integer point);

    //根据积分查询级别,返回积分等级ID
    public Integer queryGradeByPointReturnId(Integer point);
}
