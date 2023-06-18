package com.zst.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/13 12:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("schedules")
public class Trip {

    private int id;

    // 班次号
    @TableField("classes")
    private String classes;

    // 发车时间
    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    // 起点站
    @TableField("start_station")
    private String startStation;

    // 终点站
    @TableField("end_station")
    private String endStation;

    // 行车时间
    @TableField("travel_time")
    private double travelTime;

    // 额定容量
    @TableField("capacity")
    private int capacity;

    // 已售车票
    @TableField(exist = false)
    private int soldTickets;

    private  int isDelete;

    private Ticket ticket;


}
