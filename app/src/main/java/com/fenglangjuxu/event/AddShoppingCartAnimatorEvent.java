package com.fenglangjuxu.event;

/**
 * @author syj
 * @date 2019/7/8
 */
public class AddShoppingCartAnimatorEvent {
    private int [] startLocation;

    public AddShoppingCartAnimatorEvent(int[] location){
        this.startLocation = location;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
    }
}
