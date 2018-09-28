package droideye.pojo;


import java.io.Serializable;

public class Memberspace implements Serializable {

    private static final long serialVersionUID = 6433262843386834560L;

    //记录流水号
    private Integer id;

    //跑步地点
    private String runPlace;

    //跑步跑步时间
    private String runTime;

    //跑步习惯
    private String runHabit;

    //喜欢的跑步明星
    private String runStar;

    //正在使用的手机
    private String cellPhone;

    //个人对跑步的看法
    private String opinion;

    //头像保存路径
    private String icon;

    //会员id
    private Integer memberId;

    public Memberspace() {
    }

    public Memberspace(String runPlace, String runTime, String runHabit, String runStar, String cellPhone, String opinion, Integer memberId) {
        this.runPlace = runPlace;
        this.runTime = runTime;
        this.runHabit = runHabit;
        this.runStar = runStar;
        this.cellPhone = cellPhone;
        this.opinion = opinion;
        this.memberId = memberId;
    }

    public Memberspace(String runPlace, String runTime, String runHabit, String runStar, String cellPhone, String opinion, String icon, Integer memberId) {
        this.runPlace = runPlace;
        this.runTime = runTime;
        this.runHabit = runHabit;
        this.runStar = runStar;
        this.cellPhone = cellPhone;
        this.opinion = opinion;
        this.icon = icon;
        this.memberId = memberId;
    }

    public Memberspace(Integer id, String runPlace, String runTime, String runHabit, String runStar, String cellPhone, String opinion, String icon, Integer memberId) {
        this.id = id;
        this.runPlace = runPlace;
        this.runTime = runTime;
        this.runHabit = runHabit;
        this.runStar = runStar;
        this.cellPhone = cellPhone;
        this.opinion = opinion;
        this.icon = icon;
        this.memberId = memberId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRunPlace() {
        return runPlace;
    }

    public void setRunPlace(String runPlace) {
        this.runPlace = runPlace;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getRunHabit() {
        return runHabit;
    }

    public void setRunHabit(String runHabit) {
        this.runHabit = runHabit;
    }

    public String getRunStar() {
        return runStar;
    }

    public void setRunStar(String runStar) {
        this.runStar = runStar;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Memberspace{" +
                "id=" + id +
                ", runPlace='" + runPlace + '\'' +
                ", runTime='" + runTime + '\'' +
                ", runHabit='" + runHabit + '\'' +
                ", runStar='" + runStar + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", opinion='" + opinion + '\'' +
                ", icon='" + icon + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}