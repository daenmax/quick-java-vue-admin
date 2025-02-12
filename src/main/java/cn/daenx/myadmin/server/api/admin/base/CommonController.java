package cn.daenx.myadmin.server.api.admin.base;

import cn.daenx.myadmin.framework.common.vo.Result;
import cn.daenx.myadmin.framework.common.vo.RouterVo;
import cn.daenx.myadmin.framework.common.data.DictData;
import cn.daenx.myadmin.framework.common.data.MenuData;
import cn.daenx.myadmin.framework.common.vo.DictDetailVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/common")
public class CommonController {
    @Resource
    private MenuData menuData;


    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public Result getRouters() {
        List<RouterVo> data = menuData.getData();
        return Result.ok(data);
    }

    /**
     * 根据字典编码查询字典详细信息
     *
     * @param dictCode 字典编码
     */
    @GetMapping(value = "/dict/{dictCode}")
    public Result dictType(@PathVariable String dictCode) {
        List<DictDetailVo> data = DictData.getDetailListByCode(dictCode);
        return Result.ok(data);
    }

}
