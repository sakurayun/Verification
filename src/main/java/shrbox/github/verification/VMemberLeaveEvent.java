package shrbox.github.verification;

import net.mamoe.mirai.event.events.MemberLeaveEvent;

import java.util.function.Consumer;

public class VMemberLeaveEvent implements Consumer<MemberLeaveEvent> {
    @Override
    public void accept(MemberLeaveEvent event) {
        if(event.getGroup().getBotPermission().getLevel()==0|| !VMain.config.getLongList("enable_group").contains(event.getGroup().getId())) {
            return;//如果机器人权限不足或该群不在开启的群列表内
        }
        long memberid = event.getMember().getId();
        long groupid = event.getGroup().getId();
        if(VMain.checkverMember(memberid,groupid)) {
            VMain.removeverMember(memberid,groupid);
        }
    }
}
