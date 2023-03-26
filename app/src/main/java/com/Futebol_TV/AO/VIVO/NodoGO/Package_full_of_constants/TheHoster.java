package com.Futebol_TV.AO.VIVO.NodoGO.Package_full_of_constants;

import java.net.MalformedURLException;
import java.net.URL;

public class TheHoster {
    public static String getHost(String url) throws MalformedURLException {
        return new URL(url).getHost();
    }
}
