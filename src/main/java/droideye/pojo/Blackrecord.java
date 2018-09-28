package droideye.pojo;

import java.io.Serializable;

//记录黑名单的一个类
public class Blackrecord implements Serializable {

    private static final long serialVersionUID = 4301788897526948727L;

    //记录流水号
    private Integer id;

    //会员登录名
    private String selfName;

    //被加入黑名单的登录名
    private String blackName;

    public Blackrecord() {
    }

    public Blackrecord(String selfName, String blackName) {
        this.selfName = selfName;
        this.blackName = blackName;
    }

    public Blackrecord(Integer id, String selfName, String blackName) {
        this.id = id;
        this.selfName = selfName;
        this.blackName = blackName;
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

    public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
    }

    @Override
    public String toString() {
        return "Blackrecord{" +
                "id=" + id +
                ", selfName='" + selfName + '\'' +
                ", blackName='" + blackName + '\'' +
                '}';
    }
}