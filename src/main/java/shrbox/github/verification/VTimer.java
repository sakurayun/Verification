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
        if(VMain.vlist.contains(memberid)) {
            memberJoinEvent.getGroup().sendMessage(MessageUtils.newChain(new At(memberJoinEvent.getMember())+"未能通过加群验证，他离开了我们"));
            memberJoinEvent.getMember().kick();
        }
        VMain.vlist.remove(memberid);
        this.cancel();
    }
}
