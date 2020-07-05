package shrbox.github.verification;

import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;

import java.util.function.Consumer;

public class VMessageListener implements Consumer <GroupMessageEvent> {
    @Override
    public void accept(GroupMessageEvent event) {
        long memberid = event.getSender().getId();//获取QQ号
        if(VMain.vlist.contains(memberid)) {
            int vercode = VMain.verification.getInt(String.valueOf(memberid));//获取对应验证码

            if(event.getMessage().contentToString().equals(String.valueOf(vercode))) {
                VMain.removeverMember(memberid);
                event.getGroup().sendMessage(MessageUtils.newChain("恭喜你！")
                        .plus(new At(event.getSender()))
                        .plus("\n你已经通过了加群验证！"));
            } else {
                event.getGroup().sendMessage(MessageUtils.newChain("很遗憾，")
                        .plus(new At(event.getSender()))
                        .plus("\n你没有通过本群的加群验证，请重试！\n验证码："+vercode));
            }
        }
    }
}
