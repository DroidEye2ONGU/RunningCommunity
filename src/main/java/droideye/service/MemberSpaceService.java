package droideye.service;

import droideye.pojo.Memberspace;

public interface MemberSpaceService {

    public boolean addMemberSpace(Memberspace memberspace);

    public Memberspace queryMemberSpaceByMemberId(Integer memberId);

    public boolean modifyMemberSpace(Memberspace memberspace);
}
