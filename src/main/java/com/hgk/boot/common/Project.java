package com.hgk.boot.common;

import lombok.Data;

@Data
public class Project implements Comparable<Project>{
    public Integer id;

    public String title;//项目名称

    public Integer startYearMonth;//202108

    public Project(Integer id,String title,Integer startYearMonth){
        this.id = id;
        this.title = title;
        this.startYearMonth = startYearMonth;
    }

    /**
     * 定制排序规则：
     * 先按照 startYearMonth 倒序排，再按照 id 升序排列
     * @param o
     * @return
     */
    @Override
    public int compareTo(Project o) {
        if(o.getStartYearMonth() > this.getStartYearMonth()){
            return 1;
        }
        if(o.getStartYearMonth() < this.getStartYearMonth()){
            return -1;
        }

        if(o.getId() < this.getId()){
            return 1;
        }
        if(o.getId() > this.getId()){
            return -1;
        }
        return 0;
    }
}
