package org.fengyue.hadoopctweb.service;

import org.fengyue.hadoopctweb.entity.Calllog;

import java.util.List;

public interface CalllogService {
    List<Calllog> queryMonthDatas(String tel, String calltime);

}
