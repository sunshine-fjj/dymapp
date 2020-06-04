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

	//ͳ�ƹ�����Ϣ������
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="count")
	int count(Map<String, Object> params);

	//��̬��ҳ��ѯ������Ϣ
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="selectWithParam")
	List<Diagnose> selectByPage(Map<String, Object> params);

	//��̬���������Ϣ
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="insertDiagnose")
	void save(Diagnose diagnose);

	//����idɾ��������Ϣ
	@Delete("delete from "+DIAGNOSETABLE+" where id=#{id}")
	void deleteById(Integer id);

	//����id��ѯ������Ϣ
	@Select("select * from "+DIAGNOSETABLE+" where id=#{id}")
	Diagnose selectById(Integer id);
	
	//��̬�޸Ĺ�����Ϣ
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="updateDiagnose")
	void update(Diagnose diagnose);

	//��̬��ѯ�����ϴ������ĵ�
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="count3")
	int count3(Map<String, Object> params);

	//��̬��ҳ��ѯ
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="selectWithParam3")
	List<Diagnose> selectByPage3(Map<String, Object> params);

	//��̬���������Ϣ���ҵ������ĵ�����
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="insertMyDownload")
	void save2(Diagnose target);

	//��̬��ѯ�������ع����ĵ�
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="count4")
	int count4(Map<String, Object> params);
    
	//��̬��ҳ��ѯ
	@SelectProvider(type=DiagnoseDynaSqlProvider.class,method="selectWithParam4")
	List<Diagnose> selectByPage4(Map<String, Object> params);

	//����idɾ�������ĵ�
	@Delete("delete from "+MYDOWNLOADTABLE+" where id=#{id}")
	void deleteByDownId(Integer id);

}
