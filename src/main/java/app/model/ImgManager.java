package app.model;

/**
 * Created by PZON_SM on 18.12.2020.
 **/
public class ImgManager {

    public static String setImg(String icon) {
        switch (icon) {
            case "01d":
                return "/icons/01d.png";
            case "01n":
                return "/icons/01n.png";
            case "02d":
                return "/icons/02d.png";
            case "02n":
                return "/icons/02n.png";
            case "03d":
            case "03n":
                return "/icons/03.png";
            case "04d":
            case "04n":
                return "/icons/04.png";
            case "09d":
            case "09n":
                return "/icons/09.png";
            case "10d":
                return "/icons/10d.png";
            case "10n":
                return "/icons/10n.png";
            case "11n":
            case "11d":
                return "/icons/11.png";
            case "13d":
            case "13n":
                return "/icons/13.png";
            case "50d":
            case "50n":
                return "/icons/50.png";
        }
        return "/icons/01d.jpg";
    }
}
