package droideye.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Graderecord entity.
 *
 * @author MyEclipse Persistence Tools
 */
//表示等级的一个类  积分增加 等级会升高
public class Graderecord implements Serializable {
    private static final long serialVersionUID = 131091950483517258L;

    // Fields

    private Long id;
    //当前等级积分的最低值
    private Long minpoint;
    //当前等级积分的最高值
    private Long maxpoint;
    //当前等级的名字
    private String gradename;
    //当前等级的图标路径
    private String iconpath;
    //有哪些用户属于当前这个等级
    private Set memberinfos = new HashSet(0);

    // Constructors

    /** default constructor */
    public Graderecord() {
    }

    /** minimal constructor */
    public Graderecord(Long minpoint, Long maxpoint, String gradename,
                       String iconpath) {
        this.minpoint = minpoint;
        this.maxpoint = maxpoint;
        this.gradename = gradename;
        this.iconpath = iconpath;
    }

    /** full constructor */
    public Graderecord(Long minpoint, Long maxpoint, String gradename,
                       String iconpath, Set memberinfos) {
        this.minpoint = minpoint;
        this.maxpoint = maxpoint;
        this.gradename = gradename;
        this.iconpath = iconpath;
        this.memberinfos = memberinfos;
    }

    // Property accessors


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinpoint() {
        return minpoint;
    }

    public void setMinpoint(Long minpoint) {
        this.minpoint = minpoint;
    }

    public Long getMaxpoint() {
        return maxpoint;
    }

    public void setMaxpoint(Long maxpoint) {
        this.maxpoint = maxpoint;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public Set getMemberinfos() {
        return memberinfos;
    }

    public void setMemberinfos(Set memberinfos) {
        this.memberinfos = memberinfos;
    }

    @Override
    public String toString() {
        return "Graderecord{" +
                "id=" + id +
                ", minpoint=" + minpoint +
                ", maxpoint=" + maxpoint +
                ", gradename='" + gradename + '\'' +
                ", iconpath='" + iconpath + '\'' +
                ", memberinfos=" + memberinfos +
                '}';
    }
}