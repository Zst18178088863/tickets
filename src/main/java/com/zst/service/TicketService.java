package com.zst.service;

import com.github.pagehelper.PageInfo;
import com.zst.pojo.Ticket;
import com.zst.pojo.Trip;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/14 18:21
 */

public interface TicketService {

    int addTicket(Ticket ticket);

    // 获取全部的车次信息带分页
    PageInfo<Ticket> getAllTicket(Map<String,Object> map, Integer pageNum, Integer pageSize);

    // 统计指定日期的全部班次售票数量
    int countTicketsByDate(@Param("date") Date date);

    // 售票
    boolean sellTickets(@Param("date") Date date, String classes, int quantity);

    // 退票
    boolean refundTicket(@Param("date") Date date, String classes, int quantity);

}
