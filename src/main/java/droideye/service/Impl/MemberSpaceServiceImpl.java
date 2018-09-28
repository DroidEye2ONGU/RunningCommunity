package droideye.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import droideye.mapper.MemberInfoMapper;
import droideye.mapper.MemberSpaceMapper;
import droideye.pojo.Memberinfo;
import droideye.pojo.Memberspace;
import droideye.pojo.Pointaction;
import droideye.pojo.Pointrecord;
import droideye.service.GradeRecordService;
import droideye.service.MemberService;
import droideye.service.MemberSpaceService;
import droideye.service.PointActionService;
import droideye.service.PointRecordService;

@Service("memberSpaceService")
public class MemberSpaceServiceImpl implements MemberSpaceService {

    @Autowired
    MemberSpaceMapper memberSpaceMapper;

    @Autowired
    MemberService memberService;
    //积分Service
    @Autowired
    PointActionService pointActionService;
    //积分Action Service
    @Autowired
    PointRecordService pointRecordService;
    //积分等级 Service
    @Autowired
    GradeRecordService gradeRecordService;

    @Override
    public boolean addMemberSpace(Memberspace memberspace) {
        System.out.println("MemberSpaceImpl:addMemberSpace:Memberspace:" + memberspace);
        boolean result = memberSpaceMapper.addMemberSpace(memberspace);


        //如果存储成功,为会员增加积分
        //查找对应的用户
        Memberinfo memberinfo = memberService.querySingleMemberById(memberspace.getMemberId());
        //查找创建个人空间的积分
        Pointaction pointaction = pointActionService.queryPointActionByActionName(
                PointActionService.CREATEPERSONALSPACE);
        Integer point = pointaction.getPoint();
        Integer pointactionId = pointaction.getId();

        //更新用户积分状态
        memberService.addPointByNickName(point, memberinfo.getNickName());

        //增加增加积分记录
        pointRecordService.addPointRecord(new Pointrecord(memberinfo.getNickName(), new Timestamp(
                System.currentTimeMillis()), pointactionId));


        return result;
    }

    @Override
    public Memberspace queryMemberSpaceByMemberId(Integer memberId) {
        Memberspace memberspace = memberSpaceMapper.queryMemberSpaceByMemberId(memberId);

        return memberspace;
    }

    @Override
    public boolean modifyMemberSpace(Memberspace memberspace) {
        boolean result = memberSpaceMapper.modifyMemberSpace(memberspace);

        return result;
    }
}
