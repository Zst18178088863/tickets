package com.zst.mapper;

import com.zst.pojo.Trip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/13 12:45
 */

@Repository
public interface TripMapper {

    // 增加一个Trip班次信息
    int addTrip(Trip trip);

    //根据班次号删除一个trip
    int deleteTripByClasses(@Param("classes") String classes);

    //根据 classes 查询，返回一个 Trip
     Trip queryTripByClasses(String classes);

    //更新班次信息
    int updateTrip(Trip trip);

    // 获取全部的车次信息
    List<Trip> getAllTrips(Map<String,Object> map);

    // 根据条件查询带分页
    List<Trip> selectByCondition(Map<String,Object> map);



}
