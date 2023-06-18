package com.zst.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zst.mapper.TicketMapper;
import com.zst.pojo.Ticket;
import com.zst.pojo.Trip;
import com.zst.service.TicketService;
import com.zst.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/13 13:01
 */

@Controller
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TicketService ticketService;

    // 定义全局变量，用于做数据回显保存旧的车次信息
    private Map<String,Object> globalVal = new HashMap<>();

    /**
     * 点击添加按钮跳转到添加页面
     * @return
     */
    @RequestMapping("/addTrip")
    public String addTrip(){
        return "addTrip";
    }

    /**
     * 在添加界面填上班次信息
     * @param trip
     * @return
     */
    @RequestMapping("/saveTrip")
    public String saveTrip(Trip trip){
        tripService.addTrip(trip);

        // 需要转化一下取到 trip.getStartTime() 时间
        // 创建日期格式化对象
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 将 trip.getStartTime() 的 Date 对象转换为目标日期格式的字符串
        String formattedDate = targetFormat.format(trip.getStartTime());

        // 将格式化后的日期字符串转换为 Date 对象
        Date date = null;
        try {
            date = targetFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 新建一个 Ticket 对象，把数据set之后，调用新增方法
        Ticket ticket = new Ticket(trip.getId(),date, trip.getClasses(),
                trip.getCapacity(), 0);
        ticketService.addTicket(ticket);

        return "redirect:/trip/findAllTrip";
    }

    /**
     * 根据班次号删除车次
     * @param classes
     * @return
     */
    @RequestMapping("/deleteTrip/{classes}")
    public String deleteTripByClasses(@PathVariable("classes") String classes){
        tripService.deleteTripByClasses(classes);
        return "redirect:/trip/findAllTrip";
    }

    /**
     * 批量删除
     * @param classes
     * @return
     */
    @RequestMapping("/deleteCounts")
    public String deleteCounts(String[] classes){
        if (classes != null && classes.length > 0){
            //遍历数组
            for (String string : classes) {
                //调用删除接口
                tripService.deleteTripByClasses(string);
            }
        }
        return "redirect:/trip/findAllTrip";
    }

    /**
     * 跳转到修改页面 并使数据回显
     * @param classes
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdatePaper(String classes,Model model){
        Trip trip = tripService.queryTripByClasses(classes);
        System.out.println("trip2222222 = " + trip);
        model.addAttribute("queryTrip",trip);
        globalVal.put("defaultTrip", trip);
        return "updateTrip";
    }

    /**
     * 修改车次信息
     * @param newTrip
     * @param model
     * @return
     */
    @RequestMapping("/updateTrip")
    public String updateTrip(Trip newTrip,Model model){

        Trip oldTrip = (Trip) globalVal.get("defaultTrip");

        System.out.println("oldTrip = " + oldTrip);

        int res = tripService.updateTrip(newTrip);
        if(res > 0){
            return "redirect:/trip/findAllTrip";
        }
        else {
            model.addAttribute("oldTrip",oldTrip);
            return "updateTrip";
        }
    }

    /**
     * 根据条件查询全部的车次信息带分页
     * @param model
     * @param map
     * @param pageNum
     * @param pageSize
     */
    @RequestMapping("/findAllTrip")
    public String findAllTrip(Model model, @RequestParam Map<String,Object> map,
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
        PageInfo<Trip> pageInfo = tripService.getAllTrips(map,pageNum,pageSize);
       /* System.out.println("pageInfo = " + pageInfo);*/

        model.addAttribute("hashMap",hashMap);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageNo",pageInfo.getPageNum());             // 页号
        model.addAttribute("totalPages",pageInfo.getPages());           // 总页数
        model.addAttribute("pageNums",pageInfo.getNavigatepageNums());     // 所有导航页码数

        return "trip";
    }

}

