package com.myEnum;

/**
 * @author youngxinler  19-6-20 下午4:00
 * @version 0.1
 **/

public class EnumTest {
    private Day day;


    public EnumTest(Day day) {
        this.day = day;
    }

    public void whatIsThat() {
        switch (day) {
            case FRIDAY:
                System.out.println("friday's time is so fast");
                break;
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
                System.out.println("these days is so terrible");
                break;
            case SUNDAY:
            case SATURDAY:
                System.out.println("i am so happy!");
                break;
        }
    }

    public static void main(String[] args) {
        Day friday = Day.FRIDAY;
        EnumTest test1 = new EnumTest(friday);
        test1.whatIsThat();
        Day monday = Day.MONDAY;
        EnumTest test2 = new EnumTest(monday);
        test2.whatIsThat();
        Day sunday = Day.SUNDAY;
        EnumTest test3 = new EnumTest(sunday);
        test3.whatIsThat();
    }
}
