package org.fkit.dym.dao.provider;

import static org.fkit.dym.util.common.DymConstants.NOTICETABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.dym.domain.Notice;

/**
 * ���涯̬sql����ṩ�� 
 * @author ������
 *
 */
public class NoticeDynaSqlProvider {
	
	//��̬��ѯ������
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
                FROM(NOTICETABLE);
                if(params.get("notice") != null){
                    Notice notice = (Notice)params.get("notice");
                    if(notice.getTitle() != null && !notice.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }
                    if(notice.getContent() != null && !notice.getContent().equals("")){
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                    if(notice.getAuthor()!=null && !notice.getAuthor().equals(""))
                    {
                    	WHERE("  author LIKE CONCAT ('%',#{notice.author},'%') ");
                    }
                }
			}
		}.toString();
	}
	
	//��ҳ��̬��ѯ
	public String selectWhitParam(Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(NOTICETABLE);
                if(params.get("notice") != null){
                    Notice notice = (Notice)params.get("notice");
                    if(notice.getTitle() != null && !notice.getTitle().equals("")){
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }
                    if(notice.getContent() != null && !notice.getContent().equals("")){
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                    if(notice.getAuthor()!=null && !notice.getAuthor().equals(""))
                    {
                    	WHERE("  author LIKE CONCAT ('%',#{notice.author},'%') ");
                    }
                }
            }
        }.toString();
        
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        
        return sql;
    } 
	
	//��̬���빫��
	public String insertNotice(Notice notice) {
		return new SQL() {
			{
				INSERT_INTO(NOTICETABLE);
				if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
					VALUES("title","#{title}");
				}
				if(notice.getOutline()!=null && !notice.getOutline().equals("")) {
					VALUES("outline","#{outline}");
				}
				if(notice.getContent()!=null && !notice.getContent().equals("")) {
					VALUES("content","#{content}");
				}
				if(notice.getAuthor()!=null && !notice.getAuthor().equals("")) {
					VALUES("author","#{author}");
				}
			}
		}.toString();
	}
	
	//��̬���¹���
	public String updateNotice(Notice notice) {
		return new SQL() {
			{
				UPDATE(NOTICETABLE);
				if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
					SET("title=#{title}");
				}
				if(notice.getOutline()!=null && !notice.getOutline().equals("")) {
					SET("outline=#{outline}");
				}
				if(notice.getContent()!=null && !notice.getContent().equals("")) {
					SET("content=#{content}");
				}
				if(notice.getAuthor()!=null && !notice.getAuthor().equals("")) {
					SET("author=#{author}");
				}
				WHERE("id=#{id}");
			}
		}.toString();
	}
}
