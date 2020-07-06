package shrbox.github.verification;

import net.mamoe.mirai.console.plugins.Config;
import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VMain extends PluginBase {
    private static VMain pluginmain;
    public static Config config;
    public static Config verification;
    public static List<Long> groupList = new ArrayList<>();
    public static List<String> vlist = new ArrayList<>();
    public static VMain getPluginmain() {
        return pluginmain;
    }
    public static void removeverMember(long memberid,long groupid) {
        String s_memberid = String.valueOf(memberid);
        String s_groupid = String.valueOf(groupid);
        String checkid = s_memberid+"_"+s_groupid;
        vlist.remove(checkid);
        verification.set(checkid,0);
        getPluginmain().getLogger().info("删除ID："+checkid);
    }

    public static void addverMember(long memberid,long groupid,int code) {
        String s_memberid = String.valueOf(memberid);
        String s_groupid = String.valueOf(groupid);
        String checkid = s_memberid+"_"+s_groupid;
        vlist.add(checkid);//添加新成员到List
        verification.set(checkid,code);
        //getPluginmain().getLogger().info("添加ID："+checkid);
    }

    public static boolean checkverMember(long memberid,long groupid) {
        String s_memberid = String.valueOf(memberid);
        String s_groupid = String.valueOf(groupid);
        //getPluginmain().getLogger().info("检查返回值："+vlist.contains(s_memberid + "_" + s_groupid));
        return vlist.contains(s_memberid + "_" + s_groupid);
    }

    public static int getVerification(long memberid,long groupid) {
        String s_memberid = String.valueOf(memberid);
        String s_groupid = String.valueOf(groupid);
        String checkid = s_memberid+"_"+s_groupid;
        //getPluginmain().getLogger().info("返回值："+verification.getInt(checkid));
        return verification.getInt(checkid);
    }

    public void onLoad() {}
    public static void loadConfigFiles() {
        Collections.addAll(groupList, 114514L, 1919810L);
        config.setIfAbsent("enable_group",groupList);
        config.setIfAbsent("timeout", 300);
        config.save();
        groupList = config.getLongList("enable_group");
    }

    public void onEnable() {
        pluginmain = this;
        config = loadConfig("config.yml");
        verification = loadConfig("verification.yml");//新成员对应验证码存储文件
        loadConfigFiles();

        getLogger().info("Plugin enabled!");
        this.getEventListener().subscribeAlways(MemberJoinEvent.class,new VMemberJoinEvent());
        this.getEventListener().subscribeAlways(GroupMessageEvent.class,new VMessageListener());
        this.getEventListener().subscribeAlways(MemberJoinEvent.class,new VMemberJoinEvent());
    }
}          