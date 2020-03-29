package com.study.shiwu.controller;    /**
 * @author: wxs
 * @date: 2020/3/27
 */

import com.study.shiwu.dao.OrderDao;
import com.study.shiwu.entity.Order;
import com.study.shiwu.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ControllerExcle {
    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/userImg")
    public String userImg(HttpServletRequest request, MultipartFile upload) throws Exception{
        //获取上传文件的名字
        String filename=upload.getOriginalFilename();
        System.out.println("原始文件名字是："+filename);
        //上传的位置
        String path="C:/Users/ASUS/upload";
        System.out.println("上传的路径----："+path);
        //判断文件是否存在，没有就创建
        File file=new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        //设置文件名唯一值
        String uuid= UUID.randomUUID().toString().replace("_","");
        filename=uuid+"_"+filename;
        System.out.println("唯一文件名字是："+filename);
        //上传
        upload.transferTo(new File(path,filename));
        //新路径
        String path1="C:/Users/ASUS/upload"+uuid;

        String[][] count= ExcelUtils.readexcell(path1,1,upload);
        System.out.println("控制层得到二维数组的长度："+count.length);
        List<Order> list=new ArrayList<>();
        for (int i=0;i<count.length;i++){
            System.out.println("第一次外循环中：-----------------------------"+i);
            Order order1=new Order();
            for (int k=0;k<count[i].length;k++){
                System.out.println("第二次循环输出："+count[i][k]);
                order1.setId(Integer.parseInt(count[i][0]));
                order1.setAccount(count[i][1]);
                order1.setuName(count[i][2]);
                order1.setPhone(count[i][3]);
                order1.setSite(count[i][4]);
                order1.setPlaceTime(count[i][5]);
            }
            list.add(order1);
        }
        //循环将订单对象存入数据库中
        for (int i=0;i<list.size();i++){
            Order order=list.get(i);
            orderDao.addOrder(order);
        }
        return "";

    }

    @RequestMapping("index")
    public String add(){
        return "index";
    }
}
