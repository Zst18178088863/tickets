package com.zst.service;

import com.github.pagehelper.PageInfo;
import com.zst.pojo.Trip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/13 12:43
 */

@Repository
public interface TripService {

    // 增加一个班次信息
    int addTrip(Trip trip);

    //根据班次号删除一个trip
    int deleteTripByClasses(@Param("classes") String classes);

    //根据 classes 查询，返回一个 Trip
    Trip queryTripByClasses(String classes);

    //更新班次信息
    int updateTrip(Trip trip);

    // 根据条件查询带分页
    PageInfo<Trip> selectByCondition(Map<String,Object> map, Integer pageNum, Integer pageSize);

    // 获取全部的车次信息带分页
    PageInfo<Trip> getAllTrips(Map<String,Object> map, Integer pageNum, Integer pageSize);
}
