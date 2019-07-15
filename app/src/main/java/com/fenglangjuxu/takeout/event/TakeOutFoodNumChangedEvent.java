package com.fenglangjuxu.takeout.event;

/**
 * @author syj
 * @date 2019/6/26
 */
public class TakeOutFoodNumChangedEvent {
    private int id;
    private int tag;
    private String price;
    private int num;
    private int difference;
    private String name;

    public TakeOutFoodNumChangedEvent(int id, int tag,int num, int difference,String price, String name){
        this.id = id;
        this.tag = tag;
        this.difference = difference;
        this.num = num;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
