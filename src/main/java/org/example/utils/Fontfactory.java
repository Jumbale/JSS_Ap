package org.example.utils;

import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;

public class Fontfactory {
    public static FontIcon createIcon(Ikon iconEnum, int size, Color color){
        FontIcon icon=new FontIcon(iconEnum);
        icon.setIconSize(size);
        icon.setIconColor(color);

        return icon;
    }
}
