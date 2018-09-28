package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import droideye.mapper.BlackRecordMapper;
import droideye.pojo.Blackrecord;
import droideye.service.BlackRecordService;

@Service("blackRecordService")
public class BlackRecordServiceImpl implements BlackRecordService {

    @Autowired
    BlackRecordMapper blackRecordMapper;

    @Override
    public boolean addOneBlackRecord(Blackrecord blackrecord) {
        boolean result = blackRecordMapper.addOneBlackRecord(blackrecord);
        return result;
    }

    @Override
    public void addManyToBlackRecord(String selfName, String[] blackNames) {
        for (String blackName :
                blackNames) {
            blackRecordMapper.addOneBlackRecord(new Blackrecord(selfName, blackName));
        }
    }

    @Override
    public List<Blackrecord> queryAllBlackRecordsBySelfName(String selfName) {
        List<Blackrecord> blackrecords = blackRecordMapper.queryAllBlackRecordsBySelfName(selfName);
        return blackrecords;
    }

    @Override
    public boolean deleteFromBlackRecord(String selfName, String blackName) {
        boolean result = blackRecordMapper.deleteFromBlackRecord(selfName, blackName);

        return result;
    }

    @Override
    public void deleteBlackRecordList(String selfName, String[] blackNames) {
        for (String blackName :
                blackNames) {
            blackRecordMapper.deleteFromBlackRecord(selfName, blackName);
        }
    }
}
