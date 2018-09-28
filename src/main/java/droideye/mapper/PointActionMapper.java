package droideye.mapper;

import droideye.pojo.Pointaction;

public interface PointActionMapper {

    // 根据actionName获得对应的积分对象
    public Pointaction queryPointActionByActionName(String actionName);

    // 根据id查询积分对象
    public Pointaction queryPointActionById(Integer id);
}
