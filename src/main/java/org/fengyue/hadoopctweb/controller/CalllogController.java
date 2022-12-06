/**
 * @Time : 2022/12/6 15:58
 * @Author : jin
 * @File : CalllogController.class
 */
package org.fengyue.hadoopctweb.controller;

import org.fengyue.hadoopctweb.entity.Calllog;
import org.fengyue.hadoopctweb.service.CalllogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class CalllogController {
    @Autowired
    private CalllogService calllogService;

    @RequestMapping("/query_clog")
    public String query() {
        return "query.jsp";
    }

    // Object ==> json ==> String
    //@ResponseBody
    @RequestMapping("/view")
    public String view(String tel, String calltime, Model model) {

        List<Calllog> logs = calllogService.queryMonthDatas(tel, calltime);

        System.out.println("**************************************");
        System.out.println(logs);
        System.out.println("**************************************");

        model.addAttribute("clogs", logs);

        return "view.jsp";
    }

}
