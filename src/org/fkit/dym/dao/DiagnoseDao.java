package org.fkit.dym.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.dym.dao.provider.DiagnoseDynaSqlProvider;
import org.fkit.dym.domain.Diagnose;
import static org.fkit.dym.util.common.DymConstants.DIAGNOSETABLE;
import static org.fkit.dym.util.common.DymConstants.MYDOWNLOADTABLE;

public interface DiagnoseDao {

	//统计故障信息总数量
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//动态分页查询故障信息
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="selectWithParam")
	List<Diagnose> selectByPage(Map<String, Object> params);

	//动态插入故障信息
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="insertDiagnose")
	void save(Diagnose diagnose);

	//根据id删除故障信息
	@Delete("delete from "+DIAGNOSETABLE+" where id=#{id}")
	void deleteById(Integer id);

	//根据id查询故障信息
	@Select("select * from "+DIAGNOSETABLE+" where id=#{id}")
	Diagnose selectById(Integer id);
	
	//动态修改故障信息
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="updateDiagnose")
	void update(Diagnose diagnose);

	//动态查询个人上传故障文档
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="count3")
	int count3(Map<String, Object> params);

	//动态分页查询
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="selectWithParam3")
	List<Diagnose> selectByPage3(Map<String, Object> params);

	//动态插入故障信息到我的下载文档表中
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="insertMyDownload")
	void save2(Diagnose target);

	//动态查询个人下载故障文档
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="count4")
	int count4(Map<String, Object> params);
    
	//动态分页查询
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="selectWithParam4")
	List<Diagnose> selectByPage4(Map<String, Object> params);

	//根据id删除下载文档
	@Delete("delete from "+MYDOWNLOADTABLE+" where id=#{id}")
	void deleteByDownId(Integer id);

}
