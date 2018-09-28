package droideye.pojo;

import java.io.Serializable;
import java.sql.Timestamp;


public class Messagerecord implements Serializable {
    private static final long serialVersionUID = -3722026165371759565L;

    //记录流水号
    private Integer id;
    //发件人登录名
    private String sender;
    //收件人登录名
    private String receiver;
    //发送日期
    private Timestamp sendDate;
    //短信标题
    private String title;
    //短信内容
    private String content;
    //阅读状态
    //0:未阅读
    //1:已阅读
    private Integer status;

    //发送方存储状态
    //0:表示未删除
    //1:表示已删除
    private Integer senderStatus;

    //收件方存储状态
    private Integer receiverStatus;

    public Messagerecord() {
    }

    public Messagerecord(String sender, String receiver, Timestamp sendDate, String title, String content, Integer status, Integer senderStatus, Integer receiverStatus) {
        this.sender = sender;
        this.receiver = receiver;
        this.sendDate = sendDate;
        this.title = title;
        this.content = content;
        this.status = status;
        this.senderStatus = senderStatus;
        this.receiverStatus = receiverStatus;
    }

    public Messagerecord(Integer id, String sender, String receiver, Timestamp sendDate, String title, String content, Integer status, Integer senderStatus, Integer receiverStatus) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.sendDate = sendDate;
        this.title = title;
        this.content = content;
        this.status = status;
        this.senderStatus = senderStatus;
        this.receiverStatus = receiverStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSenderStatus() {
        return senderStatus;
    }

    public void setSenderStatus(Integer senderStatus) {
        this.senderStatus = senderStatus;
    }

    public Integer getReceiverStatus() {
        return receiverStatus;
    }

    public void setReceiverStatus(Integer receiverStatus) {
        this.receiverStatus = receiverStatus;
    }

    @Override
    public String toString() {
        return "Messagerecord{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", sendDate=" + sendDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", senderStatus=" + senderStatus +
                ", receiverStatus=" + receiverStatus +
                '}';
    }
}