package com.fenglangjuxu.takeout.model.store;

/**
 * @author syj
 * @date 2019/6/27
 */
public class TakeOutCartVo {
    private String name;
    private int id;
    private String price;
    private int num;
    private int tag;

    public TakeOutCartVo(String name, int id, String price,int num, int tag) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.num = num;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TakeOutCartVo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price='" + price + '\'' +
                ", num=" + num +
                '}';
    }
}
