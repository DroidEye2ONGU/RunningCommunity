package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import droideye.mapper.PointActionMapper;
import droideye.pojo.Pointaction;
import droideye.service.PointActionService;

@Service("pointActionService")
public class PointActionServiceImpl implements PointActionService {

    @Autowired
    PointActionMapper pointActionMapper;

    @Override
    public Pointaction queryPointActionByActionName(String actionName) {
        Pointaction pointaction = pointActionMapper.queryPointActionByActionName(actionName.toUpperCase());
        return pointaction;
    }

    @Override
    public Pointaction queryPointActionById(Integer id) {
        Pointaction pointaction = pointActionMapper.queryPointActionById(id);

        return pointaction;
    }
}
