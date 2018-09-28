package droideye.pojo;


import java.io.Serializable;

//记录好友的一个类
public class Friendrecord implements Serializable {

    private static final long serialVersionUID = 6594561461969877566L;

    //记录流水号
    private Integer id;

    //会员登录名
    private String selfName;

    //好友登录名
    private String friendName;

    public Friendrecord() {
    }

    public Friendrecord(String selfName, String friendName) {
        this.selfName = selfName;
        this.friendName = friendName;
    }

    public Friendrecord(Integer id, String selfName, String friendName) {
        this.id = id;
        this.selfName = selfName;
        this.friendName = friendName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelfName() {
        return selfName;
    }

    public void setSelfName(String selfName) {
        this.selfName = selfName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    @Override
    public String toString() {
        return "Friendrecord{" +
                "id=" + id +
                ", selfName='" + selfName + '\'' +
                ", friendName='" + friendName + '\'' +
                '}';
    }
}