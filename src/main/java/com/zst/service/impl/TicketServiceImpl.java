package com.zst.service.impl;

import com.github.pagehelper.PageInfo;
import com.zst.mapper.TicketMapper;
import com.zst.mapper.TripMapper;
import com.zst.pojo.Ticket;
import com.zst.pojo.Trip;
import com.zst.service.TicketService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/14 18:22
 */

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TripMapper tripMapper;

    @Override
    public int addTicket(Ticket ticket) {
        return ticketMapper.addTicket(ticket);
    }

    @Override
    public PageInfo<Ticket> getAllTicket(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        List<Ticket> allTicket = ticketMapper.getAllTicket(map);
        return new PageInfo<>(allTicket);
    }

    @Override
    public int countTicketsByDate(@Param("date") Date date) {
        return ticketMapper.countTicketsByDate(date);
    }

    @Override
    public boolean sellTickets(@Param("date") Date date, String classes, int quantity) {

        // 查询车次信息
        Trip trip = tripMapper.queryTripByClasses(classes);
        if (trip == null) {
            // 指定日期和班次的车次不存在
            return false;
        }

        // 判断剩余车票数量是否足够
        int remainingTickets = trip.getCapacity() - trip.getSoldTickets();

        if (remainingTickets < 0){
            // 剩余车票不足
            return false;
        }

        /*if (remainingTickets == trip.getCapacity()){
            // 说明对应车次的票没有出售过，则新增一条车票记录
            Ticket ticket = new Ticket();
            ticket.setDate(trip.getStartTime());
            ticket.setClasses(trip.getClasses());
            ticket.setCapacity(trip.getCapacity());
            ticket.setSoldTickets(quantity);
            ticketMapper.addTicket(ticket);
        }*/

        if (remainingTickets > quantity ) {
            // 更新已售车票数量
            int newSoldTickets = trip.getSoldTickets() + quantity;
            ticketMapper.updateSoldTickets(trip.getClasses(), newSoldTickets);
        }

        return true;
    }

    @Override
    public boolean refundTicket(@Param("date") Date date, String classes, int quantity) {
        // 根据日期和班次查询班次信息
        Trip trip = tripMapper.queryTripByClasses(classes);

        if (trip == null) {
            // 班次不存在，退票失败
            return false;
        }

        // 获取当前已售车票数量
        int soldTickets = trip.getSoldTickets();

        // 判断退票数量是否合理
        if (quantity <= soldTickets) {
            // 计算退票后的已售车票数量
            int updatedSoldTickets = soldTickets - quantity;

            // 更新已售车票数量
            ticketMapper.updateSoldTickets(trip.getClasses(), updatedSoldTickets);

            return true; // 退票成功

        } else {
            return false; // 退票失败，已定票数量不足
        }
    }

}
