package com.study.flowable.dao;

import com.study.flowable.entity.HolidayEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HolidayDao {

    @Select("insert into wx_holiday(holiday_name,holiday_count,holiday_reson,task_key,process_id) " +
            "VALUES(#{holidayName},#{holidayCount}," +
            "#{holidayReson},#{taskKey},#{processId})")
    void insert(HolidayEntity holidayEntity);

}
