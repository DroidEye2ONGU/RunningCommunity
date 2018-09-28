package droideye.mapper;

import droideye.pojo.Memberspace;

public interface MemberSpaceMapper {


    //新增个人空间
    public boolean addMemberSpace(Memberspace memberspace);

    //根据会员名查找个人空间
    public Memberspace queryMemberSpaceByMemberId(Integer memberId);

    //更新个人空间
    public boolean modifyMemberSpace(Memberspace memberspace);
}
