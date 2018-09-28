package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import droideye.mapper.PointRecordMapper;
import droideye.pojo.Pointaction;
import droideye.pojo.Pointrecord;
import droideye.service.PointActionService;
import droideye.service.PointRecordService;

@Service("pointRecordService")
public class PointRecordServiceImpl implements PointRecordService {

    @Autowired
    PointRecordMapper pointRecordMapper;

    //积分Service
    @Autowired
    PointActionService pointActionService;

    @Override
    public boolean addPointRecord(Pointrecord pointrecord) {

        boolean result = pointRecordMapper.addPointRecord(pointrecord);

        return result;
    }

    @Override
    public List<Pointrecord> queryPointRecordsByNickName(String nickName) {

        List<Pointrecord> result = pointRecordMapper.queryPointRecordsByNickName(nickName);

        return result;
    }

    @Override
    public Integer changePointRecord(String pointAction, String nickName) {
        //获得相应的积分Action
        Pointaction pointaction = pointActionService.queryPointActionByActionName(pointAction);

        //获得积分Action对应的ID
        Integer pointActionId = pointaction.getId();

        //添加相应的记录
        addPointRecord(new Pointrecord(nickName,
                new Timestamp(System.currentTimeMillis()), pointActionId));

        return pointaction.getPoint();
    }
}
