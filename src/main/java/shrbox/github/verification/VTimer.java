package shrbox.github.verification;

import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;

import java.util.TimerTask;

public class VTimer extends TimerTask {
    MemberJoinEvent memberJoinEvent;
    public VTimer(MemberJoinEvent event) {
        memberJoinEvent = event;
    }

    @Override
    public void run() {
        long memberid = memberJoinEvent.getMember().getId();
        long groupid = memberJoinEvent.getGroup().getId();
        if(VMain.checkverMember(memberid,groupid)) {
            memberJoinEvent.getGroup().sendMessage(MessageUtils.newChain(new At(memberJoinEvent.getMember()))
                    .plus("未能通过加群验证，他离开了我们"));
            VMain.removeverMember(memberid,groupid);
            memberJoinEvent.getMember().kick();
        }
        this.cancel();
    }
}
