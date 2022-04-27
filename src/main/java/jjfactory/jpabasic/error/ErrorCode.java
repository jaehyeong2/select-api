package jjfactory.jpabasic.error;

public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "C001", "올바르지 않은 형식입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않는 메소드입니다."),
    ENTITY_NOT_FOUND(400, "C003", "해당 엔티티를 찾을 수가 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C004", "알 수 없는 에러 (서버 에러)"),
    INVALID_TYPE_VALUE(400, "C005", "타입이 올바르지 않습니다."),
    HANDLE_ACCESS_DENIED(403, "C006", "권한이 없습니다."),
    HANDLE_INVALID_TOKEN(401, "C007", "토큰이 없거나 올바르지 않습니다."),

    NOT_FOUND_USER(509, "L007", "존재하지 않는 회원입니다..");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status,String code,String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}
