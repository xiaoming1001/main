package com.study.shiwu.serviceimp;

import com.study.shiwu.dao.DaoInt;
import com.study.shiwu.dao.OrderDao;
import com.study.shiwu.dao.UserDao;
import com.study.shiwu.entity.Order;
import com.study.shiwu.entity.Use;
import com.study.shiwu.entity.User;;
import com.study.shiwu.error.ZengError;
import com.study.shiwu.response.ResponseStatus;
import com.study.shiwu.util.ExcelUtils;
import com.study.shiwu.util.NoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;


/**
 * @author ASUS
 */
@Service
public class ServiceImp{
    @Autowired
    private DaoInt dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;

    private static final Logger log= LoggerFactory.getLogger(ServiceImp.class);

    @Transactional(rollbackFor = Exception.class)
    public String update1(String card1,String card2,double money) {
        String str="";
        //通过账号获得卡内余额
      User user=dao.select1(card1);
      User user1=dao.select1(card2);
      log.info("原账号"+card1+"的余额是"+user.getMoney());
      log.info("被转账的账号"+card2+"的余额是"+user.getMoney());
        //进行金额加减
        if (user.getMoney()>money){
            double money1=user.getMoney()-money;
            double money2=user1.getMoney()+money;

            dao.update1(card1, money1);
            //int i=1/0;
            dao.update1(card2, money2);
        }else {
            str="201";
        }

       return "str";
    }


    public User select1(String card){
        if (card.equals("1002")){
            throw new ZengError(ResponseStatus.MONEY_BU_ZU);
        }
        return dao.select1(card);
    }


    public void addUser(User user){
        if (user.getCard().equals("1003")){
            System.out.println("只有等于1003才执行，这个异常方法");
            throw new ZengError(ResponseStatus.TONG_MING);
        }
        dao.addUser(user);
    }

    public void updateUser(String card,double money){
        dao.updateUser(card,money);
    }

    //测试test'
    public Use selectUser(String account,String pwd){
        return  dao.selectUser(account, pwd);
    }
    //测试test_db
    public void addUser1(String account){userDao.addUser1(account);}


    //写死路径导出ex
    public List<Order> getOrder(){
        List<Order> list=orderDao.getOrder();
        String file="H:\\ex/order.xls";
        String[] columnNames={"订单ID","订单账号","订单人","电话号码","城市","时间"};
        String[] columns={"id","account","uName","phone","site","placeTime"};
        String sheetName="订单信息表";
        ExcelUtils.exportExcelByList(file,list,columnNames,columns,sheetName);
        return list;
    }

    //不写死导出ex
    public List<Order> getOrder2(String file){
        List<Order> list=orderDao.getOrder();
        File f = new File(file);
        if (!f.exists() && f.isDirectory()) {
            f.mkdir();
        }else {
            System.out.println("已存在，不用创建了！！！");
        }
        String[] columnNames={"订单ID","订单账号","订单人","电话号码","城市","时间"};
        String[] columns={"id","account","uName","phone","site","placeTime"};
        String sheetName="订单信息表";
        ExcelUtils.exportExcelByList(file,list,columnNames,columns,sheetName);
        return list;
    }

    //导入数据
    public void addOrder(String filepath) throws Exception {
        int startrow=0;
        String[][] o=ExcelUtils.readexcell(filepath,startrow);
        for (int i=0;i<o.length;i++){
            System.out.println("输出一号："+o[i].getClass());
            for (int k=0;k<o.length;k++){
                System.out.println("输出标题："+o[i][k]);
            }
        }
        System.out.println("得到二维数组是："+o.length);
    }

    //通过电话号码获取验证码
    public String getNote(String phone) throws Exception {
        String result= NoteUtil.add(phone);
        return result;
    }

}
