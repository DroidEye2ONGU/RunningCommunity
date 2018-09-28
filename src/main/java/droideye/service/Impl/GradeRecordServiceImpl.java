package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import droideye.mapper.GradeRecordMapper;
import droideye.pojo.Graderecord;
import droideye.service.GradeRecordService;

@Service("gradeRecordService")
public class GradeRecordServiceImpl implements GradeRecordService {

    @Autowired
    GradeRecordMapper gradeRecordMapper;


    @Override
    public Graderecord queryGradeByPoint(Integer point) {
        Graderecord graderecord = gradeRecordMapper.queryGradeByPoint(point);
        return graderecord;
    }

    @Override
    public Integer queryGradeByPointReturnId(Integer point) {
        Integer id = gradeRecordMapper.queryGradeByPointReturnId(point);

        return id;
    }
}
