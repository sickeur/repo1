package com.Futebol_TV.AO.VIVO.NodoGO.CurrentModels;

public class g_u_i_d_e_model {

    public g_u_i_d_e_model(String myTitle, String myDescription, String myImage) {
        this.myTitle = myTitle;
        this.myDescription = myDescription;
        this.myImage = myImage;
    }

    private String myTitle;

    private String myDescription;
    private String myImage;

    public String getMyImage() {
        return myImage;
    }

    public void setMyImage(String myImage) {
        this.myImage = myImage;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public String getMyDescription() {
        return myDescription;
    }

}
