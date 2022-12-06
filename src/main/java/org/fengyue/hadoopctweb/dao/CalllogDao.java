package org.fengyue.hadoopctweb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.fengyue.hadoopctweb.entity.Calllog;

import java.util.List;
import java.util.Map;

@Mapper
public interface CalllogDao {

    @Select("select * from calllog where tel_id= (SELECT id from ct_user where tel=#{tel}) and date_id in (SELECT ID from ct_date where year=#{year} and month !='-1'  and  day!='-1')")
    List<Calllog> queryMonthDatas(Map<String, Object> paramMap);
}
