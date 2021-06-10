package rs.ac.bg.fon.CinemaCommon.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface GenericEntity extends Serializable{
    String getTableName();

    String getColumnNamesForInsert();
    
    String getDeleteString();
    
    String getUpdateString();    
        
    String getUpdateValues(GenericEntity entity);

    String getInsertValues();
    
    String getInsertValues(int id);
    
    String getAlias();
    
    String getForeignKey();
    
    String getSecondForeignKey();
    
    String getPrimaryKey();

    void setId(int id);
    
    List<GenericEntity> getList();
        
    List<GenericEntity> readList(ResultSet rs) throws Exception;
}
