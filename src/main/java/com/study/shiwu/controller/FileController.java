package com.study.shiwu.controller;    /**
 * @author: wxs
 * @date: 2020/3/24
 */

import com.study.shiwu.entity.Order;
import com.study.shiwu.response.ResponseBody;
import com.study.shiwu.response.ResponseStatus;
import com.study.shiwu.serviceimp.ServiceImp;
import com.study.shiwu.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zm
 * @date 2020/3/24 17:54
 */
@Api(tags = "文件导入导出")
@Controller
public class FileController {
    @Autowired
    private ServiceImp si;

    @ApiOperation("导入excle表")
    @PostMapping("fileInport")
    public ResponseBody fileInport(String filepath, int startrow, MultipartFile upload) throws Exception {
        si.fileImport(filepath, startrow,upload);
        return new ResponseBody(ResponseStatus.SUCCESS);
    }

}
