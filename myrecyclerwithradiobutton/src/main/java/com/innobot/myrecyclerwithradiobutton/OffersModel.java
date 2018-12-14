package com.innobot.myrecyclerwithradiobutton;

/**
 * Created by innobot-linux-7 on 7/5/18,07,MyApplication.
 */
class OffersModel {
    public OffersModel(String s, int i) {

        this.offer =s;
        this.savings=i;

    }

    String offer="";
    int savings;
    public String getOffer() {
        return offer;
    }

    public int getSavings() {
        return savings;
    }
}
