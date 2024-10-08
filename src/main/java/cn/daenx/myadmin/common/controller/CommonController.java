package cn.daenx.myadmin.common.controller;

import cn.daenx.myadmin.common.vo.Result;
import cn.daenx.myadmin.common.vo.RouterVo;
import cn.daenx.myadmin.common.data.DictData;
import cn.daenx.myadmin.common.data.MenuData;
import cn.daenx.myadmin.common.vo.DictDetailVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Resource
    private DictData dictData;
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
        List<DictDetailVo> data = dictData.getDetailListByCode(dictCode);
        return Result.ok(data);
    }

}
