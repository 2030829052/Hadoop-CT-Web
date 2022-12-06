/**
 * @Time : 2022/12/6 15:57
 * @Author : jin
 * @File : CalllogServiceImpl.class
 */
package org.fengyue.hadoopctweb.service.impl;

import org.fengyue.hadoopctweb.dao.CalllogDao;
import org.fengyue.hadoopctweb.entity.Calllog;
import org.fengyue.hadoopctweb.service.CalllogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalllogServiceImpl implements CalllogService {
    @Autowired
    CalllogDao calllogDao;

    /**
     * 查询用户指定时间的通话统计信息
     *
     * @param tel
     * @param calltime
     * @return
     */
    @Override
    public List<Calllog> queryMonthDatas(String tel, String calltime) {


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tel", tel);

        if (calltime.length() > 4) {
            calltime = calltime.substring(0, 4);
        }
        paramMap.put("year", calltime);
        System.out.println("**************************************");
        System.out.println(paramMap);
        System.out.println("**************************************");


        return calllogDao.queryMonthDatas(paramMap);
    }


}
