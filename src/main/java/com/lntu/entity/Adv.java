package com.lntu.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Adv implements Comparable<Adv> {

    private Integer id;

    private String name;

    private String photo;

    private Integer addtime;

    private Integer sort;

    private String type;

    private String action;

    private Byte position;

    public Adv() {
    }

    @Override
    public int compareTo(Adv adv) {
        if(adv != null){
            return adv.getSort() - this.sort;
        }
        return 0;
    }

}