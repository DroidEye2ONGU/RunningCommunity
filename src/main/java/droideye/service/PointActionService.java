package droideye.service;

import droideye.pojo.Pointaction;

public interface PointActionService {

    public static final String REGISTER = "REGISTER";
    public static final String RECOMMEND = "RECOMMEND";
    public static final String LOGIN = "LOGIN";
    public static final String LOGINDESKHELPER = "LOGINDESKHELPER";
    public static final String CREATEPERSONALSPACE = "CREATEPERSONALSPACE";
    public static final String SENDSTICK = "SENDSTICK";
    public static final String REPLYSTICK = "REPLYSTICK";
    public static final String GOODSTICK = "GOODSTICK";
    public static final String SUPERGOODSTICK = "SUPERGOODSTICK";
    public static final String BBSMANAGER = "BBSMANAGER";
    public static final String REPLYSTICK10 = "REPLYSTICK";
    public static final String EDM = "EDM";
    public static final String JOINRUNNING = "JOINRUNNING";
    public static final String WINRUNNING1 = "WINRUNNING1";
    public static final String WINRUNNING2 = "WINRUNNING2";

    // 根据actionName获得对应的积分对象
    public Pointaction queryPointActionByActionName(String actionName);

    // 根据id查询积分对象
    public Pointaction queryPointActionById(Integer id);
}
