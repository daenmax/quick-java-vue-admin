package cn.daenx.myadmin.common.vo;

import cn.hutool.core.lang.Validator;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 */
@Data
public class MyMenuVo implements Serializable {

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单类型，0=目录，1=菜单，2=外链
     */
    private String type;

    /**
     * 组件路径，只有当type=1时才需要配置，为前端的组件路径，例如：custom/demoPeople/index
     */
    private String component = "Layout";

    /**
     * 路由地址，目录时填写/，菜单时填写abc，外链时填写http....
     */
    private String path;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 子路由
     */
    private List<MyMenuVo> children = new ArrayList<>();

    public MyMenuVo addChildren(MyMenuVo vo) {
        this.children.add(vo);
        return this;
    }
}
