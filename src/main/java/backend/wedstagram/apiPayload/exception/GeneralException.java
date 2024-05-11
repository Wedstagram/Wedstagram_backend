package backend.wedstagram.apiPayload.exception;

import backend.wedstagram.apiPayload.code.BaseCode;
import backend.wedstagram.apiPayload.code.ReasonDto;
import backend.wedstagram.apiPayload.code.status.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public GeneralException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ReasonDto getErrorStatus() {
        return this.errorStatus.getReason();
    }

}
