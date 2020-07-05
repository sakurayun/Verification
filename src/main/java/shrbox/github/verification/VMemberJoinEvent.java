package shrbox.github.verification;

import jdk.internal.misc.VM;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;

import java.util.Random;
import java.util.Timer;
import java.util.function.Consumer;

public class VMemberJoinEvent implements Consumer<MemberJoinEvent> {
    @Override
    public void accept(MemberJoinEvent event) {
        Member newmember = event.getMember();
        Random random = new Random();
        int code1 = random.nextInt(1000);
        int code = code1*56;
        VMain.addverMember(newmember.getId(),code);//新增一个未验证成员
        Timer timer = new Timer();
        event.getGroup().sendMessage(MessageUtils.newChain("欢迎新成员")
                .plus(new At(newmember))
                .plus("加入本群\n在与群友进行友好交流之前，请先输入验证码："+code+" 你有"+VMain.config.getInt("timeout")+"秒的时间"));
        timer.schedule(new VTimer(event),VMain.config.getInt("timeout")*1000);
    }
}
