package com.rifu.springboot.controller.portal;

import com.github.pagehelper.PageInfo;
import com.rifu.springboot.common.ResponseMsg;
import com.rifu.springboot.service.IRecordService;
import com.rifu.springboot.vo.CategoryVo;
import com.rifu.springboot.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @Author Rifu
 * @Date 2018/9/28  19:23
 */
@Controller
@RequestMapping("/portal/rec")
public class RecordPortalController {
    @Autowired
    IRecordService iRecordService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value="pn",defaultValue = "1",required = false)int pageNum,
                       @RequestParam(value = "size",defaultValue = "20",required = false)int size){
        ResponseMsg responseMsg = iRecordService.selectRepertorys(pageNum, size);
        Collection<RecordVo> recordVoVos=null;
        if (responseMsg.getData() instanceof PageInfo){
            recordVoVos =  ((PageInfo) responseMsg.getData()).getList();
        }
        model.addAttribute("recs",recordVoVos);
        return "record/list";
    }
}
