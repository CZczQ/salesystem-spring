package org.cc.pojo;

/**
 * @Author cc
 * @Date 2022/10/15 10:50
 * @PackageName:org.cc.pojo
 * @ClassName: Good
 * @Description: TODO
 * @Version 1.0
 */
public class Good {
    private Integer id;
    private String goodname;
    private String goodtype;
    private Double price;
    private String pic;
    private int amount;

    public Good() {
    }


    public Good(Integer id, String goodname, String goodtype, Double price, String pic) {
        this.id = id;
        this.goodname = goodname;
        this.goodtype = goodtype;
        this.price = price;
        this.pic = pic;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodname='" + goodname + '\'' +
                ", goodtype='" + goodtype + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
