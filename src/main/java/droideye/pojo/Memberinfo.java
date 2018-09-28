package droideye.pojo;

import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


//用户信息
public class Memberinfo implements Serializable {

    private static final long serialVersionUID = 5343912148702347623L;

    //流水号ID
    private Integer id;

    //用户昵称
    @Length(min = 1,max = 16,message = "您的昵称必须在1-16个字符之间")
    private String nickName;

    //密码
    @Length(min = 6,max = 14,message = "您的密码必须在6-14个字符之间")
    private String password;

    //性别
    @NotNull(message = "您必须选择性别")
    @NotEmpty(message = "您必须选择性别")
    private String gender;

    //年龄
    @DecimalMin(value = "10", message = "您输入的年龄不能低于10且只能是数字")
    @DecimalMax(value = "90", message = "您输入的年龄不能高于90且只能是数字")
    @NotNull(message = "您必须输入年龄")
    private Integer age;

    //邮箱
    @Email(message = "您输入的邮箱格式有误")
    @NotNull(message = "您必须输入邮箱")
    @NotEmpty(message = "您必须输入邮箱")
    private String email;

    //密码提示问题
    @NotNull(message = "您必须设置密码提示问题")
    @NotEmpty(message = "您必须设置密码提示问题")
    private String passwordQuestion;

    //密码提示问题答案
    @NotNull(message = "您必须设置密码提示问题答案")
    @NotEmpty(message = "您必须设置密码提示问题答案")
    private String passwordAnswer;

    //所在省市
    @Length(min = 2,message = "您必须选择所在城市")
    private String provinceCity;

    //地址
    private String address;

    //联系电话
    @Pattern(regexp = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)|(^$)",
            message = "您输入电话格式有误")
    private String phone;

    //当前积分
    private Integer point;

    //推荐人
    @Length(max = 16,message = "您的推荐人名称不会超过16字,请重新检查")
    private String recommender;

    //注册时间
    private Timestamp registerDate;

    //上次上线时间
    private Timestamp latestDate;

    //正常和注销状态
    //正常:0
    //注销:1
    private Integer status;

    //在线和下线状态
    //下线:0
    //在线:1
    private Integer isOnline;

    //等级ID
    private Integer gradeId;

    public Memberinfo() {
    }


    public Memberinfo(Integer id, String nickName, String password, String gender, Integer age, String email, String passwordQuestion, String passwordAnswer, String provinceCity, String address, String phone, Integer point, String recommender, Timestamp registerDate, Timestamp latestDate, Integer status, Integer isOnline, Integer gradeId) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.passwordQuestion = passwordQuestion;
        this.passwordAnswer = passwordAnswer;
        this.provinceCity = provinceCity;
        this.address = address;
        this.phone = phone;
        this.point = point;
        this.recommender = recommender;
        this.registerDate = registerDate;
        this.latestDate = latestDate;
        this.status = status;
        this.isOnline = isOnline;
        this.gradeId = gradeId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
    }

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(Timestamp latestDate) {
        this.latestDate = latestDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "Memberinfo{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", passwordQuestion='" + passwordQuestion + '\'' +
                ", passwordAnswer='" + passwordAnswer + '\'' +
                ", provinceCity='" + provinceCity + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", point=" + point +
                ", recommender='" + recommender + '\'' +
                ", registerDate=" + registerDate +
                ", latestDate=" + latestDate +
                ", status=" + status +
                ", isOnline=" + isOnline +
                ", gradeId=" + gradeId +
                '}';
    }
}