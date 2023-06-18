package com.zst.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zst.pojo.Ticket;
import com.zst.pojo.Trip;
import com.zst.service.TicketService;
import com.zst.service.TripService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/14 18:23
 */

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TripService tripService;

    // 定义全局变量，用于做数据回显保存旧的车次信息
    private Map<String,Object> globalVal = new HashMap<>();

    /**
     * 获取全部的车票信息带分页
     * @param model
     * @param map
     * @param pageNum
     * @param pageSize
     * @param ticket
     * @return
     */
    @RequestMapping("/getAllTicket")
    public String getAllTicket(Model model, @RequestParam Map<String,Object> map,
                             @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                             @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Ticket ticket){

        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1){
            pageSize = 5;
        }

        //新建Map集合 保存条件，用于展示在页面上，进行回显
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("classes",ticket.getClasses());
        hashMap.put("date",ticket.getDate());

        //分页
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Ticket> pageInfo = ticketService.getAllTicket(map,pageNum,pageSize);
        System.out.println("pageInfo = " + pageInfo);

        model.addAttribute("hashMap",hashMap);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageNo",pageInfo.getPageNum());             // 页号
        model.addAttribute("totalPages",pageInfo.getPages());           // 总页数
        model.addAttribute("pageNums",pageInfo.getNavigatepageNums());     // 所有导航页码数

        return "ticket";
    }


    /**
     * 条件查询带分页
     * @param model
     * @param map
     * @param pageNum
     * @param pageSize
     */
    @RequestMapping("/findByPage")
    public String findByPage(Model model, @RequestParam Map<String,Object> map,
                             @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                             @RequestParam(defaultValue = "5",value = "pageSize")Integer pageSize,Trip trip){
        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1){
            pageSize = 5;
        }

        //新建Map集合 保存条件，用于展示在页面上，进行回显
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("classes",trip.getClasses());
        hashMap.put("startStation",trip.getStartStation());
        hashMap.put("endStation",trip.getEndStation());

        //分页
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Trip> pageInfo = tripService.selectByCondition(map,pageNum,pageSize);

        model.addAttribute("hashMap",hashMap);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageNo",pageInfo.getPageNum());             // 页号
        model.addAttribute("totalPages",pageInfo.getPages());           // 总页数
        model.addAttribute("pageNums",pageInfo.getNavigatepageNums());     // 所有导航页码数

        return "list";
    }

    /**
     * 统计指定日期的全部班次售票数量
     * @param date
     * @return
     */
    @GetMapping("/countTickets/{date}")
    @ResponseBody
    public int countTicketsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ticketService.countTicketsByDate(date);
    }

    /**
     *  点击售票跳转到售票页面
     * @param model
     * @return
     */
    @RequestMapping("/toSell")
    public String sellTicketForm(Model model,String classes) {
        Trip queryTrip = tripService.queryTripByClasses(classes);
        model.addAttribute("queryTrip",queryTrip);
        System.out.println("queryTrip = " + queryTrip);
        return "sell";
    }

    @RequestMapping("/sell")
    public String sellTicket(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date, String classes,Model model,@RequestParam("quantity") int quantity) {

        // 执行售票操作
        boolean success = ticketService.sellTickets(date,classes,quantity);

        if (success) {
            model.addAttribute("message", "售票成功");
        } else {
            model.addAttribute("message", "售票失败，请检查剩余车票数量");
        }

        return "redirect:/ticket/findByPage"; // 返回售票结果视图
    }

    /**
     *  点击退票跳转到退票页面
     * @param model
     * @return
     */
    @RequestMapping("/toRefund")
    public String refundTicketForm(Model model,String classes) {
        Trip queryTrip = tripService.queryTripByClasses(classes);
        model.addAttribute("queryTrip",queryTrip);
        return "refund";
    }

    @PostMapping("/refund")
    public String refundTicket(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,String classes, Model model,@RequestParam("quantity") int quantity) {
        // 执行退票操作
        boolean success = ticketService.refundTicket(date, classes, quantity);

        if (success) {
            model.addAttribute("message", "退票成功");
        } else {
            model.addAttribute("message", "退票失败，请检查已定票人数");
        }

        return "redirect:/ticket/findByPage"; // 返回退票结果视图
    }

}
