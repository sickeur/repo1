package com.Futebol_TV.AO.VIVO.NodoGO.CurrentModels;

public class s_t_e_p_s_model {

    private String pleaseSelect_TV;
    private String choice_1;
    private String choice_2;
    private int ImageView1;
    private int ImageView2;

    public String getPleaseSelect_TV() {
        return pleaseSelect_TV;
    }

    public void setPleaseSelect_TV(String pleaseSelect_TV) {
        this.pleaseSelect_TV = pleaseSelect_TV;
    }

    public String getChoice_1() {
        return choice_1;
    }

    public void setChoice_1(String choice_1) {
        this.choice_1 = choice_1;
    }

    public String getChoice_2() {
        return choice_2;
    }

    public void setChoice_2(String choice_2) {
        this.choice_2 = choice_2;
    }

    public int getImageView1() {
        return ImageView1;
    }

    public void setImageView1(int imageView1) {
        this.ImageView1 = imageView1;
    }

    public int getImageView2() {
        return ImageView2;
    }

    public void setImageView2(int imageView2) {
        this.ImageView2 = imageView2;
    }

    public s_t_e_p_s_model(String pleaseSelect_TV, String choice_1, String choice_2, int ImageView1, int ImageView2) {
        this.pleaseSelect_TV = pleaseSelect_TV;
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.ImageView1 = ImageView1;
        this.ImageView2 = ImageView2;
    }
}
