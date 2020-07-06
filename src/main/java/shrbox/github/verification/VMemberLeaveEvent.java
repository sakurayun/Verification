package shrbox.github.verification;

import net.mamoe.mirai.event.events.MemberLeaveEvent;

import java.util.function.Consumer;

public class VMemberLeaveEvent implements Consumer<MemberLeaveEvent> {
    @Override
    public void accept(MemberLeaveEvent event) {
        long memberid = event.getMember().getId();
        long groupid = event.getGroup().getId();
        if(VMain.checkverMember(memberid,groupid)) {
            VMain.removeverMember(memberid,groupid);
        }
    }
}
