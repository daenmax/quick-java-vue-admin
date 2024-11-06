package cn.daenx.myadmin.common.data;

import cn.daenx.myadmin.common.utils.MyMenuUtil;
import cn.daenx.myadmin.common.vo.MyMenuVo;
import cn.daenx.myadmin.common.vo.RouterVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuData {
    private List<RouterVo> data;

    public List<RouterVo> getData() {
        return data;
    }

    public MenuData() {
        data = new ArrayList<>();
        //在这里添加菜单
        this.data.add(MyMenuUtil.handle(demoPeople()));
        this.data.add(MyMenuUtil.handle(systemConfig()));
        this.data.add(MyMenuUtil.handle(testMenu()));
        this.data.add(MyMenuUtil.handle(testToBaidu()));
    }

    public MyMenuVo demoPeople() {
        MyMenuVo vo = MyMenuUtil.GenMenu("人员管理", "/demoPeople", "peoples", "custom/demoPeople/index");
        return vo;
    }

    public MyMenuVo systemConfig() {
        MyMenuVo vo = MyMenuUtil.GenMenu("系统设置", "/systemConfig", "system", "system/systemConfig/index");
        return vo;
    }

    public MyMenuVo testMenu() {
        MyMenuVo vo = MyMenuUtil.GenCatalog("测试菜单", "/testMenu", "system");
        vo.addChildren(MyMenuUtil.GenMenu("菜单1", "/a1", "peoples", "custom/demoPeople/index"));
        vo.addChildren(MyMenuUtil.GenMenu("菜单2", "/a2", "peoples", "custom/demoPeople/index"));
        vo.addChildren(MyMenuUtil.GenLink("Gitee", "https://gitee.com/daenmax", "peoples"));
        vo.addChildren(MyMenuUtil.GenCatalog("多级菜单", "/more", "peoples")
                .addChildren(MyMenuUtil.GenMenu("多级1", "/b1", "peoples", "custom/demoPeople/index"))
                .addChildren(MyMenuUtil.GenMenu("多级2", "/b2", "peoples", "custom/demoPeople/index"))
        );
        return vo;
    }

    public MyMenuVo testToBaidu() {
        MyMenuVo vo = MyMenuUtil.GenLink("跳转到百度", "https://www.baidu.com/", "peoples");
        return vo;
    }

}
