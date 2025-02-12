package cn.daenx.myadmin.modules.demoPeople.mapper;

import cn.daenx.myadmin.modules.demoPeople.domain.po.DemoPeople;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoPeopleMapper extends BaseMapper<DemoPeople> {
}