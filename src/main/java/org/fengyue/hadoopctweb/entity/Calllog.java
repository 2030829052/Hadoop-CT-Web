/**
 * @Time : 2022/12/6 16:21
 * @Author : jin
 * @File : Calllog.class
 */
package org.fengyue.hadoopctweb.entity;

public class Calllog {
    private Integer id;
    private Integer tel_id;
    private Integer date_id;
    private Integer sumcall;
    private Integer sumduration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTel_id() {
        return tel_id;
    }

    public void setTel_id(Integer tel_id) {
        this.tel_id = tel_id;
    }

    public Integer getDate_id() {
        return date_id;
    }

    public void setDate_id(Integer date_id) {
        this.date_id = date_id;
    }

    public Integer getSumcall() {
        return sumcall;
    }

    public void setSumcall(Integer sumcall) {
        this.sumcall = sumcall;
    }

    public Integer getSumduration() {
        return sumduration;
    }

    public void setSumduration(Integer sumduration) {
        this.sumduration = sumduration;
    }


    @Override
    public String toString() {
        return "Calllog{" +
                "id=" + id +
                ", tel_id=" + tel_id +
                ", date_id=" + date_id +
                ", sumcall=" + sumcall +
                ", sumduration=" + sumduration + '}';
    }
}
