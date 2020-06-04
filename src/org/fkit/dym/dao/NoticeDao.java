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

	//��̬��ѯ��������
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//��ҳ��̬��ѯ
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWhitParam")
	List<Notice> selectByPage(Map<String, Object> params);

	//��̬���빫��
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);

	//����idɾ������
	@Delete("delete from "+NOTICETABLE+" where id=#{id}")
	void deleteById(Integer id);

	//����id��ѯ����
	@Select("select * from "+NOTICETABLE+" where id=#{id}")
	Notice selectById(Integer id);

	//��̬�޸Ĺ���
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);

}
