package model;

import javax.servlet.http.Cookie;

public class ActorManagement {
    public static String encryptPassword(String password){
        String encrpytPassword = "";
        int rand = (int)((Math.random()*(9)) + 1);
        for(int i = password.length()-1 ; i >= 0 ; i--){
            char iter = password.charAt(i);
            if(iter >= 65 && iter <= 90){
                iter = (iter + "").toLowerCase().charAt(0);
            }else if(iter >= 97 && iter <= 122){
                iter = (iter + "").toUpperCase().charAt(0);
            }
            iter = (char)(iter + rand);
            encrpytPassword += iter;
        }
        encrpytPassword += rand;
        return encrpytPassword;
    }

    public static String decryptPassword(String encryptPassword){
        String password = "";
        int rand = Integer.parseInt(encryptPassword.charAt(encryptPassword.length()-1)+"");
        for(int i = encryptPassword.length()-2 ; i >= 0 ; i--){
            char iter = encryptPassword.charAt(i);
            if(iter >= 65 && iter <= 90){
                iter = (iter + "").toLowerCase().charAt(0);
            }else if(iter >= 97 && iter <= 122){
                iter = (iter + "").toUpperCase().charAt(0);
            }
            iter = (char)(iter - rand);
            password += iter;
        }
        return password;
    }

    public static String chkCookie(Cookie[] cookies){
        String value = null;
        for(int i = 0; i < cookies.length ;i++){
            Cookie c = cookies[i];
            if(c.getName().equals("restowner")){
                value = c.getValue();
                return value;
            }
        }
        return value;
    }
}

//check code 1