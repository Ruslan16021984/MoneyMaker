package carbit3333333.gmail.com.moneytracker;

import com.google.gson.annotations.SerializedName;

public class AuthResult {
    public String status;
    public int id;
    @SerializedName("auth_token")
    public String token;
}
