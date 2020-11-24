package lab.management.Middlewares;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;

public class JWT_Helper {


    public static String createUserToken(String username, String user_role){

        try{

            final Algorithm algo = Algorithm.HMAC384("secret");

            String token = JWT.create()
                            .withIssuer("crossphoton")
                            .withClaim("username", username)
                            .withClaim("role", user_role)
                            .sign(algo);

            return token;
        } catch(JWTCreationException error){
            System.out.println(error);
            return null;
        }
    }

    public static boolean checkAdmin(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC384("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                                    .withIssuer("crossphoton")
                                    .build();

            DecodedJWT jwt = JWT.decode(token);
            verifier.verify(token);

            return jwt.getClaim("role").asString().equals("ROLE_ADMIN");

        } catch (JWTVerificationException exception){
            return false;
        }
    }


    public static boolean checkTeacher(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC384("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                                    .withIssuer("crossphoton")
                                    .build();

            DecodedJWT jwt = verifier.verify(token);

            return ((jwt.getClaim("role").asString().equals("ROLE_TEACHER")) || checkAdmin(token));
        } catch (JWTVerificationException exception){
            return false;
        }
    }


    public static boolean checkStudent(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC384("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                                    .withIssuer("crossphoton")
                                    .build();

            DecodedJWT jwt = verifier.verify(token);

            return ((jwt.getClaim("role").asString().equals("ROLE_STUDENT")) || checkTeacher(token));
        } catch (JWTVerificationException exception){
            return false;
        }
    }
    
}
