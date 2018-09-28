package droideye.controller.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import droideye.pojo.Messagerecord;
import droideye.service.MessageRecordService;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageRecordService messageRecordService;

    @RequestMapping("/seeMessageDetail")
    public String seeMessageDetail(@RequestParam("messageId") String messageId,
                                   HttpServletRequest request,
                                   ModelMap modelMap) {

        //获取请求前的地址
        String refererPath = request.getHeader("Referer");
        refererPath = refererPath.substring(refererPath.lastIndexOf("/") + 1);

        //根据信件ID获取信件
        Messagerecord messagerecord = messageRecordService.querySingleMessageRecordByMessageId(Integer.parseInt(messageId));
        modelMap.addAttribute("message", messagerecord);

        //更新邮件的查看状态
        messageRecordService.updateMessageStatus(Integer.parseInt(messageId), 1);

        //将请求前的地址放入request,以便在页面点击返回时返回到查询邮件详细信息前的地址
        modelMap.addAttribute("refererPath", refererPath);

        return "messenger/view";
    }

    @RequestMapping("/deleteReceiverMessage")
    public String deleteReceiverMessage(@RequestParam("messageId") String[] messageId) {
        messageRecordService.updateReceiverStatus(messageId, 1);

        return "redirect:/member/toMessagePage";
    }

    @RequestMapping("/deleteSenderMessage")
    public String deleteSenderMessage(@RequestParam("messageId") String[] messageId) {
        messageRecordService.updateSenderStatus(messageId, 1);

        return "redirect:/member/toSendMessagePage";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(@RequestParam("receiver") String receiver,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @SessionAttribute("username") String sender,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {

        String refererPath = request.getHeader("Referer");

        Messagerecord messagerecord = new Messagerecord(sender, receiver,
                new Timestamp(System.currentTimeMillis()), title, content,
                0, 0, 0);

        messageRecordService.addOneMessage(messagerecord);

        redirectAttributes.addFlashAttribute("fromController", true);

        //根据上次请求的地址判断该返回那个页面
        if (refererPath.contains("?")) {
            //包含?说明是从加好友页面带参过来的
            //转回加好友页面
            return "redirect:/member/toMatchFriendPage";
        } else {
            //否则是正常发送信息
            return "redirect:/member/toWriteMessagePage";
        }

    }

}
