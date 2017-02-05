package knowhere.mx.milanopartner.interfaces;

import knowhere.mx.milanopartner.pojo.MilanoUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by cacorona on 21/07/2016.
 */
public interface MilanoPartnerLoginInterface  {

    @POST("/rest-auth/login/")
    Call<MilanoUser> loginUser(@Body MilanoUser user);

}
