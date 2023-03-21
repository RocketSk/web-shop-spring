package com.training.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Good> goods;
    public Cart(){
        this.goods = new ArrayList<>();
    }
    public List<Good> getGoods(){
        return goods;
    }
    public void addGood(Good good){
        goods.add(good);
    }

    public double getTotalCost(){
        double totalCost = 0;
        for(Good good : goods){
            totalCost += good.getPrice();
        }
        return totalCost;
    }
}
