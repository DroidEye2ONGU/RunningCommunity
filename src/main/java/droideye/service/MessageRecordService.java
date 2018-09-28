package droideye.service;


import java.util.List;

import droideye.pojo.Messagerecord;

public interface MessageRecordService {
    //添加一条信息
    public boolean addOneMessage(Messagerecord messagerecord);

    //查看发件人的所有发件
    public List<Messagerecord> queryAllSendersMessages(String sender);

    //查看收件人的所有收件
    public List<Messagerecord> queryAllReceiversMessages(String receiver);

    //根据ID查询信件
    public Messagerecord querySingleMessageRecordByMessageId(Integer messageId);

    //更改阅读状态
    public boolean updateMessageStatus(Integer messageId, Integer status);

    //更改发件人的删除状态
    public void updateSenderStatus(String[] messageId, Integer senderStatus);

    //更改收件人的删除状态
    public void updateReceiverStatus(String[] messageId, Integer receiverStatus);
}
