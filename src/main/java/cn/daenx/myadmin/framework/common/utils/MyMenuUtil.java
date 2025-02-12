package cn.daenx.myadmin.framework.common.utils;

import cn.daenx.myadmin.framework.common.vo.MyMenuVo;
import cn.daenx.myadmin.framework.common.vo.RouterVo;
import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMenuUtil {
    /**
     * 创建一个目录
     * @param name
     * @param path
     * @param icon
     * @return
     */
    public static MyMenuVo GenCatalog(String name, String path, String icon) {
        MyMenuVo vo = new MyMenuVo();
        vo.setName(name);
        vo.setPath(path);
        vo.setIcon(icon);
        vo.setType("0");
        return vo;
    }

    /**
     * 创建一个菜单
     * @param name
     * @param path
     * @param icon
     * @param component 前端的组件路径，例如：custom/demoPeople/index
     * @return
     */
    public static MyMenuVo GenMenu(String name, String path, String icon, String component) {
        MyMenuVo vo = new MyMenuVo();
        vo.setName(name);
        vo.setPath(path);
        vo.setIcon(icon);
        vo.setComponent(component);
        vo.setType("1");
        return vo;
    }

    /**
     * 创建一个链接
     * @param name
     * @param url
     * @param icon
     * @return
     */
    public static MyMenuVo GenLink(String name, String url, String icon) {
        MyMenuVo vo = new MyMenuVo();
        vo.setName(name);
        vo.setPath(url);
        vo.setIcon(icon);
        vo.setType("2");
        return vo;
    }

    public static RouterVo handle(MyMenuVo myMenuVo) {
        RouterVo vo = new RouterVo();
        if ("0".equals(myMenuVo.getType())) {
            //目录
            vo.setComponent("Layout");
            vo.setHidden(false);
            vo.setPath(myMenuVo.getPath());
            vo.setName(myMenuVo.getName());
            vo.setMeta(new RouterVo.MetaVo(myMenuVo.getName(), myMenuVo.getIcon(), false, null));
            List<RouterVo> routerVos = handleSub(myMenuVo.getChildren());
            vo.setChildren(routerVos);
            if (CollUtil.isNotEmpty(routerVos)) {
                vo.setAlwaysShow(true);
            }
        } else if ("1".equals(myMenuVo.getType())) {
            //菜单
            vo.setComponent("Layout");
            vo.setHidden(false);
            vo.setPath("/");
            vo.setName(myMenuVo.getName());
            vo.setMeta(new RouterVo.MetaVo(myMenuVo.getName(), myMenuVo.getIcon(), false, null));
            List<RouterVo> routerVos = handleSub(Arrays.asList(myMenuVo));
            vo.setChildren(routerVos);
        } else if ("2".equals(myMenuVo.getType())) {
            //外链
            vo.setComponent("Layout");
            vo.setHidden(false);
            vo.setPath("/");
            vo.setName(myMenuVo.getName());
            vo.setMeta(new RouterVo.MetaVo(myMenuVo.getName(), myMenuVo.getIcon(), false, null));
            List<RouterVo> routerVos = handleSub(Arrays.asList(myMenuVo));
            vo.setChildren(routerVos);
        }
        return vo;
    }

    public static List<RouterVo> handleSub(List<MyMenuVo> listVo) {
        List<RouterVo> retList = new ArrayList<>();
        for (MyMenuVo myMenuVo : listVo) {
            RouterVo retVo = new RouterVo();
            if ("0".equals(myMenuVo.getType())) {
                //目录
                retVo.setComponent("ParentView");
                retVo.setHidden(false);
                retVo.setPath(myMenuVo.getPath().substring(1));
                retVo.setName(myMenuVo.getName());
                retVo.setMeta(new RouterVo.MetaVo(myMenuVo.getName(), myMenuVo.getIcon(), false, null));
                List<RouterVo> routerVos = handleSub(myMenuVo.getChildren());
                retVo.setChildren(routerVos);
                if (CollUtil.isNotEmpty(routerVos)) {
                    retVo.setAlwaysShow(true);
                }
                retList.add(retVo);
            } else if ("1".equals(myMenuVo.getType())) {
                //菜单
                retVo.setComponent(myMenuVo.getComponent());
                retVo.setHidden(false);
                retVo.setPath(myMenuVo.getPath().substring(1));
                retVo.setName(myMenuVo.getName());
                retVo.setMeta(new RouterVo.MetaVo(myMenuVo.getName(), myMenuVo.getIcon(), false, null));
                retList.add(retVo);
            } else if ("2".equals(myMenuVo.getType())) {
                //外链
                retVo.setComponent("Layout");
                retVo.setHidden(false);
                retVo.setPath(myMenuVo.getPath());
                retVo.setName(myMenuVo.getName());
                retVo.setMeta(new RouterVo.MetaVo(myMenuVo.getName(), myMenuVo.getIcon(), false, null));
                retList.add(retVo);
            }
        }
        return retList;
    }
}
