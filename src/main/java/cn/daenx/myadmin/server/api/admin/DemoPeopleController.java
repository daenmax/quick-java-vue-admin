package cn.daenx.myadmin.server.api.admin;


import cn.daenx.myadmin.framework.common.vo.ComStatusUpdVo;
import cn.daenx.myadmin.framework.excel.ExcelResult;
import cn.daenx.myadmin.framework.excel.utils.ExcelUtil;
import cn.daenx.myadmin.framework.common.exception.MyException;
import cn.daenx.myadmin.framework.common.vo.Result;
import cn.daenx.myadmin.modules.demoPeople.domain.po.DemoPeople;
import cn.daenx.myadmin.modules.demoPeople.domain.vo.*;
import cn.daenx.myadmin.modules.demoPeople.service.DemoPeopleService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试人员
 */
@RestController
@RequestMapping("/admin/demoPeople")
@Slf4j
public class DemoPeopleController {
    @Resource
    private DemoPeopleService demoPeopleService;


    /**
     * 分页列表
     *
     * @param vo
     * @return
     */
    @GetMapping("/page")
    public Result page(DemoPeoplePageVo vo) {
        IPage<DemoPeople> page = demoPeopleService.getPage(vo);
        return Result.ok(page);
    }

    /**
     * 导出
     */
    @PostMapping("/exportData")
    public void exportData(HttpServletResponse response, DemoPeoplePageVo vo) {
        List<DemoPeopleExportDto> list = demoPeopleService.exportData(vo);
        ExcelUtil.exportXlsx(response, "人员信息", "sheet1", list, DemoPeopleExportDto.class);
    }

    /**
     * 导入
     */
    @PostMapping("/importData")
    public Result importData(@RequestPart("file") MultipartFile file) throws IOException {
        ExcelResult<DemoPeopleImportVo> dataResult = ExcelUtil.importExcel(file.getInputStream(), DemoPeopleImportVo.class, false);
        List<DemoPeopleImportVo> list = dataResult.getList();
        Integer num = demoPeopleService.importData(list);
        return Result.ok("成功导入" + num + "条");
    }

    /**
     * 下载导入模板
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil.exportXlsx(response, "人员信息", "sheet1", new ArrayList<>(), DemoPeopleImportVo.class);
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/query")
    public Result query(@RequestParam(name = "id", required = false) String id) {
        DemoPeople po = demoPeopleService.getInfo(id);
        return Result.ok(po);
    }


    /**
     * 新增
     *
     * @param vo
     * @return
     */
    @PostMapping("/add")
    public Result add(@Validated @RequestBody DemoPeopleAddVo vo) {
        DemoPeople po = demoPeopleService.addInfo(vo);
        return Result.ok(po);
    }

    /**
     * 批量新增
     *
     * @param vos
     * @return
     */
    @PostMapping("/addBatch")
    public Result addBatch(@Validated @RequestBody List<DemoPeopleAddVo> vos) {
        Integer successNum = demoPeopleService.addInfoBatch(vos);
        return Result.ok(successNum);
    }

    /**
     * 修改
     *
     * @param vo
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody DemoPeopleUpdVo vo) {
        demoPeopleService.editInfo(vo);
        return Result.ok();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/del")
    public Result del(@RequestBody List<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            throw new MyException("参数错误");
        }
        demoPeopleService.deleteByIds(ids);
        return Result.ok();
    }

    /**
     * 全部删除
     *
     * @return
     */
    @GetMapping("/delAll")
    public Result delAll() {
        demoPeopleService.delAll();
        return Result.ok();
    }

    /**
     * 修改状态
     *
     * @param vo
     * @return
     */
    @PostMapping("/changeStatus")
    public Result changeStatus(@Validated @RequestBody ComStatusUpdVo vo) {
        demoPeopleService.changeStatus(vo);
        return Result.ok();
    }

    /**
     * 呼叫测试
     *
     * @param ids
     * @return
     */
    @PostMapping("/testCall")
    public Result testCall(@RequestBody List<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            throw new MyException("参数错误");
        }
        for (String id : ids) {
            demoPeopleService.testCall(id);
        }
        return Result.ok("已提交测试");
    }

    /**
     * 复制人员信息
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/copyInfo")
    public Result copyInfo(@RequestBody DemoPeopleCopyInfoVo vo) {
        if (CollUtil.isEmpty(vo.getIds())) {
            throw new MyException("参数错误");
        }
        Map<String, Object> map = demoPeopleService.copyInfo(vo);
        return Result.ok(map);
    }

}
