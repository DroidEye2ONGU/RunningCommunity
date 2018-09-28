package droideye.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


//代表哪些可以加分的动作/行为	的类
public class Pointaction implements Serializable {

    private static final long serialVersionUID = 5206141799502249255L;

    //积分动作ID
    private Integer id;
    //标识积分操作的名称
    private String actionName;
    //可获得的积分
    private Integer point;
    //描述
    private String description;

    public Pointaction() {
    }

    public Pointaction(String actionName, Integer point, String description) {
        this.actionName = actionName;
        this.point = point;
        this.description = description;
    }

    public Pointaction(Integer id, String actionName, Integer point, String description) {
        this.id = id;
        this.actionName = actionName;
        this.point = point;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pointaction{" +
                "id=" + id +
                ", actionName='" + actionName + '\'' +
                ", point=" + point +
                ", description='" + description + '\'' +
                '}';
    }
}