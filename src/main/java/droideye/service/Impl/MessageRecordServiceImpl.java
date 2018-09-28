package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import droideye.mapper.MessageRecordMapper;
import droideye.pojo.Messagerecord;
import droideye.service.MessageRecordService;

@Service("messageRecordService")
public class MessageRecordServiceImpl implements MessageRecordService {

    @Autowired
    MessageRecordMapper messageRecordMapper;


    @Override
    public boolean addOneMessage(Messagerecord messagerecord) {
        boolean result = messageRecordMapper.addOneMessage(messagerecord);

        return result;
    }

    @Override
    public List<Messagerecord> queryAllSendersMessages(String sender) {
        List<Messagerecord> messagerecords = messageRecordMapper.queryAllSendersMessages(sender);

        return messagerecords;
    }

    @Override
    public List<Messagerecord> queryAllReceiversMessages(String receiver) {
        List<Messagerecord> messagerecords = messageRecordMapper.queryAllReceiversMessages(receiver);
        return messagerecords;
    }

    @Override
    public Messagerecord querySingleMessageRecordByMessageId(Integer messageId) {
        Messagerecord messagerecord = messageRecordMapper.querySingleMessageRecordByMessageId(messageId);
        return messagerecord;
    }

    @Override
    public boolean updateMessageStatus(Integer messageId, Integer status) {
        boolean result = messageRecordMapper.updateMessageStatus(messageId, status);
        return result;
    }

    @Override
    public void updateSenderStatus(String[] messageId, Integer senderStatus) {
        for (String id :
                messageId) {
            messageRecordMapper.updateSenderStatus(Integer.parseInt(id), 1);
        }
    }

    @Override
    public void updateReceiverStatus(String[] messageId, Integer receiverStatus) {

        for (String id :
                messageId) {
            messageRecordMapper.updateReceiverStatus(Integer.parseInt(id), receiverStatus);
        }

    }
}
