package droideye.controller.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import droideye.pojo.Blackrecord;
import droideye.pojo.Friendrecord;
import droideye.service.BlackRecordService;
import droideye.service.FriendRecordService;

@Controller
@RequestMapping("/messenger")
public class MessengerController {

    @Autowired
    BlackRecordService blackRecordService;

    @Autowired
    FriendRecordService friendRecordService;

    @RequestMapping("/addOneToBlackRecord")
    public String addOneToBlackRecord(@SessionAttribute("username") String selfName,
                                      @RequestParam("blackName") String blackName) {
        System.out.println("MessengerController:addOneToBlackRecord:selfName:" + selfName);
        System.out.println("MessengerController:addOneToBlackRecord:friendName:" + blackName);

        Blackrecord blackrecord = new Blackrecord(selfName, blackName);

        //开始添加黑名单
        blackRecordService.addOneBlackRecord(blackrecord);
        //从好友列表中删除此好友
        friendRecordService.deleteOneFriend(selfName, blackName);

        return "redirect:/member/toBuddyListPage";
    }

    @RequestMapping("/addManyToBlackRecord")
    public String addManyToBlackRecord(@SessionAttribute("username") String selfName,
                                       @RequestParam("friendName") String[] blackNames) {
        //开始添加黑名单
        blackRecordService.addManyToBlackRecord(selfName, blackNames);
        //从好友列表中删除好友
        friendRecordService.deleteManyFriends(selfName, blackNames);

        return "redirect:/member/toBuddyListPage";
    }

    @RequestMapping("/deleteFriend")
    public String deleteFriend(@SessionAttribute("username") String selfName,
                               @RequestParam("friendName") String[] friendNames) {
        System.out.println("MessengerController:deleteFriend:selfName:"+selfName);
        System.out.println("MessengerController:deleteFriend:friendName:");
        for (String name :
                friendNames) {
            System.out.println(name);
        }

        if (friendNames.length == 0) {
            return "redirect:/member/toBuddyListPage";
        }

        //调用service进行删除操作&&从好友列表中移出这些好友
        friendRecordService.deleteManyFriends(selfName, friendNames);

        return "redirect:/member/toBuddyListPage";
    }

    @RequestMapping("/deleteOneFromBlackRecord")
    public String deleteOneFromBlackRecord(@SessionAttribute("username") String selfName,
                                           @RequestParam("blackName") String blackName) {
        System.out.println("MessengerController:deleteOneFromBlackRecord:selfName:" + selfName);
        System.out.println("MessengerController:deleteOneFromBlackRecord:blackName:" + blackName);

        //调用service方法进行移出
        blackRecordService.deleteFromBlackRecord(selfName, blackName);

        //将该用户加回好友列表
        friendRecordService.addOneFriend(new Friendrecord(selfName, blackName));

        return "redirect:/member/toBlackListPage";
    }

    @RequestMapping("/deleteOnesBlackRecords")
    public String deleteOnesBlackRecords(@SessionAttribute("username") String selfName,
                                         @RequestParam("blackName") String[] blackNames) {
        System.out.println("MessengerController:deleteOnesBlackRecords:selfName:" + selfName);
        System.out.println("MessengerController:deleteOnesBlackRecords:blackNames:");

        for (String name :
                blackNames) {
            System.out.println(name);
        }

        blackRecordService.deleteBlackRecordList(selfName,blackNames);

        return "redirect:/member/toBlackListPage";
    }

    @RequestMapping("/addFriend")
    public String addFriend(@SessionAttribute("username") String selfName,
                            @RequestParam("friendName")String friendName) {
        friendRecordService.addOneFriend(new Friendrecord(selfName, friendName));

        return "redirect:/member/toMatchFriendPage";
    }

}
