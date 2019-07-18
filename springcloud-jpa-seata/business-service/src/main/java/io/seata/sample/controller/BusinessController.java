package io.seata.sample.controller;

import io.seata.sample.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 购买下单，模拟全局事务提交
     *  http://127.0.0.1:8084/purchase/commit
     * @return
     */
    @RequestMapping("/purchase/commit")
    public Boolean purchaseCommit() {
        businessService.purchase("1001", "2001", 1);
        return true;
    }

    /**
     * io.seata.sample.service.AccountService#debit(java.lang.String, java.math.BigDecimal)   userID 传入 1002 直接报错
     * 购买下单，模拟全局事务回滚
     *  http://127.0.0.1:8084/purchase/rollback
     * @return
     */
    @RequestMapping("/purchase/rollback")
    public Boolean purchaseRollback() {
        try {
            businessService.purchase("1002", "2001", 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     *  io.seata.sample.service.BusinessService#purchaseWithoutTransaction(java.lang.String, java.lang.String, int) 没有加分布式事务注解
     *  http://127.0.0.1:8084/purchase/noTransaction
     *
     *  storage_tbl 减少 但是 order_tbl 和 account_tbl无变化 三个表数据存在不一致的情况
     *
     * @return
     */
    @RequestMapping("/purchase/noTransaction")
    public Boolean purchaseNoTransaction() {
        try {
            businessService.purchaseWithoutTransaction("1002", "2001", 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
