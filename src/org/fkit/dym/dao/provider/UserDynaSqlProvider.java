package org.fkit.dym.dao.provider;

import java.util.Map;
import static org.fkit.dym.util.common.DymConstants.USERTABLE;
import org.apache.ibatis.jdbc.SQL;
import org.fkit.dym.domain.User;

/**
 * �û���̬sql����ṩ��
 * @author ������
 *
 */
public class UserDynaSqlProvider {
	
	//��̬��ѯ������
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(USERTABLE);
				if(params.get("user")!=null) {
					User user=(User)params.get("user");
					if(user.getRealname()!=null&&!user.getRealname().equals("")) {
						WHERE("realname LIKE CONCAT ('%',#{user.realname},'%')");
					}
					if(user.getRealclass()!=null&&!user.getRealclass().equals("")) {
						WHERE("realclass LIKE CONCAT ('%',#{user.realclass},'%')");
					}
				}
			}
		}.toString();
	}
	
	
	//��ҳ��̬��ѯ
	public String selectWithParam(Map<String,Object> params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user")!=null) {
					User user=(User)params.get("user");
					if(user.getRealname()!=null&&!user.getRealname().equals("")) {
						WHERE("realname LIKE CONCAT ('%',#{user.realname},'%')");
					}
					if(user.getRealclass()!=null&&!user.getRealclass().equals("")) {
						WHERE("realclass LIKE CONCAT ('%',#{user.realclass},'%')");
					}
				}
			}
		}.toString();
		
		System.out.print(sql);
		if(params.get("pageModel")!=null) {
			sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
		}
		
		return sql;
	}
	
	//��̬����
	public String updateUser(User user) {
		return new SQL() {
			{
				UPDATE(USERTABLE);
				if(user.getLoginname()!=null) {
					SET("loginname=#{loginname}");
				}
				if(user.getPassword()!=null) {
					SET("password=#{password}");
				}
				if(user.getRealname()!=null) {
					SET("realname=#{realname}");
				}
				if(user.getRealclass()!=null) {
					SET("realclass=#{realclass}");
				}
				if(user.getUserstatus()!=null) {
					SET("userstatus=#{userstatus}");
				}
				if(user.getCreatedate()!=null) {
					SET("createdate=#{createdate}");
				}
				if(user.getPhonenum()!=null) {
					SET("phonenum=#{phonenum}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}
	
	//��̬����
	public String insertUser(User user) {
		return new SQL() {
			{
				INSERT_INTO(USERTABLE);
				if(user.getRealname()!=null) {
					VALUES("realname","#{realname}");
				}
				if(user.getRealclass()!=null) {
					VALUES("realclass","#{realclass}");
				}
				if(user.getPhonenum()!=null) {
					VALUES("phonenum","#{phonenum}");
				}
				if(user.getUserstatus()!=null) {
					VALUES("userstatus","#{userstatus}");
				}
				if(user.getLoginname()!=null) {
					VALUES("loginname","#{loginname}");
				}
				if(user.getPassword()!=null) {
					VALUES("password","#{password}");
				}
			}
		}.toString();
	}
}
