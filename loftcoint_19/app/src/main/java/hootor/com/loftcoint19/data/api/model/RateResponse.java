package hootor.com.loftcoint19.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RateResponse {

    @SerializedName("data")
    public List<Coin> data;
}
