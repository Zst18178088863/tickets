package com.zst.mapper;

import com.zst.pojo.Ticket;
import com.zst.pojo.Trip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/14 10:43
 */

@Repository
public interface TicketMapper {

    // 查询全部车票信息带分页
    List<Ticket> getAllTicket(Map<String,Object> map);

    // 统计指定日期的全部班次售票数量
    int countTicketsByDate(@Param("date") Date date);

    // 根据日期和班次查询车次信息
    Trip getTripByDateAndClasses(@Param("date") Date date, @Param("classes") String classes);

    // 更新已售车票数量
    int updateSoldTickets(@Param("classes") String classes, @Param("soldTickets") int soldTickets);

    // 新增一条车票记录
    int addTicket(Ticket ticket);


}
