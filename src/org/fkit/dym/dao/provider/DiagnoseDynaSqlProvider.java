package org.fkit.dym.dao.provider;

import static org.fkit.dym.util.common.DymConstants.MYDOWNLOADTABLE;
import static org.fkit.dym.util.common.DymConstants.DIAGNOSETABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.dym.domain.Diagnose;
public class DiagnoseDynaSqlProvider {

	//动态查询总数量
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(DIAGNOSETABLE);
				if(params.get("diagnose")!=null) {
					Diagnose diagnose=(Diagnose) params.get("diagnose");
					if(diagnose.getTitle()!=null && !diagnose.getTitle().equals("")) {
						WHERE(" title LIKE CONCAT ('%',#{diagnose.title},'%')");
					}
					if(diagnose.getRemark()!=null && !diagnose.getRemark().equals("")) {
						WHERE(" remark LIKE CONCAT ('%',#{diagnose.remark},'%')");
					}
					if(diagnose.getAuthor()!=null && !diagnose.getAuthor().equals("")) {
						WHERE(" author LIKE CONCAT ('%',#{diagnose.author},'%')");
					}
				}
			}
			
		}.toString();
	}
	
	// 动态查询个人上传故障文档总数量
    public String count3(Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(DIAGNOSETABLE);
                if(params.get("diagnose") != null){
                	Diagnose diagnose = (Diagnose)params.get("diagnose");
                    Integer userid=diagnose.getUser().getId();
                    
                    System.out.print("userid:");
                    System.out.println(userid);
                    
                    if(diagnose.getTitle() != null && !diagnose.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{diagnose.title},'%') ");   
                    }
                    if(diagnose.getRemark() != null && !diagnose.getRemark().equals("")){
                        WHERE("  remark LIKE CONCAT ('%',#{diagnose.remark},'%') ");                     
                    }
                    if(diagnose.getFilename() != null && !diagnose.getFilename().equals("")){
                        WHERE("  filename LIKE CONCAT ('%',#{diagnose.filename},'%') ");                     
                    }
     
                    WHERE("user_id="+userid);

                }
            }
        }.toString();
    }    
	
    
    
    public String count4(Map<String, Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(MYDOWNLOADTABLE);
                if(params.get("diagnose") != null){
                	Diagnose diagnose = (Diagnose)params.get("diagnose");
                    Integer userid=diagnose.getUser().getId();
                    
                    if(diagnose.getTitle() != null && !diagnose.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{diagnose.title},'%') ");   
                    }
                    if(diagnose.getFilename() != null && !diagnose.getFilename().equals("")){
                        WHERE("  filename LIKE CONCAT ('%',#{diagnose.filename},'%') ");                     
                    }
                    WHERE("user_id="+userid);

                }
            }
        }.toString();
    }    
    
    
    
    public String selectWithParam3 (Map<String, Object> params){
        String sql= new SQL(){
            {
            	SELECT("*");
                FROM(DIAGNOSETABLE);
                if(params.get("diagnose") != null){
                	Diagnose diagnose = (Diagnose)params.get("diagnose");
                    Integer userid=diagnose.getUser().getId();
                    
                    System.out.print("userid:");
                    System.out.println(userid);
                    
                    if(diagnose.getTitle() != null && !diagnose.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{diagnose.title},'%') ");   
                    }
                    if(diagnose.getRemark() != null && !diagnose.getRemark().equals("")){
                        WHERE("  remark LIKE CONCAT ('%',#{diagnose.remark},'%') ");                     
                    }
                    if(diagnose.getFilename() != null && !diagnose.getFilename().equals("")){
                        WHERE("  filename LIKE CONCAT ('%',#{diagnose.filename},'%') ");                     
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
    
    
    
    
    public String selectWithParam4 (Map<String, Object> params){
        String sql= new SQL(){
            {
            	SELECT("*");
                FROM(MYDOWNLOADTABLE);
                if(params.get("diagnose") != null){
                	Diagnose diagnose = (Diagnose)params.get("diagnose");
                    Integer userid=diagnose.getUser().getId();
                    if(diagnose.getTitle() != null && !diagnose.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{diagnose.title},'%') ");   
                    }
                    if(diagnose.getFilename() != null && !diagnose.getFilename().equals("")){
                        WHERE("  filename LIKE CONCAT ('%',#{diagnose.filename},'%') ");                     
                    }
                    WHERE("user_id="+userid);

                }
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
       
		return sql;
    }    
    
    
    
	//分页动态查询
	public String selectWithParam(Map<String,Object> params) {
		String sql=new SQL() {
			{
				SELECT("*");
				FROM(DIAGNOSETABLE);
				if(params.get("diagnose")!=null) {
					Diagnose diagnose=(Diagnose)params.get("diagnose");
					if(diagnose.getTitle()!=null && !diagnose.getTitle().equals("")) {
						WHERE(" title LIKE CONCAT ('%',#{diagnose.title},'%') ");
					}
					if(diagnose.getRemark()!=null && !diagnose.getRemark().equals("")) {
						WHERE(" remark LIKE CONCAT ('%',#{diagnose.remark},'%')" );
					}
					if(diagnose.getAuthor()!=null && !diagnose.getAuthor().equals("")) {
						WHERE(" author LIKE CONCAT ('%',#{diagnose.author},'%')" );
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel")!=null) {
			sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		
		return sql;
	}
	
	//动态插入故障信息
	public String insertDiagnose(Diagnose diagnose) {
		return new SQL() {
			{
				INSERT_INTO(DIAGNOSETABLE);
				if(diagnose.getTitle()!=null && !diagnose.getTitle().equals("")) {
					VALUES("title","#{title}");
				}
				if(diagnose.getRemark()!=null && !diagnose.getRemark().equals("")) {
					VALUES("remark","#{remark}");
				}
				if(diagnose.getFilename()!=null && !diagnose.getFilename().equals("")) {
					VALUES("filename","#{filename}");
				}
				if(diagnose.getFilepath()!=null && !diagnose.getFilepath().equals("")) {
					VALUES("filepath","#{filepath}");
				}
				if(diagnose.getUser() != null && diagnose.getUser().getRealname()!=null){
                    VALUES("author", "#{user.realname}");
                }
				if(diagnose.getUser() != null && diagnose.getUser().getId()!=null){
                    VALUES("user_id", "#{user.id}");
                }
			}
		}.toString();
	}
	
	
	public String insertMyDownload(Diagnose diagnose) {
		return new SQL() {
			{
				
				String title=diagnose.getTitle();
				String author=diagnose.getAuthor();
				System.out.println(title);
				String filename=diagnose.getFilename();
				System.out.println(filename);
				String filepath=diagnose.getFilepath();
				Integer user_id=diagnose.getUser().getId();
				System.out.println(user_id);
				
				
				INSERT_INTO(MYDOWNLOADTABLE);
				if(diagnose.getTitle()!=null && !diagnose.getTitle().equals("")) {
					VALUES("title","#{title}");
				}
				if(diagnose.getFilename()!=null && !diagnose.getFilename().equals("")) {
					VALUES("filename","#{filename}");
				}
				if(diagnose.getUser() != null && diagnose.getUser().getRealname()!=null){
                    VALUES("author", "#{user.realname}");
                }
				if(diagnose.getFilepath()!=null && !diagnose.getFilepath().equals("")) {
					VALUES("filepath","#{filepath}");
				}
				if(diagnose.getUser() != null && diagnose.getUser().getId()!=null){
                    VALUES("user_id", "#{user.id}");
                }
			}
		}.toString();
	}
	
	
	//动态更新
	public String updateDiagnose(Diagnose diagnose) {
		return new SQL() {
			{
				UPDATE(DIAGNOSETABLE);
				 if(diagnose.getTitle() != null && !diagnose.getTitle().equals("")){
	                    SET(" title = #{title} ");
	                }
	                if(diagnose.getFilename() != null && !diagnose.getFilename().equals("")){
	                    SET(" filename = #{filename} ");
	                }
	                if(diagnose.getRemark() != null && !diagnose.getRemark().equals("")){
	                    SET("remark = #{remark}");
	                }
	                if(diagnose.getAuthor() != null && !diagnose.getAuthor().equals("")){
	                    SET("author = #{author}");
	                }
	                WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
