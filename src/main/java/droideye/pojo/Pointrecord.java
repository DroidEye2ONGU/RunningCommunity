package droideye.pojo;


import java.sql.Timestamp;

//用户完成的 可以加分的动作/行为之后 的记录
public class Pointrecord implements java.io.Serializable {

    private static final long serialVersionUID = -9121063342980699795L;

    //ID流水号
    private Integer id;
    //获得积分的会员
    private String nickName;
    //获得积分的日期
    private Timestamp receiveDate;
    //获得的积分
    private Integer pointActionId;

    public Pointrecord() {
    }

    public Pointrecord(String nickName, Timestamp receiveDate, Integer pointActionId) {
        this.nickName = nickName;
        this.receiveDate = receiveDate;
        this.pointActionId = pointActionId;
    }

    public Pointrecord(Integer id, String nickName, Timestamp receiveDate, Integer pointActionId) {
        this.id = id;
        this.nickName = nickName;
        this.receiveDate = receiveDate;
        this.pointActionId = pointActionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Timestamp getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Timestamp receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getPointActionId() {
        return pointActionId;
    }

    public void setPointActionId(Integer pointActionId) {
        this.pointActionId = pointActionId;
    }

    @Override
    public String toString() {
        return "Pointrecord{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", receiveDate=" + receiveDate +
                ", pointActionId=" + pointActionId +
                '}';
    }
}