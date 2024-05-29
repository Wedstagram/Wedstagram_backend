package backend.wedstagram.apiPayload.code.status;

import backend.wedstagram.apiPayload.code.BaseCode;
import backend.wedstagram.apiPayload.code.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),

    // Follow Error
    FOLLOW_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "FOLLOW4001", "이미 팔로우 중입니다."),
    FOLLOW_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOLLOW4002", "팔로우 중이 아닙니다."),
    FOLLOW_SELF(HttpStatus.BAD_REQUEST, "FOLLOW4003", "자기 자신을 팔로우하거나 취소할 수 없습니다."),

    // Feed Error
    FEED_NOT_FOUND(HttpStatus.BAD_REQUEST, "FEED4001", "게시글을 찾을 수 없습니다."),
    FEED_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "FEED4011", "게시글에 대한 권한이 없습니다.");

    // Follow Error
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .httpStatus(httpStatus)
                .isSuccess(false)
                .code(this.code)
                .message(this.message)
                .build();
    }

}
