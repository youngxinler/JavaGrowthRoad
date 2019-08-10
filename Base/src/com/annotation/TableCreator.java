package com.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youngxinler  2019/8/10
 **/
public class TableCreator {
    public static void main(String[] args) throws Exception{
        List<String> classNames = new ArrayList<>();
        classNames.add("com.annotation.Member");
        for (String className:
             classNames){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null){
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            if (tableName.length() < 1){
                //用class的名字
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field f :
                    cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] ans = f.getDeclaredAnnotations();
                if (ans.length < 1){
                    continue;
                }
                if (ans[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger)ans[0];
                    if (sInt.name().length() < 1)
                        columnName = f.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if (ans[0] instanceof SQLString){
                    SQLString string = (SQLString)ans[0];
                    if (string.name().length() < 1){
                        columnName = f.getName().toUpperCase();
                    }else {
                        columnName = string.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + string.value() + ")"
                            + getConstraints(string.constraints()));
                }
            }
            StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
            for (String columnDef: columnDefs){
                createCommand.append("\n    " + columnDef + ",");
            }
            String tableCreate = createCommand.substring(0,createCommand.length());
            System.out.println("Table creation SQL for" + className + " is \n" + tableCreate);
        }
    }

    private static String getConstraints(Constraints cons){
        String constraints = "";
        if (!cons.allowNull()){
            constraints += " NOT NULL";
        }
        if (cons.primaryKey()){
            constraints += " PRIMARY KEY";
        }
        if (cons.unique()){
            constraints += " UNIQUE";
        }
        return constraints;
    }
}
