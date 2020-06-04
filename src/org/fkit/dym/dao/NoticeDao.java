package org.fkit.dym.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.dym.dao.provider.NoticeDynaSqlProvider;
import org.fkit.dym.domain.Notice;
import static org.fkit.dym.util.common.DymConstants.NOTICETABLE;


public interface NoticeDao {

	//动态查询公告数量
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//分页动态查询
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWhitParam")
	List<Notice> selectByPage(Map<String, Object> params);

	//动态插入公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);

	//根据id删除公告
	@Delete("delete from "+NOTICETABLE+" where id=#{id}")
	void deleteById(Integer id);

	//根据id查询公告
	@Select("select * from "+NOTICETABLE+" where id=#{id}")
	Notice selectById(Integer id);

	//动态修改公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);

}
