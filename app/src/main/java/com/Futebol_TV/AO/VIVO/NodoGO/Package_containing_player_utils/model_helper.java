package com.Futebol_TV.AO.VIVO.NodoGO.Package_containing_player_utils;

public class model_helper {
    private String Game_Name;
    private String Game_Description;
    private String Game_Icon;
    private String Game_Bg_Image;
    private String Game_Url;

    public String getGame_Bg_Image() {
        return Game_Bg_Image;
    }

    public void setGame_Bg_Image(String game_Bg_Image) {
        Game_Bg_Image = game_Bg_Image;
    }

    public model_helper(String game_Name, String game_Description, String game_Icon, String game_Bg_Image , String game_Url) {
        Game_Name = game_Name;
        Game_Description = game_Description;
        Game_Icon = game_Icon;
        Game_Bg_Image = game_Bg_Image;
        Game_Url = game_Url;
    }

    public String getGame_Name() {
        return Game_Name;
    }

    public void setGame_Name(String game_Name) {
        Game_Name = game_Name;
    }

    public String getGame_Description() {
        return Game_Description;
    }

    public void setGame_Description(String game_Description) {
        Game_Description = game_Description;
    }

    public String getGame_Icon() {
        return Game_Icon;
    }

    public void setGame_Icon(String game_Icon) {
        Game_Icon = game_Icon;
    }

    public String getGame_Url() {
        return Game_Url;
    }

    public void setGame_Url(String game_Url) {
        Game_Url = game_Url;
    }
}
