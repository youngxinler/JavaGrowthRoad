package com.annotation;

/**
 * @author youngxinler  2019/8/10
 **/
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(value = 30)
    String firstName;

    @SQLString(50)
    String lastName;

    @SQLInteger
    Integer age;

    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;
    public String getHandle(){
        return handle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString(){
        return handle;
    }
}
