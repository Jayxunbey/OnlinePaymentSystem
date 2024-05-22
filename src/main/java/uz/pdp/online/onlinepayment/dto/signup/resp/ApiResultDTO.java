package uz.pdp.online.onlinepayment.dto.signup.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResultDTO<T> {

    private boolean success;

    private T data;

    private String errorMessage;

    private List<FieldErrorDTO> fieldErrors;

    public static<T> ApiResultDTO<T> success(T data){
        ApiResultDTO<T> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setSuccess(true);
        apiResultDTO.setData(data);
        return apiResultDTO;
    }

    public static ApiResultDTO<?> error(String errorMessage){
        ApiResultDTO<?> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setSuccess(false);
        apiResultDTO.setErrorMessage(errorMessage);
        return apiResultDTO;
    }

}
