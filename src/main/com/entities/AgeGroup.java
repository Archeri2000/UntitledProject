package main.com.entities;

/** This class holds the age group values
 * @author SS1 Group 6
 * @version 13
 */
public enum AgeGroup {
    Child, Adult, Senior;

    public static AgeGroup getGroup(int age){
        if(age < 12){
            return Child;
        }
        else if(age < 65){
            return Adult;
        }else{
            return Senior;
        }
    }
}
