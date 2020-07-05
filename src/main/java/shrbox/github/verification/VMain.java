package shrbox.github.verification;

import net.mamoe.mirai.console.plugins.Config;
import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.ArrayList;
import java.util.List;

public class VMain extends PluginBase {
    public static Config config;
    public static Config verification;
    public static List<Long> vlist = new ArrayList<>();

    public static void removeverMember(long memberid) {
        vlist.remove(memberid);
        verification.exist(String.valueOf(memberid));
        verification.save();
    }

    public static void addverMember(long memberid,int code) {
        vlist.add(memberid);//添加新成员到List
        verification.set(String.valueOf(memberid),code);
        verification.save();
    }

    public void onLoad() {}

    public void onEnable() {
        config = loadConfig("config.yml");
        verification = loadConfig("verification.yml");//新成员对应验证码存储文件
        config.setIfAbsent("timeout", 300);
        config.save();

        getLogger().info("Plugin enabled!");
        this.getEventListener().subscribeAlways(MemberJoinEvent.class,new VMemberJoinEvent());
        this.getEventListener().subscribeAlways(GroupMessageEvent.class,new VMessageListener());
    }

}          