package st.ggviario.house.singleton;

import st.ggviario.house.model.Colaborador;

import java.util.UUID;

public class AuthSingleton {

    private static Colaborador auth;

    public static Colaborador getAuth() {
        return AuthSingleton.auth;
    }


    public static boolean isAuth(){
        return AuthSingleton.auth != null;
    }

    public static void login(String userName, String password ){
        AuthSingleton.auth = new Colaborador
                .ColaboradorBuilder()
                .id( UUID.fromString("00000000-0000-0000-0000-000000000001") )
                .build();
    }

    public static void logOut(){
        AuthSingleton.auth = null;
    }


}
