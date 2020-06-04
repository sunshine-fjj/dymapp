package org.fkit.dym.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.dym.dao.provider.AppointmentDynaSqlProvider;
import org.fkit.dym.domain.Appointment;
import static org.fkit.dym.util.common.DymConstants.APPOINTMENTTABLE;

public interface AppointmentDao {

	//动态查询预约总量
	@SelectProvider(type=AppointmentDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//动态分页查询
	@SelectProvider(type=AppointmentDynaSqlProvider.class,method="selectWithParam")
	List<Appointment> selectByPage(Map<String, Object> params);

	//根据id查询预约信息
	@Select("select * from "+APPOINTMENTTABLE+" where id=#{id}")
	Appointment selectById(Integer id);

	//动态修改预约审核状态
	@SelectProvider(type=AppointmentDynaSqlProvider.class,method="updateAppointment")
	void update(Appointment appointment);

	//动态插入公告
	@SelectProvider(type=AppointmentDynaSqlProvider.class,method="insertAppointment")
	void save(Appointment appointment);

	//动态查询个人预约信息
	@SelectProvider(type=AppointmentDynaSqlProvider.class,method="count2")
	int count2(Map<String, Object> params);

	//动态分页查询
	@SelectProvider(type=AppointmentDynaSqlProvider.class,method="selectWithParam2")
	List<Appointment> selectByPage2(Map<String, Object> params);

	//根据id删除个人预约信息
	@Delete("delete from "+APPOINTMENTTABLE+" where id=#{id}")
	void deleteById(Integer id);

}
