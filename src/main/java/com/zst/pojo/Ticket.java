package com.zst.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/13 14:44
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tickets")
public class Ticket {

    // 编号
    private int id;

    // 日期
    @TableField("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    // 班次号
    @TableField("classes")
    private String classes;

    // 额定容量
    @TableField("capacity")
    private int capacity;

    // 已售车票
    @TableField("sold_tickets")
    private int soldTickets;

}
