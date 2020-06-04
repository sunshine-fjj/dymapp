package org.fkit.dym.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.dym.domain.Appointment;
import static org.fkit.dym.util.common.DymConstants.APPOINTMENTTABLE;

/**
 * ԤԼ��Ϣ��̬sql����ṩ��
 * @author ������
 *
 */
public class AppointmentDynaSqlProvider {

	//��̬��ѯ������
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(APPOINTMENTTABLE);
				if(params.get("appointment")!=null) {
					Appointment appointment=(Appointment)params.get("appointment");
					if(appointment.getRealclass()!= null && !appointment.getRealclass().equals("")){
                        WHERE("  realclass LIKE CONCAT ('%',#{appointment.realclass},'%') ");
                    }
                    if(appointment.getRealname() != null && !appointment.getRealname().equals("")){
                        WHERE("  realname LIKE CONCAT ('%',#{appointment.realname},'%') ");
                    }
                    if(appointment.getAppointstatus()!=null && !appointment.getAppointstatus().equals(""))
                    {
                    	WHERE("  appointstatus LIKE CONCAT ('%',#{appointment.appointstatus},'%') ");
                    }
                    if(appointment.getOutlinedescribe() != null && !appointment.getOutlinedescribe().equals("")){
                        WHERE("  outlinedescribe LIKE CONCAT ('%',#{appointment.outlinedescribe},'%') ");
                    }
				}
			}
		}.toString();
	}
	
	
	// ��̬��ѯ����ԤԼ������
    public String count2(Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(APPOINTMENTTABLE);
                if(params.get("appointment") != null){
                    Appointment appointment = (Appointment)params.get("appointment");
                    Integer userid=appointment.getUser().getId();

                    System.out.println(appointment.getDetaildescribe());
                    System.out.println(appointment.getAppointstatus());
                    
                    if(appointment.getDetaildescribe() != null && !appointment.getDetaildescribe().equals("")){
                        WHERE("  detaildescribe LIKE CONCAT ('%',#{appointment.detaildescribe},'%') ");   
                    }
                    if(appointment.getAppointstatus() != null && !appointment.getAppointstatus().equals("0")){
                        WHERE("  appointstatus LIKE CONCAT ('%',#{appointment.appointstatus},'%') ");                     
                    }
     
                    WHERE("user_id="+userid);

                }
            }
        }.toString();
    }    
    
    
    
    public String selectWithParam2 (Map<String, Object> params){
        String sql= new SQL(){
            {
                SELECT("*");
                FROM(APPOINTMENTTABLE);
                if(params.get("appointment") != null){
                    Appointment appointment = (Appointment)params.get("appointment");
                    Integer userid=appointment.getUser().getId();
                    
                    if(appointment.getDetaildescribe() != null && !appointment.getDetaildescribe().equals("")){
                        WHERE("  detaildescribe LIKE CONCAT ('%',#{appointment.detaildescribe},'%') ");
                        //System.out.println("3");
                    }
                    if(appointment.getAppointstatus() != null && !appointment.getAppointstatus().equals("0")){
                        WHERE("  appointstatus LIKE CONCAT ('%',#{appointment.appointstatus},'%') ");
                      //  System.out.println("4");
                    }
     
                    WHERE("user_id="+userid);

                }
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        System.out.println(sql);
		return sql;
    }    
    
    
    
    
	//��̬��ҳ��ѯ
	public String selectWithParam(Map<String,Object> params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM(APPOINTMENTTABLE);
				if(params.get("appointment")!=null) {
					Appointment appointment=(Appointment)params.get("appointment");
					
					if(appointment.getRealclass()!= null && !appointment.getRealclass().equals("")){
                        WHERE("  realclass LIKE CONCAT ('%',#{appointment.realclass},'%') ");
                    }
                    if(appointment.getRealname() != null && !appointment.getRealname().equals("")){
                        WHERE("  realname LIKE CONCAT ('%',#{appointment.realname},'%') ");
                    }
                    if(appointment.getAppointstatus()!=null && !appointment.getAppointstatus().equals(""))
                    {
                    	WHERE("  appointstatus LIKE CONCAT ('%',#{appointment.appointstatus},'%') ");
                    }
                    if(appointment.getOutlinedescribe() != null && !appointment.getOutlinedescribe().equals("")){
                        WHERE("  outlinedescribe LIKE CONCAT ('%',#{appointment.outlinedescribe},'%') ");
                    }
				}
			}
		}.toString();
		if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
		return sql;
	}
	
	//��̬�޸�ԤԼ���״̬
	public String updateAppointment(Appointment appointment) {
		return new SQL() {
			{
				UPDATE(APPOINTMENTTABLE);
				if(appointment.getAppointstatus()!=null) {
					SET("appointstatus=#{appointstatus}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}
	
	//��̬����
	public String insertAppointment(Appointment appointment){
	        
	        return new SQL(){
	            {
	                INSERT_INTO(APPOINTMENTTABLE);
	                if(appointment.getRealclass() != null && !appointment.getRealclass().equals("")){
	                    VALUES("realclass", "#{realclass}");
	                }
	                if(appointment.getPhonenum() != null && !appointment.getPhonenum().equals("")){
	                    VALUES("phonenum", "#{phonenum}");
	                }
	                if(appointment.getDetaildescribe() != null && !appointment.getDetaildescribe().equals("")){
	                    VALUES("detaildescribe", "#{detaildescribe}");
	                }
	                if(appointment.getOutlinedescribe() != null && !appointment.getOutlinedescribe().equals("")){
	                    VALUES("outlinedescribe", "#{outlinedescribe}");
	                }
	                if(appointment.getAppointdate() != null && !appointment.getAppointdate().equals("")){
	                    VALUES("appointdate", "#{appointdate}");
	                }
	                if(appointment.getUser() != null && appointment.getUser().getRealname()!=null){
	                    VALUES("realname", "#{user.realname}");
	                }
	                if(appointment.getUser() != null && appointment.getUser().getId() != null){
	                    VALUES("user_id", "#{user.id}");
	                }
	            }
	        }.toString();
	    }
}
