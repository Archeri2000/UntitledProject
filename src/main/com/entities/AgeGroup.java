package main.com.entities;

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
