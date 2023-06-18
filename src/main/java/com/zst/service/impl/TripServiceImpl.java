package com.zst.service.impl;

import com.github.pagehelper.PageInfo;
import com.zst.mapper.TripMapper;
import com.zst.pojo.Trip;
import com.zst.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/6/13 12:57
 */

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripMapper tripMapper;

    /**
     * 添加车次信息
     * @param trip
     * @return
     */
    @Override
    public int addTrip(Trip trip) {
        return tripMapper.addTrip(trip);
    }

    @Override
    public int deleteTripByClasses(String classes) {
        return tripMapper.deleteTripByClasses(classes);
    }

    @Override
    public Trip queryTripByClasses(String classes) {
        return tripMapper.queryTripByClasses(classes);
    }

    @Override
    public int updateTrip(Trip trip) {
        return tripMapper.updateTrip(trip);
    }

    /**
     * 根据条件查询带分页
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Trip> selectByCondition(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        List<Trip> tripList = tripMapper.selectByCondition(map);
        return new PageInfo<>(tripList);
    }

    /**
     * 查询全部车次
     * @return
     */
    @Override
    public PageInfo<Trip> getAllTrips(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        List<Trip> allTrips = tripMapper.getAllTrips(map);
        return new PageInfo<>(allTrips);
    }

}
