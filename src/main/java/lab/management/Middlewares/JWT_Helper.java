package lab.management.Middlewares;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;


import com.auth0.jwt.JWT;

public class JWT_Helper {


    public static String createUserToken(String username, String user_role){

        try{

            final Algorithm algo = Algorithm.HMAC384("secret");

            String token = JWT.create()
                            .withIssuer("crosphoton")
                            .withClaim("username", username)
                            .withClaim("role", user_role)
                            .sign(algo);

            return token;
        } catch(JWTCreationException error){
            System.out.println(error);
            return null;
        }
    }
    
}
