package org.fkit.dym.dao.provider;

import static org.fkit.dym.util.common.DymConstants.EMPLOYEETABLE;


import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.dym.domain.Employee;


public class EmployeeDynaSqlProvider {

	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(EMPLOYEETABLE);
				if(params.get("employee")!=null) {
					Employee employee=(Employee)params.get("employee");
					if(employee.getRealname()!=null && !employee.getRealname().equals("")) {
						WHERE("realname LIKE CONCAT ('%',#{employee.realname},'%')");
					}
					if(employee.getRealclass()!=null&&!employee.getRealclass().equals("")) {
						WHERE("realclass LIKE CONCAT ('%',#{employee.realclass},'%')");
					}
					if(employee.getSex()!=null && employee.getSex()!=0) {
						WHERE("sex = #{employee.sex}");
					}
					if(employee.getPosition()!=null && !employee.getPosition().equals("0") ) {
						WHERE("position = #{employee.position}");
					}
					if(employee.getDepartment()!=null && !employee.getDepartment().equals("0")) {
						WHERE("department = #{employee.department}");
					}
					if(employee.getPhonenum()!=null&&!employee.getPhonenum().equals("")) {
						WHERE("phonenum LIKE CONCAT ('%',#{employee.phonenum},'%')");
					}			
				}
			}
		}.toString();
	}
	
	
	//分页动态查询
		public String selectWithParam(Map<String,Object> params) {
			String sql=new SQL() {
				{
					SELECT("*");
					FROM(EMPLOYEETABLE);
					if(params.get("employee")!=null) {
						Employee employee=(Employee)params.get("employee");
						
						if(employee.getRealname()!=null && !employee.getRealname().equals("")) {
							WHERE("realname LIKE CONCAT ('%',#{employee.realname},'%')");
						}
						if(employee.getRealclass()!=null && !employee.getRealclass().equals("")) {
							WHERE("realclass LIKE CONCAT ('%',#{employee.realclass},'%')");
						}
						System.out.print("sex:");
						System.out.println(employee.getSex());
						
						if(employee.getSex()!=null && employee.getSex()!=0) {
							WHERE("sex = #{employee.sex}");
						}
						
						System.out.print("position:");
						System.out.println(employee.getPosition());
						
						if(employee.getPosition()!=null && !employee.getPosition().equals("0")) {
							WHERE("position = #{employee.position}");
						}
						
						System.out.print("department:");
						System.out.println(employee.getDepartment());
						
						if(employee.getDepartment()!=null&&!employee.getDepartment().equals("0")) {
							WHERE("department = #{employee.department}");
						}
						if(employee.getPhonenum()!=null && !employee.getPhonenum().equals("")) {
							WHERE("phonenum LIKE CONCAT ('%',#{employee.phonenum},'%')");
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
		
		//动态插入
		public String insertEmployee(Employee employee) {
			return new SQL() {
				{
					INSERT_INTO(EMPLOYEETABLE);
					if(employee.getRealname()!=null) {
						VALUES("realname","#{realname}");
					}
					if(employee.getRealclass()!=null) {
						VALUES("realclass","#{realclass}");
					}
					if(employee.getPhonenum()!=null) {
						VALUES("phonenum","#{phonenum}");
					}
					if(employee.getEmpnum()!=null) {
						VALUES("empnum","#{empnum}");
					}
					if(employee.getSex()!=null) {
						VALUES("sex","#{sex}");
					}
					if(employee.getParty()!=null) {
						VALUES("party","#{party}");
					}
					if(employee.getPosition()!=null) {
						VALUES("position","#{position}");
					}
					if(employee.getDepartment()!=null) {
						VALUES("department","#{department}");
					}
					
				}
			}.toString();
		}
		
		public String updateEmployee(Employee employee) {
			return new SQL() {
				{
					UPDATE(EMPLOYEETABLE);
					if(employee.getRealname()!=null) {
						SET("realname=#{realname}");
					}
					if(employee.getRealclass()!=null) {
						SET("realclass=#{realclass}");
					}
					if(employee.getPhonenum()!=null) {
						SET("phonenum=#{phonenum}");
					}
					if(employee.getParty()!=null) {
						SET("party=#{party}");
					}
					if(employee.getEmpnum()!=null) {
						SET("empnum=#{empnum}");
					}
					if(employee.getSex()!=null) {
						SET("sex=#{sex}");
					}
					if(employee.getPosition()!=null) {
						SET("position=#{position}");
					}
					if(employee.getDepartment()!=null) {
						SET("department=#{department}");
					}
					WHERE("id=#{id}");
				}
			}.toString();
		}
}
