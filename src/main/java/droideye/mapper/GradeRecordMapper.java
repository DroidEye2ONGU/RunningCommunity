package droideye.mapper;


import droideye.pojo.Graderecord;

public interface GradeRecordMapper {

    //根据积分查询级别
    public Graderecord queryGradeByPoint(Integer point);


    //根据积分查询级别,返回积分等级ID
    public Integer queryGradeByPointReturnId(Integer point);
}
