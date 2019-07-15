package com.fenglangjuxu.takeout.event;

/**
 * @author syj
 * @date 2019/6/24
 */
public class TakeOutFoodClassificationSelectedEvent {
    private String name;
    public TakeOutFoodClassificationSelectedEvent (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
