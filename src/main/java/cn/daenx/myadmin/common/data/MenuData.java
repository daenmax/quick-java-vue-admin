package cn.daenx.myadmin.common.data;

import cn.daenx.myadmin.common.vo.RouterVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MenuData {
    private List<RouterVo> data;

    public List<RouterVo> getData() {
        return data;
    }

    public MenuData() {
        data = new ArrayList<>();
        this.data.add(demoPeople());
        this.data.add(systemConfig());
    }

    public RouterVo demoPeople() {
        RouterVo vo = new RouterVo();
        vo.setComponent("Layout");
        vo.setHidden(false);
        vo.setPath("/");
        RouterVo emailChildren1 = new RouterVo();
        emailChildren1.setComponent("custom/demoPeople/index");
        emailChildren1.setHidden(false);
        emailChildren1.setName("DemoPeople");
        emailChildren1.setPath("DemoPeople");
        emailChildren1.setMeta(new RouterVo.MetaVo("人员管理", "peoples", false, null));
        vo.setChildren(Arrays.asList(emailChildren1));
        return vo;
    }

    public RouterVo systemConfig() {
        RouterVo vo = new RouterVo();
        vo.setComponent("Layout");
        vo.setHidden(false);
        vo.setPath("/");
        RouterVo emailChildren1 = new RouterVo();
        emailChildren1.setComponent("system/systemConfig/index");
        emailChildren1.setHidden(false);
        emailChildren1.setName("systemConfig");
        emailChildren1.setPath("systemConfig");
        emailChildren1.setMeta(new RouterVo.MetaVo("系统设置", "system", false, null));
        vo.setChildren(Arrays.asList(emailChildren1));
        return vo;
    }

}
